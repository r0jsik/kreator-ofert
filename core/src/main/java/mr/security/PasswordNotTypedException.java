package mr.security;


public class PasswordNotTypedException extends RuntimeException
{
	public PasswordNotTypedException(String message)
	{
		super(message);
	}
}