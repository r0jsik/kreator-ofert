package mr.clients.service;


import java.util.List;
import java.util.function.Consumer;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import mr.client.Client;
import mr.security.SecureHibernateField;


public class HibernateClientsService implements SecureClientsService
{
	private static final Configuration configuration;
	
	static
	{
		configuration = new Configuration();
		configuration.configure();
	}
	
	private final SessionFactory sessionFactory;
	
	public HibernateClientsService() throws HibernateException
	{
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Override
	public void insert(Client client)
	{
		withSession(session -> {
			session.save(client);
		});
	}
	
	private void withSession(Consumer<Session> sessionConsumer)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try
		{
			sessionConsumer.accept(session);
			transaction.commit();
		}
		catch (PersistenceException exception)
		{
			transaction.rollback();
			
			throw new ClientsServiceException("Błąd podczas wykonywania akcji Hibernate", exception);
		}
		finally
		{
			session.close();
		}
	}
	
	@Override
	public void update(Client client)
	{
		withSession(session -> {
			session.update(client);
		});
	}
	
	@Override
	public void remove(Client client)
	{
		withSession(session -> {
			session.delete(client);
		});
	}
	
	@Override
	public void withAll(Consumer<List<Client>> clientsConsumer)
	{
		withSession(session -> {
			Query query = createQuery(session);
			List<Client> clients = query.getResultList();
			
			clientsConsumer.accept(clients);
		});
	}
	
	private Query createQuery(Session session)
	{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		criteria.from(Client.class);
		
		return session.createQuery(criteria);
	}
	
	@Override
	public void setPassword(String password)
	{
		SecureHibernateField.setPassword(password);
	}
}