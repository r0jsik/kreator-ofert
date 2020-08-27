package mr.database.table;


import java.util.function.Consumer;


public interface TableImporter
{
	void importColumnNames(Consumer<String[]> columnNamesConsumer);
	void forEach(Consumer<String[]> recordsConsumer);
}