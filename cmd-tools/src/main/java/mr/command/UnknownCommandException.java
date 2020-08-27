package mr.command;


public class UnknownCommandException extends RuntimeException
{
	public UnknownCommandException(String command)
	{
		super(command);
	}
}