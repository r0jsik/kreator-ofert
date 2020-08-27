package mr.price.list;


import org.apache.maven.plugins.annotations.Mojo;

import mr.database.table.TableImporter;


@Mojo(name = "createPriceList")
public class CreatePriceListMojo extends TableImporterMojo
{
	@Override
	public void invokeWithTableImporter(PriceListTool tool, String priceListName, TableImporter tableImporter)
	{
		tool.createPriceList(priceListName, tableImporter);
	}
}