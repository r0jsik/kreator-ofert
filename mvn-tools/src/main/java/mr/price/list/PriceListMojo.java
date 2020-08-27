package mr.price.list;


import java.io.File;
import java.io.FileNotFoundException;

import org.apache.maven.plugins.annotations.Parameter;

import mr.DatabaseMojo;


public abstract class PriceListMojo extends DatabaseMojo<PriceListTool>
{
	@Parameter(alias = "password", required = true)
	private String password;
	
	@Parameter(alias = "name", required = true)
	private String priceListName;
	
	@Override
	public PriceListTool createTool(File databaseFile) throws FileNotFoundException
	{
		return new DatabasePriceListTool(databaseFile, password);
	}
	
	@Override
	public void invokeOnTool(PriceListTool tool)
	{
		invokeWithPriceListName(tool, priceListName);
	}
	
	public abstract void invokeWithPriceListName(PriceListTool tool, String priceListName);
}