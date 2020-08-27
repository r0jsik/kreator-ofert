package mr.price.view;


import mr.price.discount.DiscountablePrice;


public interface PriceTypeView
{
	void showDiscountChooser(DiscountablePrice price);
	void showLabel(String text);
}