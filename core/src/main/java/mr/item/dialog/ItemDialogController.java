package mr.item.dialog;


import mr.item.Item;
import mr.item.view.ItemView;
import mr.price.view.PriceTypeView;


public interface ItemDialogController extends ItemView, PriceTypeView
{
	void show(Item item);
	void showOn(Item item);
}