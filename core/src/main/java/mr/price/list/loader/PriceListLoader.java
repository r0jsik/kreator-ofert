package mr.price.list.loader;


import java.util.List;
import java.util.function.Consumer;

import mr.price.Price;
import mr.price.list.PriceList;
import mr.price.list.PriceListRecord;


public interface PriceListLoader
{
	void loadRecords(PriceList priceList, Consumer<List<PriceListRecord>> recordsConsumer);
	void loadPrice(PriceList priceList, String artID, Consumer<Price> priceConsumer);
}