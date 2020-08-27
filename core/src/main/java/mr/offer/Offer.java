package mr.offer;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import mr.item.Item;
import mr.record.RecordsView;


public class Offer
{
	private static Offer instance;
	
	private List<Item> items;
	private List<OfferRecord> records;
	private RecordsView<OfferRecord> view;
	
	private Offer()
	{
		items = new LinkedList<>();
		records = new LinkedList<>();
	}
	
	public void insert(Item item)
	{
		OfferRecord record = new OfferRecord();
		item.showOn(record);
		
		items.add(item);
		records.add(record);
		view.show(record);
	}
	
	public void remove(OfferRecord record)
	{
		int index = records.indexOf(record);
		
		items.remove(index);
		records.remove(index);
		view.hide(record);
	}
	
	public void clear()
	{
		items.clear();
		records.clear();
		view.hideAll();
	}
	
	public List<Item> getItems()
	{
		return Collections.unmodifiableList(items);
	}
	
	public Item getItem(OfferRecord record)
	{
		return items.get(records.indexOf(record));
	}
	
	public void setView(RecordsView<OfferRecord> view)
	{
		this.view = view;
	}
	
	public static Offer getInstance()
	{
		if (instance == null)
		{
			instance = new Offer();
		}
		
		return instance;
	}
}