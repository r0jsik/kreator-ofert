package mr.price.list.dialog;


import java.util.List;

import mr.price.list.PriceList;


public interface PriceListDialogController
{
	void setPriceLists(List<PriceList> priceLists);
	void selectFirstPriceList();
	void clearFilter();
}