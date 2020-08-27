package mr.price.list.loader;


public class PriceListLoadException extends RuntimeException
{
	PriceListLoadException(String message, Throwable cause)
	{
		super(message, cause);
	}
}