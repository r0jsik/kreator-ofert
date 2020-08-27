package mr.database.table;


import mr.database.DatabaseActionException;


public class TableImportException extends DatabaseActionException
{
	public TableImportException(String message, Throwable cause)
	{
		super(message, cause);
	}
}