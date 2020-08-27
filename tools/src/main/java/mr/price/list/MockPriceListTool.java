package mr.price.list;


import mr.database.table.TableImporter;


public class MockPriceListTool implements PriceListTool
{
	@Override
	public void createPriceList(String name, TableImporter tableImporter)
	{
		System.out.println("Tworzenie cennika: " + name);
		
		tableImporter.importColumnNames(columnNames -> {
			System.out.println("\tNazwy kolumn: " + String.join(", ", columnNames));
		});
	}
	
	@Override
	public void renamePriceList(String oldName, String newName)
	{
		System.out.println("Zmienianie nazwy cennika '" + oldName + "' na '" + newName + "'");
	}
	
	@Override
	public void updatePriceList(String name, TableImporter tableImporter)
	{
		System.out.println("Aktualizowanie cennika" + name);
	}
	
	@Override
	public void removePriceList(String name)
	{
		System.out.println("Usuwanie cennika: " + name);
	}
	
	@Override
	public void close()
	{
		System.out.println("Zamykanie narzędzia");
	}
}