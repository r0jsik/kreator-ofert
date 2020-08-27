package mr.clients.service;


import java.util.List;
import java.util.function.Consumer;

import mr.client.Client;


public interface ClientsService
{
	void insert(Client client);
	void update(Client client);
	void remove(Client client);
	void withAll(Consumer<List<Client>> clientsConsumer);
}