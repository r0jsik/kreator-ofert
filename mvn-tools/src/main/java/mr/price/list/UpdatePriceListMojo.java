package mr.price.list;


import org.apache.maven.plugins.annotations.Mojo;

import mr.database.table.TableImporter;


@Mojo(name = "updatePriceList")
public class UpdatePriceListMojo extends TableImporterMojo
{
	@Override
	public void invokeWithTableImporter(PriceListTool tool, String priceListName, TableImporter tableImporter)
	{
		tool.updatePriceList(priceListName, tableImporter);
	}
}