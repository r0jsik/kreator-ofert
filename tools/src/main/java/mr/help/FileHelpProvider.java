package mr.help;


import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;


public class FileHelpProvider implements HelpProvider
{
	private final String fileName;
	
	public FileHelpProvider(String fileName)
	{
		this.fileName = fileName;
	}
	
	@Override
	public void provide(Consumer<String> helpSentenceConsumer)
	{
		Scanner scanner = openHelpFileScanner();
		
		while (scanner.hasNext())
		{
			helpSentenceConsumer.accept(scanner.nextLine());
		}
		
		scanner.close();
	}
	
	private Scanner openHelpFileScanner()
	{
		InputStream inputStream = FileHelpProvider.class.getResourceAsStream(fileName);
		Scanner scanner = new Scanner(inputStream);
		
		return scanner;
	}
}