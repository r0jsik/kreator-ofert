package mr.price;


import mr.memento.MementoOriginator;
import mr.memento.NumericMemento;
import mr.price.view.PriceTypeView;
import mr.price.view.PriceView;


public interface Price extends MementoOriginator<NumericMemento>
{
	void showOn(PriceView view);
	void showType(PriceTypeView view);
	double getUnitPrice();
	void setItemCount(int count);
}