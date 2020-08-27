package mr.database.table;


import java.util.function.BiConsumer;
import java.util.function.Consumer;


public interface Table
{
	void forEach(Consumer<String[]> entryConsumer);
	void selectEntryAndColumnNames(String entryID, BiConsumer<String[], String[]> entryAndColumnNamesConsumer);
	void insert(String[] entry);
	void update(String entryID, String[] entry);
	void delete(String entryID);
	void clear();
}