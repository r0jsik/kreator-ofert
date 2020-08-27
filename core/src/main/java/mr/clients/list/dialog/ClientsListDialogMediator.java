package mr.clients.list.dialog;


import mr.client.Client;
import mr.client.ClientCollector;
import mr.client.ClientEditor;
import mr.clients.service.ClientsService;
import mr.record.filter.FilteredRecordsView;


public class ClientsListDialogMediator
{
	private final ClientCollector clientCollector;
	private final ClientEditor clientEditor;
	private final ClientsService clientsService;
	private final FilteredRecordsView<Client> clientsView;
	
	public ClientsListDialogMediator(ClientCollector clientCollector, ClientEditor clientEditor, ClientsService clientsService, FilteredRecordsView<Client> clientsView)
	{
		this.clientCollector = clientCollector;
		this.clientEditor = clientEditor;
		this.clientsService = clientsService;
		this.clientsView = clientsView;
	}
	
	public void createClient()
	{
		clientCollector.collect(new Client());
	}
	
	public void modifyClient(Client client)
	{
		clientEditor.edit(client);
	}
	
	public void removeClient(Client client)
	{
		clientsService.remove(client);
	}
	
	public void filterClientsView(String pattern)
	{
		clientsView.filter(client -> {
			return clientContainsPattern(client, pattern.toUpperCase());
		});
	}
	
	private boolean clientContainsPattern(Client client, String pattern)
	{
		return client.toString().toUpperCase().contains(pattern);
	}
}