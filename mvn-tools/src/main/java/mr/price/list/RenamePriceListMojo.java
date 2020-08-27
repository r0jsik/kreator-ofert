package mr.price.list;


import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


@Mojo(name = "renamePriceList")
public class RenamePriceListMojo extends PriceListMojo
{
	@Parameter(alias = "desiredName", required = true)
	private String desiredName;
	
	@Override
	public void invokeWithPriceListName(PriceListTool tool, String priceListName)
	{
		tool.renamePriceList(priceListName, desiredName);
	}
}