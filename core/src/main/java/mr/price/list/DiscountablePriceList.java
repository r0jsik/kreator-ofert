package mr.price.list;


import java.util.ArrayList;
import java.util.List;

import mr.price.Price;
import mr.price.discount.Discount;
import mr.price.discount.DiscountablePrice;


public class DiscountablePriceList extends PriceList
{
	private static final int defaultIndexOfBasePrice = 2;
	
	private final int indexOfDefaultDiscount;
	private final int indexOfBasePrice;
	
	public DiscountablePriceList(String name, int indexOfDefaultDiscount)
	{
		this(name, indexOfDefaultDiscount, defaultIndexOfBasePrice);
	}
	
	public DiscountablePriceList(String name, int indexOfDefaultDiscount, int indexOfBasePrice)
	{
		super(name);
		
		this.indexOfDefaultDiscount = indexOfDefaultDiscount;
		this.indexOfBasePrice = indexOfBasePrice;
	}
	
	@Override
	public Price createPrice(String[] labels, double[] values)
	{
		return new DiscountablePrice(loadDiscounts(labels, values), indexOfDefaultDiscount);
	}
	
	private List<Discount> loadDiscounts(String[] labels, double[] values)
	{
		List<Discount> discounts = new ArrayList<>();
		
		for (int i = indexOfBasePrice; i < values.length; i++)
		{
			Discount discount = new Discount(labels[i], values[i]);
			discounts.add(discount);
		}
		
		return discounts;
	}
}