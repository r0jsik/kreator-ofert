package mr.item;


import mr.item.view.ItemView;
import mr.price.Price;


public class Item
{
	private final Price price;
	
	private String artID;
	private String description;
	private String comment;
	
	public Item(String artID, String description, Price price)
	{
		this.price = price;
		this.setState(artID, description, "");
	}
	
	public void setState(String artID, String description, String comment)
	{
		this.artID = artID;
		this.description = description;
		this.comment = comment;
	}
	
	public void showOn(ItemView view)
	{
		view.showDescription(description);
		view.showArticleIdentifier(artID);
		view.showComment(comment);
		
		price.showOn(view);
	}
	
	public Price getPrice()
	{
		return price;
	}
}