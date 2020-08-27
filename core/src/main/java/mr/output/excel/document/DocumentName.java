package mr.output.excel.document;


import java.time.LocalDate;

import mr.output.excel.form.InvalidFormContentException;
import mr.settings.Format;


public class DocumentName
{
	private final String documentName;
	
	private DocumentName(String documentName)
	{
		this.documentName = documentName;
	}
	
	public static DocumentName from(String reportType, String identifier, LocalDate date)
	{
		if (identifier == null || identifier.equals(""))
		{
			throw new InvalidFormContentException("Nie podano identyfikatora");
		}
		
		String dateString = Format.asDate(date);
		String documentName = Format.asDocumentName(reportType, identifier, dateString);
		
		return new DocumentName(documentName);
	}
	
	@Override
	public String toString()
	{
		return documentName;
	}
}