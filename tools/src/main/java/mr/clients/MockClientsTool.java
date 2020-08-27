package mr.clients;


import java.io.File;
import java.io.FileNotFoundException;


public class MockClientsTool implements ClientsTool
{
	public MockClientsTool(File databaseFile) throws FileNotFoundException
	{
		if ( !databaseFile.exists())
		{
			throw new FileNotFoundException("Nie znaleziono pliku: " + databaseFile.getAbsolutePath());
		}
	}
	
	@Override
	public void encrypt(String password)
	{
		System.out.println("Szyfowanie informacji o klientach");
	}
	
	@Override
	public void clear()
	{
		System.out.println("Usuwanie informacji o klientach");
	}
	
	@Override
	public void close()
	{
		System.out.println("Zamykanie narzędzia");
	}
}