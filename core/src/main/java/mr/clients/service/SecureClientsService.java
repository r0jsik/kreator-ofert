package mr.clients.service;


public interface SecureClientsService extends ClientsService
{
	void setPassword(String password);
}