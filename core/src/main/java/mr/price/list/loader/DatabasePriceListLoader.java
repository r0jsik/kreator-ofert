package mr.price.list.loader;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import mr.database.Database;
import mr.database.DatabaseException;
import mr.database.table.Table;
import mr.price.Price;
import mr.price.list.PriceList;
import mr.price.list.PriceListRecord;
import mr.settings.preferences.Preferences;


public class DatabasePriceListLoader implements PriceListLoader
{
	private final Database database;
	private final Preferences preferences;
	
	public DatabasePriceListLoader(Database database, Preferences preferences)
	{
		this.database = database;
		this.preferences = preferences;
	}
	
	@Override
	public void loadRecords(PriceList priceList, Consumer<List<PriceListRecord>> recordsConsumer)
	{
		String tableName = priceList.getName();
		
		try
		{
			database.withTable(tableName, table -> {
				consumeAllEntriesAsPriceListRecords(table, recordsConsumer);
			});
		}
		catch (DatabaseException exception)
		{
			throw new PriceListLoadException("Błąd odczytu cennika '" + tableName + "' z bazy danych", exception);
		}
	}
	
	private void consumeAllEntriesAsPriceListRecords(Table table, Consumer<List<PriceListRecord>> recordsConsumer)
	{
		List<PriceListRecord> records = new LinkedList<>();
		
		table.forEach(entry -> {
			PriceListRecord record = new PriceListRecord(entry[0], entry[1]);
			records.add(record);
		});
		
		recordsConsumer.accept(records);
	}
	
	@Override
	public void loadPrice(PriceList priceList, String artID, Consumer<Price> priceConsumer)
	{
		String tableName = priceList.getName();
		
		database.withTable(tableName, table -> {
			table.selectEntryAndColumnNames(artID, (entry, columnNames) -> {
				consumePrice(priceList, entry, columnNames, priceConsumer);
			});
		});
	}
	
	private void consumePrice(PriceList priceList, String[] entry, String[] columnNames, Consumer<Price> priceConsumer)
	{
		double[] values = convertToDoubles(entry);
		
		convertCurrency(values);
		
		Price price = priceList.createPrice(columnNames, values);
		priceConsumer.accept(price);
	}
	
	private void convertCurrency(double[] values)
	{
		double exchangeRate = preferences.getDouble("price-list", "exchange-rate");
		
		for (int i = 0; i < values.length; i++)
		{
			values[i] = values[i] * exchangeRate;
		}
	}
	
	private double[] convertToDoubles(String[] fields)
	{
		double[] values = new double[fields.length];
		
		for (int i = 0; i < values.length; i++)
		{
			values[i] = convertToDouble(fields[i]);
		}
		
		return values;
	}
	
	private double convertToDouble(String string)
	{
		try
		{
			return Double.parseDouble(string);
		}
		catch (NumberFormatException exception)
		{
			return 0;
		}
	}
}