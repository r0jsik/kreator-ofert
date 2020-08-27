package mr.database.table;


import java.sql.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import mr.database.DatabaseActionException;


public class JDBCTable implements Table
{
	private final String name;
	private final Connection connection;
	private final Statement statement;
	
	public JDBCTable(String name, Connection connection, Statement statement)
	{
		this.name = name;
		this.connection = connection;
		this.statement = statement;
	}
	
	@Override
	public void forEach(Consumer<String[]> entryConsumer)
	{
		String query = String.format("SELECT * FROM '%s'", name);
		
		try
		{
			ResultSet queryResult = statement.executeQuery(query);
			
			withSelection(queryResult, (metaData, columnCount) -> {
				consumeAllEntries(queryResult, columnCount, entryConsumer);
			});
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można załadować tabeli z bazy danych: " + name, exception);
		}
	}
	
	private void withSelection(ResultSet queryResult, SelectionConsumer selectionConsumer) throws SQLException
	{
		ResultSetMetaData metaData = queryResult.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		selectionConsumer.accept(metaData, columnCount);
	}
	
	private void consumeAllEntries(ResultSet queryResult, int columnCount, Consumer<String[]> entryConsumer) throws SQLException
	{
		while (queryResult.next())
		{
			consumeEntry(queryResult, columnCount, entryConsumer);
		}
	}
	
	private void consumeEntry(ResultSet queryResult, int columnCount, Consumer<String[]> entryConsumer) throws SQLException
	{
		String[] entry = loadEntry(queryResult, columnCount);
		entryConsumer.accept(entry);
	}
	
	private String[] loadEntry(ResultSet queryResult, int columnCount) throws SQLException
	{
		String[] record = new String[columnCount];
		
		for (int i = 0; i < columnCount; i++)
		{
			record[i] = queryResult.getString(i + 1);
		}
		
		return record;
	}
	
	@Override
	public void insert(String[] entry)
	{
		String placeholder = buildValuesPlaceholder(entry);
		String preparedQuery = String.format("INSERT INTO '%s' VALUES(%s)", name, placeholder);
		
		try
		{
			PreparedStatement preparedStatement = prepareStatement(preparedQuery, entry);
			preparedStatement.execute();
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można dodać rekordu do tabeli", exception);
		}
	}
	
	private String buildValuesPlaceholder(String[] entry)
	{
		StringBuilder placeholderBuilder = new StringBuilder();
		
		for (int i = 0; i < entry.length; i++)
		{
			placeholderBuilder.append('?');
			
			if (i != entry.length - 1)
			{
				placeholderBuilder.append(',');
			}
		}
		
		return placeholderBuilder.toString();
	}
	
	private PreparedStatement prepareStatement(String preparedQuery, String... values) throws SQLException
	{
		PreparedStatement preparedStatement = connection.prepareStatement(preparedQuery);
		
		for (int i = 0; i < values.length; i++)
		{
			preparedStatement.setString(i + 1, values[i]);
		}
		
		return preparedStatement;
	}
	
	@Override
	public void update(String entryID, String[] entry)
	{
		delete(entryID);
		insert(entry);
	}
	
	@Override
	public void delete(String entryID)
	{
		String query = String.format("DELETE FROM '%s' WHERE id = ?", name);
		
		try
		{
			PreparedStatement preparedStatement = prepareStatement(query, entryID);
			preparedStatement.execute();
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można usunąć podanego rekordu z tabeli", exception);
		}
	}
	
	@Override
	public void clear()
	{
		String query = String.format("DELETE FROM '%s'", name);
		
		try
		{
			statement.execute(query);
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można wyczyścić zawartośći tabeli", exception);
		}
	}
	
	@Override
	public void selectEntryAndColumnNames(String entryID, BiConsumer<String[], String[]> entryAndColumnNamesConsumer)
	{
		String query = String.format("SELECT * FROM '%s' WHERE id = ?", name);
		
		try
		{
			PreparedStatement preparedStatement = prepareStatement(query, entryID);
			ResultSet queryResult = preparedStatement.executeQuery();
			
			withSelection(queryResult, (metaData, columnCount) -> {
				consumeEntryAndColumnNames(queryResult, metaData, columnCount, entryAndColumnNamesConsumer);
			});
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można załadować nazw kolumn", exception);
		}
	}
	
	private void consumeEntryAndColumnNames(ResultSet queryResult, ResultSetMetaData metaData, int columnCount, BiConsumer<String[], String[]> entryAndColumnNamesConsumer) throws SQLException
	{
		String[] columnNames = loadColumnNames(metaData, columnCount);
		String[] entry = loadEntry(queryResult, columnCount);
		
		entryAndColumnNamesConsumer.accept(entry, columnNames);
	}
	
	private String[] loadColumnNames(ResultSetMetaData metaData, int columnCount) throws SQLException
	{
		String[] columnNames = new String[columnCount];
		
		for (int i = 0; i < columnCount; i++)
		{
			columnNames[i] = metaData.getColumnName(i + 1);
		}
		
		return columnNames;
	}
}