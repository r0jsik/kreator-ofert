package mr.clients.service;


import java.util.List;
import java.util.function.Consumer;

import mr.client.Client;
import mr.record.RefreshableRecordsView;


public class ObservedClientsService implements SecureClientsService
{
	private final SecureClientsService clientsService;
	private final RefreshableRecordsView<Client> clientsView;
	
	public ObservedClientsService(SecureClientsService clientsService, RefreshableRecordsView<Client> clientsView)
	{
		this.clientsService = clientsService;
		this.clientsView = clientsView;
	}
	
	@Override
	public void insert(Client client)
	{
		clientsService.insert(client);
		clientsView.show(client);
	}
	
	@Override
	public void update(Client client)
	{
		clientsService.update(client);
		clientsView.refresh(client);
	}
	
	@Override
	public void remove(Client client)
	{
		clientsService.remove(client);
		clientsView.hide(client);
	}
	
	@Override
	public void withAll(Consumer<List<Client>> clientsConsumer)
	{
		clientsService.withAll(clientsConsumer);
	}
	
	@Override
	public void setPassword(String password)
	{
		clientsService.setPassword(password);
	}
}