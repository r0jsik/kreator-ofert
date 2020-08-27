package mr.price.list.dialog;


import mr.item.ItemCollector;
import mr.price.list.PriceList;
import mr.price.list.PriceListRecord;
import mr.price.list.filter.condition.*;
import mr.price.list.loader.PriceListLoader;
import mr.record.filter.FilteredRecordsView;
import mr.record.filter.condition.AndFilterCondition;
import mr.record.filter.condition.FilterCondition;
import mr.record.filter.condition.LimitFilterCondition;


public class PriceListDialogMediator
{
	private final PriceListDialogController controller;
	private final PriceListLoader priceListLoader;
	private final ItemCollector itemCollector;
	private final FilteredRecordsView<PriceListRecord> recordsView;
	
	// Time coupling - there is no better solution
	private PriceList currentPriceList;
	
	public PriceListDialogMediator(PriceListDialogController controller, PriceListLoader priceListLoader, ItemCollector itemCollector, FilteredRecordsView<PriceListRecord> recordsView)
	{
		this.controller = controller;
		this.priceListLoader = priceListLoader;
		this.itemCollector = itemCollector;
		this.recordsView = recordsView;
	}
	
	public void selectPriceList(PriceList priceList)
	{
		priceListLoader.loadRecords(priceList, records -> {
			recordsView.show(records);
			recordsView.clearFilter();
			
			controller.clearFilter();
			
			currentPriceList = priceList;
		});
	}
	
	public void filterPriceList(String artID, String description)
	{
		String[] keywords = description.split(" ");
		
		FilterCondition<PriceListRecord> filterCondition = new AndFilterCondition<>(
			new ArticleIdentifierStartsWithPrefixCondition(artID),
			new DescriptionContainsAllKeywordsCondition(keywords),
			new LimitFilterCondition<>(1000)
		);
		
		recordsView.filter(filterCondition);
	}
	
	public void collectRecord(PriceListRecord record)
	{
		String artID = record.getArtID();
		
		priceListLoader.loadPrice(currentPriceList, artID, price -> {
			itemCollector.collect(record.createItem(price));
		});
	}
}