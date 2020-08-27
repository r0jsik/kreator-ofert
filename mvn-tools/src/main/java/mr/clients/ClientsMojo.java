package mr.clients;


import java.io.File;
import java.io.FileNotFoundException;

import mr.DatabaseMojo;


public abstract class ClientsMojo extends DatabaseMojo<ClientsTool>
{
	@Override
	public ClientsTool createTool(File databaseFile) throws FileNotFoundException
	{
		return new DatabaseClientsTool(databaseFile);
	}
}