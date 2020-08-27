package mr.output.excel.document;


import java.io.File;
import java.io.IOException;


public class ApacheDocumentFactory implements DocumentFactory
{
	private final File templatesFile;
	private final String templateName;
	
	public ApacheDocumentFactory(File templatesFile, String templateName)
	{
		this.templatesFile = templatesFile;
		this.templateName = templateName;
	}
	
	@Override
	public Document create()
	{
		try
		{
			return new ApacheDocument(templatesFile, templateName);
		}
		catch (IOException exception)
		{
			throw new DocumentCreationException("Nie można odczytać pliku z szablonami", exception);
		}
	}
}