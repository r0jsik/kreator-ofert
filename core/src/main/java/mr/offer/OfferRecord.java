package mr.offer;


import mr.item.view.ItemView;
import mr.record.Record;
import mr.settings.Format;


public class OfferRecord implements Record, ItemView
{
	private final String[] fields;
	
	OfferRecord()
	{
		fields = new String[6];
	}
	
	@Override
	public void showDescription(String value)
	{
		fields[0] = value;
	}
	
	@Override
	public void showArticleIdentifier(String value)
	{
		fields[1] = value;
	}
	
	@Override
	public void showUnitPrice(double value)
	{
		fields[2] = Format.asPrice(value);
	}
	
	@Override
	public void showItemCount(int value)
	{
		fields[3] = String.valueOf(value);
	}
	
	@Override
	public void showTotalPrice(double value)
	{
		fields[4] = Format.asPrice(value);
	}
	
	@Override
	public void showComment(String value)
	{
		fields[5] = value;
	}
	
	@Override
	public String getField(int index)
	{
		return fields[index];
	}
}