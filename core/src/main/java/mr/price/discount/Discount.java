package mr.price.discount;


public class Discount
{
	private final String name;
	private final double price;
	
	public Discount(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}