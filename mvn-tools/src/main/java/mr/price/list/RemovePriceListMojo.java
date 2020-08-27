package mr.price.list;


import org.apache.maven.plugins.annotations.Mojo;


@Mojo(name = "removePriceList")
public class RemovePriceListMojo extends PriceListMojo
{
	@Override
	public void invokeWithPriceListName(PriceListTool tool, String priceListName)
	{
		tool.removePriceList(priceListName);
	}
}