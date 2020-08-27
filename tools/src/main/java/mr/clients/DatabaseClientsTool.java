package mr.clients;


import java.io.*;
import java.util.function.Consumer;

import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;

import mr.database.Database;
import mr.database.JDBCDatabase;
import mr.database.table.Table;


/**
 * This tool manages the Clients' Database.
 */
public class DatabaseClientsTool implements ClientsTool
{
	private final Database database;
	
	/**
	 * Creates new ClientsTool object that allows the programmer to run actions on Clients Database.
	 *
	 * @param databaseFile The database file.
	 *
	 * @throws FileNotFoundException Thrown when database file has not been found.
	 */
	public DatabaseClientsTool(File databaseFile) throws FileNotFoundException
	{
		database = new JDBCDatabase(databaseFile);
	}
	
	/**
	 * Encrypts clients' database using defined password.
	 * The database file should be decrypted before invoking this action. Otherwise, the tool is going to encrypt it again.
	 */
	@Override
	public void encrypt(String password)
	{
		StrongTextEncryptor strongTextEncryptor = new StrongTextEncryptor();
		strongTextEncryptor.setPassword(password);
		
		withClientsTable(table -> {
			table.forEach(entry -> {
				encrypt(table, entry, strongTextEncryptor);
			});
		});
	}
	
	/**
	 * Replaces specified entry with the encrypted one. The encryption is delivered by the TextEncryptor object.
	 *
	 * @param table The table that contains specified entry.
	 * @param entry The entry that will be encrypted.
	 * @param textEncryptor The encryption service.
	 */
	private void encrypt(Table table, String[] entry, TextEncryptor textEncryptor)
	{
		for (int i = 1; i < entry.length; i++)
		{
			entry[i] = textEncryptor.encrypt(entry[i]);
		}
		
		table.update(entry[0], entry);
	}
	
	/**
	 * Calls an action on the Clients' table.
	 *
	 * @param tableConsumer The action.
	 */
	private void withClientsTable(Consumer<Table> tableConsumer)
	{
		database.withTable("clients", tableConsumer);
	}
	
	/**
	 * Clears the clients table.
	 */
	@Override
	public void clear()
	{
		withClientsTable(Table::clear);
	}
	
	/**
	 * @throws IOException Thrown when unable to close the database.
	 */
	@Override
	public void close() throws IOException
	{
		database.close();
	}
}