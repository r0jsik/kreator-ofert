package mr.command;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;

import mr.database.table.OpenCSVTableImporter;
import mr.database.table.TableImporter;
import mr.price.list.DatabasePriceListTool;
import mr.ToolException;


public class PriceListsCommandInterpreter
{
	@Command
	private void create(String pathToDatabaseFile, String password, String priceListName, String pathToDataFile)
	{
		invokeAction(pathToDatabaseFile, password, tool -> {
			usingTableImporter(pathToDataFile, tableImporter -> {
				tool.createPriceList(priceListName, tableImporter);
			});
		});
	}
	
	private void invokeAction(String pathToDatabaseFile, String password, Consumer<DatabasePriceListTool> toolConsumer)
	{
		try
		{
			File databaseFile = new File(pathToDatabaseFile);
			DatabasePriceListTool priceListsTool = new DatabasePriceListTool(databaseFile, password);
			
			toolConsumer.accept(priceListsTool);
		}
		catch (FileNotFoundException exception)
		{
			throw new ToolException(exception);
		}
	}
	
	private void usingTableImporter(String pathToDataFile, Consumer<TableImporter> tableImporterConsumer)
	{
		File dataFile = new File(pathToDataFile);
		TableImporter tableImporter = new OpenCSVTableImporter(dataFile);
		
		tableImporterConsumer.accept(tableImporter);
	}
	
	@Command
	private void rename(String pathToDatabaseFile, String password, String oldName, String newName)
	{
		invokeAction(pathToDatabaseFile, password, tool -> {
			tool.renamePriceList(oldName, newName);
		});
	}
	
	@Command
	private void update(String pathToDatabaseFile, String password, String priceListName, String pathToDataFile)
	{
		invokeAction(pathToDatabaseFile, password, tool -> {
			usingTableImporter(pathToDataFile, tableImporter -> {
				tool.updatePriceList(priceListName, tableImporter);
			});
		});
	}
	
	@Command
	private void remove(String pathToDatabaseFile, String password, String priceListName)
	{
		invokeAction(pathToDatabaseFile, password, tool -> {
			tool.removePriceList(priceListName);
		});
	}
	
	@Command
	private void close(String pathToDatabaseFile, String password)
	{
		invokeAction(pathToDatabaseFile, password, tool -> {
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