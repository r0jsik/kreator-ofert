package mr.output.excel.document;


public class DocumentCreationException extends RuntimeException
{
	public DocumentCreationException(String message)
	{
		super(message);
	}
	
	public DocumentCreationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}