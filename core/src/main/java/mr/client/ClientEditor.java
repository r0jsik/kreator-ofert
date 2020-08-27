package mr.client;


import mr.client.form.ClientFormController;
import mr.clients.service.ClientsService;
import mr.dialog.Confirmation;


public class ClientEditor
{
	private final Confirmation confirmation;
	private final ClientFormController controller;
	private final ClientsService clientsService;
	
	public ClientEditor(Confirmation confirmation, ClientFormController controller, ClientsService clientsService)
	{
		this.confirmation = confirmation;
		this.controller = controller;
		this.clientsService = clientsService;
	}
	
	public void edit(Client client)
	{
		client.provideDataTo(controller);
		
		confirmation.setOnConfirmed(() -> {
			client.applyDataFrom(controller);
			clientsService.update(client);
		});
		
		confirmation.setTitle("Edytuj klienta");
		confirmation.show();
	}
}