package mr.database;


import java.io.Closeable;
import java.util.function.Consumer;

import mr.database.table.Table;
import mr.database.table.TableImporter;


public interface Database extends Closeable
{
	void withTable(String name, Consumer<Table> tableConsumer);
	void createTable(String name, String[] columns);
	void renameTable(String oldName, String newName);
	void removeTable(String name);
	void importTable(String name, TableImporter tableImporter);
}