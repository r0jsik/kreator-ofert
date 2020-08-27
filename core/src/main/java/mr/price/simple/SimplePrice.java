package mr.price.simple;


import mr.memento.NumericMemento;
import mr.price.Price;
import mr.price.view.PriceTypeView;
import mr.price.view.PriceView;


public class SimplePrice implements Price
{
	private final double unitPrice;
	
	private String priceLabelText;
	private int itemCount;
	
	public SimplePrice(double unitPrice, String priceLabelText)
	{
		this.unitPrice = unitPrice;
		this.priceLabelText = priceLabelText;
		this.itemCount = 1;
	}
	
	@Override
	public void showOn(PriceView view)
	{
		view.showItemCount(itemCount);
		view.showUnitPrice(unitPrice);
		view.showTotalPrice(itemCount * unitPrice);
	}
	
	@Override
	public void showType(PriceTypeView view)
	{
		view.showLabel(priceLabelText);
	}
	
	@Override
	public double getUnitPrice()
	{
		return unitPrice;
	}
	
	@Override
	public void setItemCount(int count)
	{
		itemCount = count;
	}
	
	@Override
	public NumericMemento fetchMemento()
	{
		return new NumericMemento(itemCount);
	}
	
	@Override
	public void applyMemento(NumericMemento memento)
	{
		itemCount = memento.get(0);
	}
}