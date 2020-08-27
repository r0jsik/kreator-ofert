package mr.database;


import java.io.IOException;
import java.util.function.Consumer;

import mr.database.table.Table;
import mr.database.table.TableImporter;


public class DatabaseWithSecureCharacters implements DecryptableDatabase
{
	private final DecryptableDatabase database;
	
	public DatabaseWithSecureCharacters(DecryptableDatabase database)
	{
		this.database = database;
	}
	
	@Override
	public void decrypt(String password)
	{
		database.decrypt(onlySecureCharacters(password));
	}
	
	private String onlySecureCharacters(String string)
	{
		return string.replaceAll("[^a-zA-Z0-9!@#$%^&*-_=+;:/?]", "");
	}
	
	@Override
	public void withTable(String name, Consumer<Table> tableConsumer)
	{
		database.withTable(onlySecureCharacters(name), tableConsumer);
	}
	
	@Override
	public void createTable(String name, String[] columns)
	{
		database.createTable(onlySecureCharacters(name), onlySecureCharacters(columns));
	}
	
	private String[] onlySecureCharacters(String[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			array[i] = onlySecureCharacters(array[i]);
		}
		
		return array;
	}
	
	@Override
	public void renameTable(String oldName, String newName)
	{
		database.renameTable(onlySecureCharacters(oldName), onlySecureCharacters(newName));
	}
	
	@Override
	public void removeTable(String name)
	{
		database.removeTable(onlySecureCharacters(name));
	}
	
	@Override
	public void importTable(String name, TableImporter tableImporter)
	{
		database.importTable(onlySecureCharacters(name), tableImporter);
	}
	
	@Override
	public void close() throws IOException
	{
		database.close();
	}
}