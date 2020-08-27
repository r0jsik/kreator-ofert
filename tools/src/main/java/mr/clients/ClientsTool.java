package mr.clients;


import java.io.Closeable;


public interface ClientsTool extends Closeable
{
	void encrypt(String password);
	void clear();
}