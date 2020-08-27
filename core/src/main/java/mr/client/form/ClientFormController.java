package mr.client.form;


import mr.client.Client;
import mr.client.ClientDataProvider;
import mr.client.ClientDataReceiver;


public interface ClientFormController extends ClientDataReceiver, ClientDataProvider
{
	default Client createClient()
	{
		Client client = new Client();
		client.applyDataFrom(this);
		
		return client;
	}
	
	void clear();
}