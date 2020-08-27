package mr.database.table;


import java.io.*;
import java.util.function.Consumer;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;


public class OpenCSVTableImporter implements TableImporter
{
	private final File file;
	
	public OpenCSVTableImporter(File file)
	{
		this.file = file;
	}
	
	@Override
	public void importColumnNames(Consumer<String[]> columnNamesConsumer)
	{
		try
		{
			CSVReader reader = openReader();
			String[] columnNames = reader.readNext();
			columnNamesConsumer.accept(columnNames);
			
			reader.close();
		}
		catch (IOException | CsvValidationException exception)
		{
			throw new TableImportException("Nie można zaimportować listy kolumn z pliku: " + file.getAbsolutePath(), exception);
		}
	}
	
	private CSVReader openReader() throws FileNotFoundException
	{
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		CSVParserBuilder csvParserBuilder = new CSVParserBuilder();
		csvParserBuilder.withSeparator(';');
		
		CSVParser parser = csvParserBuilder.build();
		CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(bufferedReader);
		csvReaderBuilder.withCSVParser(parser);
		
		return csvReaderBuilder.build();
	}
	
	@Override
	public void forEach(Consumer<String[]> recordsConsumer)
	{
		try
		{
			CSVReader reader = openReader();
			
			reader.readNextSilently();
			reader.forEach(recordsConsumer);
			
			reader.close();
		}
		catch (IOException exception)
		{
			throw new TableImportException("Nie można zaimportować rekordów z pliku: " + file.getAbsolutePath(), exception);
		}
	}
}