package mr.database;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.function.Consumer;

import mr.database.table.*;


public class JDBCDatabase implements DecryptableDatabase
{
	private final Connection connection;
	private final Statement statement;
	
	public JDBCDatabase(File file) throws FileNotFoundException
	{
		if ( !file.exists())
		{
			throw new FileNotFoundException("Nie znaleziono bazy danych: " + file.getAbsolutePath());
		}
		
		try
		{
			connection = createConnection(file);
			statement = connection.createStatement();
		}
		catch (SQLException exception)
		{
			throw new DatabaseConnectionException("Nie można nawiązać połączenia z bazą danych: " + file.getAbsolutePath(), exception);
		}
	}
	
	private Connection createConnection(File databaseFile) throws SQLException
	{
		return DriverManager.getConnection("jdbc:sqlite:" + databaseFile.getAbsolutePath());
	}
	
	@Override
	public void decrypt(String password)
	{
		String query = String.format("PRAGMA key='%s'", password);
		
		try
		{
			statement.execute(query);
			statement.execute("SELECT * FROM sqlite_master");
		}
		catch (SQLException exception)
		{
			throw new DatabaseDecryptionException("Nie można odszyfrować bazy danych", exception);
		}
	}
	
	@Override
	public void withTable(String name, Consumer<Table> tableConsumer)
	{
		String query = String.format("SELECT * FROM '%s' LIMIT 0", name);
		
		try
		{
			statement.execute(query);
		}
		catch (SQLException exception)
		{
			throw new TableNotFoundException("Nie znaleziono tabeli: " + name, exception);
		}
		
		Table table = new JDBCTable(name, connection, statement);
		tableConsumer.accept(table);
	}
	
	@Override
	public void createTable(String name, String[] columnNames)
	{
		String query = String.format("CREATE TABLE '%s' ('%s' TEXT)", name, String.join("' TEXT, '", columnNames));
		
		try
		{
			statement.execute(query);
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można utworzyć tabeli: " + name, exception);
		}
	}
	
	@Override
	public void renameTable(String oldName, String newName)
	{
		String query = String.format("ALTER TABLE '%s' RENAME TO '%s'", oldName, newName);
		
		try
		{
			statement.execute(query);
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można zmienić nazwy tabeli", exception);
		}
	}
	
	@Override
	public void removeTable(String name)
	{
		String query = String.format("DROP TABLE '%s'", name);
		
		try
		{
			statement.execute(query);
		}
		catch (SQLException exception)
		{
			throw new DatabaseActionException("Nie można usunąć tabeli: " + name, exception);
		}
	}
	
	@Override
	public void importTable(String name, TableImporter tableImporter)
	{
		tableImporter.importColumnNames(columnNames -> {
			createTable(name, columnNames);
		});
		
		withTable(name, table -> {
			tableImporter.forEach(table::insert);
		});
	}
	
	@Override
	public void close() throws IOException
	{
		try
		{
			statement.execute("VACUUM");
			statement.close();
		}
		catch (SQLException exception)
		{
			throw new IOException("Nie można zamknąć bazy danych", exception);
		}
	}
}