package mr.price.list.loader;


import java.util.*;
import java.util.function.Consumer;

import mr.price.Price;
import mr.price.list.PriceList;
import mr.price.list.PriceListRecord;


public class CachedPriceListLoader implements PriceListLoader
{
	private final PriceListLoader loader;
	private final Map<PriceList, List<PriceListRecord>> cache;
	
	public CachedPriceListLoader(PriceListLoader loader)
	{
		this.loader = loader;
		this.cache = new HashMap<>();
	}
	
	@Override
	public void loadRecords(PriceList priceList, Consumer<List<PriceListRecord>> recordsConsumer)
	{
		if ( !cache.containsKey(priceList))
		{
			loader.loadRecords(priceList, records -> cache.put(priceList, records));
		}
		
		recordsConsumer.accept(cache.get(priceList));
	}
	
	@Override
	public void loadPrice(PriceList priceList, String artID, Consumer<Price> priceConsumer)
	{
		loader.loadPrice(priceList, artID, priceConsumer);
	}
}