package mr.price.list;


import mr.price.Price;
import mr.price.simple.SimplePrice;


public class SimplePriceList extends PriceList
{
	private static final int indexOfPrice = 2;
	
	public SimplePriceList(String name)
	{
		super(name);
	}
	
	@Override
	public Price createPrice(String[] labels, double[] values)
	{
		return new SimplePrice(values[indexOfPrice], labels[indexOfPrice]);
	}
}