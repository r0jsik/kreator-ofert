package mr.price.list;


import java.io.Closeable;

import mr.database.table.TableImporter;


public interface PriceListTool extends Closeable
{
	void createPriceList(String name, TableImporter tableImporter);
	void renamePriceList(String oldName, String newName);
	void updatePriceList(String name, TableImporter tableImporter);
	void removePriceList(String name);
}