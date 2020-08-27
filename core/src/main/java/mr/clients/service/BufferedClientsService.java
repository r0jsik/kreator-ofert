package mr.clients.service;


import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import mr.client.Client;


public class BufferedClientsService implements SecureClientsService
{
	private final SecureClientsService clientsService;
	
	private List<Client> buffer;
	
	public BufferedClientsService(SecureClientsService clientsService)
	{
		this.clientsService = clientsService;
	}
	
	@Override
	public void insert(Client client)
	{
		clientsService.insert(client);
		buffer.add(client);
	}
	
	@Override
	public void update(Client client)
	{
		clientsService.update(client);
		
		updateInBuffer(client);
	}
	
	private void updateInBuffer(Client client)
	{
		Iterator<Client> iterator = buffer.iterator();
		
		for (int i = 0; i < buffer.size(); i++)
		{
			Client clientInBuffer = iterator.next();
			
			if (clientInBuffer.getID() == client.getID())
			{
				buffer.set(i, client);
				
				break;
			}
		}
	}
	
	@Override
	public void remove(Client client)
	{
		clientsService.remove(client);
		buffer.remove(client);
	}
	
	@Override
	public void withAll(Consumer<List<Client>> clientsConsumer)
	{
		if (buffer == null)
		{
			clientsService.withAll(clients -> buffer = clients);
		}
		
		clientsConsumer.accept(buffer);
	}
	
	@Override
	public void setPassword(String password)
	{
		clientsService.setPassword(password);
	}
}