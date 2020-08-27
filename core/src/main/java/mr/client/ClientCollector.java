package mr.client;


import mr.client.form.ClientFormController;
import mr.clients.service.ClientsService;
import mr.dialog.Confirmation;


public class ClientCollector
{
	private final Confirmation confirmation;
	private final ClientFormController controller;
	private final ClientsService clientsService;
	
	public ClientCollector(Confirmation confirmation, ClientFormController controller, ClientsService clientsService)
	{
		this.confirmation = confirmation;
		this.controller = controller;
		this.clientsService = clientsService;
	}
	
	public void collect(Client client)
	{
		client.provideDataTo(controller);
		
		confirmation.setOnConfirmed(() -> {
			client.applyDataFrom(controller);
			clientsService.insert(client);
		});
		
		confirmation.setTitle("Dodaj klienta");
		confirmation.show();
	}
}