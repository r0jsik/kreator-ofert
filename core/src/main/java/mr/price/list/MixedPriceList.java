package mr.price.list;


import mr.price.Price;
import mr.price.simple.SimplePrice;


public class MixedPriceList extends DiscountablePriceList
{
	private static final int indexOfIsSimpleFlag = 2;
	private static final int indexOfBasePrice = 3;
	
	public MixedPriceList(String name, int indexOfDefaultDiscount)
	{
		super(name, indexOfDefaultDiscount, indexOfBasePrice);
	}
	
	@Override
	public Price createPrice(String[] labels, double[] values)
	{
		if (values[indexOfIsSimpleFlag] == 1)
		{
			return new SimplePrice(values[indexOfBasePrice], labels[indexOfBasePrice]);
		}
		
		return super.createPrice(labels, values);
	}
}