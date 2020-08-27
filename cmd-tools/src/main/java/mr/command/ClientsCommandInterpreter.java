package mr.command;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;

import mr.ToolException;
import mr.clients.ClientsTool;
import mr.clients.DatabaseClientsTool;


public class ClientsCommandInterpreter
{
	@Command
	private void encrypt(String pathToDatabaseFile, String password)
	{
		invokeAction(pathToDatabaseFile, tool -> {
			tool.encrypt(password);
		});
	}
	
	private void invokeAction(String pathToDatabaseFile, Consumer<ClientsTool> toolConsumer)
	{
		try
		{
			File databaseFile = new File(pathToDatabaseFile);
			ClientsTool clientsTool = new DatabaseClientsTool(databaseFile);
			
			toolConsumer.accept(clientsTool);
		}
		catch (FileNotFoundException exception)
		{
			throw new ToolException(exception);
		}
	}
	
	@Command
	private void clear(String pathToDatabaseFile)
	{
		invokeAction(pathToDatabaseFile, tool -> {
			tool.clear();
		});
	}
	
	@Command
	private void close(String pathToDatabaseFile)
	{
		invokeAction(pathToDatabaseFile, tool -> {
			try
			{
				tool.close();
			}
			catch (IOException exception)
			{
				throw new ToolException(exception);
			}
		});
	}
}