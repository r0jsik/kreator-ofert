package mr.database;


public class DatabaseActionException extends DatabaseException
{
	public DatabaseActionException(String message, Throwable cause)
	{
		super(message, cause);
	}
}