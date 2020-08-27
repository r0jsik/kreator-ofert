package mr.database.table;


import mr.database.DatabaseException;


public class TableNotFoundException extends DatabaseException
{
	public TableNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
}