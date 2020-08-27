package mr.output.text;


import mr.item.view.ItemView;


public class TextItemView implements ItemView
{
	private final String[] fields;
	
	TextItemView()
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
		fields[2] = String.format("%.2f", value);
	}
	
	@Override
	public void showItemCount(int value)
	{
		fields[3] = String.valueOf(value);
	}
	
	@Override
	public void showTotalPrice(double value)
	{
		fields[4] = "";
	}
	
	@Override
	public void showComment(String value)
	{
		fields[5] = value;
	}
	
	@Override
	public String toString()
	{
		return String.join("\t",  fields);
	}
}