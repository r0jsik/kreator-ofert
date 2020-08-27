package mr.price.list;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import mr.database.DatabaseWithSecureCharacters;
import mr.database.JDBCDatabase;
import mr.database.DecryptableDatabase;
import mr.database.table.TableImporter;


/**
 * This class manages the Price Lists' Database.
 */
public class DatabasePriceListTool implements PriceListTool
{
	private final DecryptableDatabase database;
	
	/**
	 * Creates new PriceListsTool object that allows the programmer to run actions on Price Lists Database.
	 *
	 * @param databaseFile The database file.
	 * @param password An password to the application.
	 *
	 * @throws FileNotFoundException Thrown when the database file has not been found.
	 */
	public DatabasePriceListTool(File databaseFile, String password) throws FileNotFoundException
	{
		database = new DatabaseWithSecureCharacters(new JDBCDatabase(databaseFile));
		database.decrypt(password);
	}
	
	/**
	 * Creates new price list using data provided by the TableImporter's instance.
	 *
	 * @param name The name of the price list.
	 * @param tableImporter An instance of the object providing data to the table.
	 */
	public void createPriceList(String name, TableImporter tableImporter)
	{
		database.importTable(name, tableImporter);
	}
	
	/**
	 * Changes the name of the table.
	 *
	 * @param oldName Current name of the table.
	 * @param newName Desired name of the table.
	 */
	@Override
	public void renamePriceList(String oldName, String newName)
	{
		database.renameTable(oldName, newName);
	}
	
	/**
	 * Updates the table with the specified name.
	 *
	 * @param name The name of the table.
	 * @param tableImporter An instance of the object providing data to the table.
	 */
	@Override
	public void updatePriceList(String name, TableImporter tableImporter)
	{
		database.withTable(name, table -> {
			tableImporter.forEach(record -> {
				table.update(record[0], record);
			});
		});
	}
	
	/**
	 * Removes price list with the specified name.
	 *
	 * @param name The name of the price list.
	 */
	@Override
	public void removePriceList(String name)
	{
		database.removeTable(name);
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