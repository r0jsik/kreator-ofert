package mr.price.list;


import java.io.File;

import org.apache.maven.plugins.annotations.Parameter;

import mr.database.table.OpenCSVTableImporter;
import mr.database.table.TableImporter;


public abstract class TableImporterMojo extends PriceListMojo
{
	@Parameter(alias = "from", required = true)
	private String pathToDataFile;
	
	@Override
	public void invokeWithPriceListName(PriceListTool tool, String priceListName)
	{
		File dataFile = new File(pathToDataFile);
		TableImporter tableImporter = new OpenCSVTableImporter(dataFile);
		
		invokeWithTableImporter(tool, priceListName, tableImporter);
	}
	
	public abstract void invokeWithTableImporter(PriceListTool tool, String priceListName, TableImporter tableImporter);
}