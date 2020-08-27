package mr.price.list;


import mr.price.Price;


public abstract class PriceList
{
	private final String name;
	
	public PriceList(String name)
	{
		this.name = name;
	}
	
	public abstract Price createPrice(String[] labels, double[] values);
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}