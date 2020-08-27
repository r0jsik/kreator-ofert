package mr.price.discount;


import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;

import mr.price.view.PriceView;


public class ComboBoxDiscountChooser extends ComboBox<Discount>
{
	private final PriceView priceView;
	private final DiscountablePrice price;
	
	public ComboBoxDiscountChooser(DiscountablePrice price, PriceView priceView)
	{
		this.priceView = priceView;
		this.price = price;
		
		showDiscounts();
		setUpSelectionModel();
	}
	
	private void showDiscounts()
	{
		List<Discount> discounts = price.getDiscounts();
		ObservableList<Discount> items = FXCollections.observableArrayList(discounts);
		
		setItems(items);
	}
	
	private void setUpSelectionModel()
	{
		Discount discount = price.getCurrentDiscount();
		
		SelectionModel<Discount> selectionModel = getSelectionModel();
		selectionModel.select(discount);
		
		selectionModel.selectedItemProperty().addListener((property, oldValue, newValue) -> {
			price.setDiscount(newValue);
			price.showOn(priceView);
		});
	}
}