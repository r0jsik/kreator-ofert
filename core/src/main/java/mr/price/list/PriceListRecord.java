package mr.price.list;


import mr.item.Item;
import mr.price.Price;
import mr.record.Record;


public class PriceListRecord implements Record
{
	private final String artID;
	private final String description;
	
	public PriceListRecord(String artID, String description)
	{
		this.artID = artID;
		this.description = description;
	}
	
	public Item createItem(Price price)
	{
		return new Item(artID, description, price);
	}
	
	@Override
	public String getField(int index)
	{
		switch (index)
		{
			case 0:
				return artID;
			
			case 1:
				return description;
			
			default:
				throw new IndexOutOfBoundsException("Nieprawidłowy indeks pola");
		}
	}
	
	public String getArtID()
	{
		return artID;
	}
	
	public String getDescription()
	{
		return description;
	}
}