package mr.price.discount;


import java.util.List;

import mr.memento.NumericMemento;
import mr.price.Price;
import mr.price.view.PriceTypeView;
import mr.price.view.PriceView;


public class DiscountablePrice implements Price
{
	private final List<Discount> discounts;
	
	private Discount currentDiscount;
	private int itemCount;
	
	public DiscountablePrice(List<Discount> discounts, int indexOfDefaultDiscount)
	{
		this.discounts = discounts;
		this.currentDiscount = discounts.get(indexOfDefaultDiscount);
		this.itemCount = 1;
	}
	
	@Override
	public void showOn(PriceView view)
	{
		double unitPrice = currentDiscount.getPrice();
		
		view.showItemCount(itemCount);
		view.showUnitPrice(unitPrice);
		view.showTotalPrice(itemCount * unitPrice);
	}
	
	@Override
	public void showType(PriceTypeView view)
	{
		view.showDiscountChooser(this);
	}
	
	@Override
	public double getUnitPrice()
	{
		return currentDiscount.getPrice();
	}
	
	@Override
	public void setItemCount(int count)
	{
		itemCount = count;
	}
	
	public void setDiscount(Discount discount)
	{
		currentDiscount = discount;
	}
	
	@Override
	public NumericMemento fetchMemento()
	{
		return new NumericMemento(itemCount, discounts.indexOf(currentDiscount));
	}
	
	@Override
	public void applyMemento(NumericMemento memento)
	{
		itemCount = memento.get(0);
		currentDiscount = discounts.get(memento.get(1));
	}
	
	public Discount getCurrentDiscount()
	{
		return currentDiscount;
	}
	
	public List<Discount> getDiscounts()
	{
		return discounts;
	}
}