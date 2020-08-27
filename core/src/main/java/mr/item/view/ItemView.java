package mr.item.view;


import mr.price.view.PriceView;


public interface ItemView extends PriceView
{
	void showDescription(String value);
	void showArticleIdentifier(String value);
	void showComment(String value);
}