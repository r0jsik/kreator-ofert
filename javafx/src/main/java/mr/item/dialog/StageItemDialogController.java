package mr.item.dialog;


import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import mr.item.Item;
import mr.price.Price;
import mr.price.discount.ComboBoxDiscountChooser;
import mr.price.discount.DiscountablePrice;
import mr.settings.Format;


public class StageItemDialogController implements ItemDialogController
{
	private static final int priceTypeViewColumnIndex = 1;
	private static final int priceTypeViewRowIndex = 1;
	
	@FXML
	private GridPane form;
	
	@FXML
	private TextArea descriptionField;
	
	@FXML
	private Label artIdLabel;
	
	@FXML
	private Label unitPriceLabel;
	
	@FXML
	private TextField itemCountField;
	
	@FXML
	private Label totalPriceLabel;
	
	@FXML
	private TextArea commentField;
	
	@FXML
	private void initialize()
	{
		itemCountField.textProperty().addListener((property, oldValue, newValue) -> {
			itemCountField.setText(newValue.replaceAll("[\\D]", ""));
		});
	}
	
	@Override
	public void show(Item item)
	{
		Price price = item.getPrice();
		price.showType(this);
		
		item.showOn(this);
		
		setUpItemCountField(price);
	}
	
	private void setUpItemCountField(Price price)
	{
		itemCountField.setOnKeyReleased(event -> {
			updateItemCount(price);
		});
		
		Platform.runLater(() -> {
			itemCountField.requestFocus();
			itemCountField.selectAll();
		});
	}
	
	private void updateItemCount(Price price)
	{
		int itemCount = getItemCount();
		double totalPrice = itemCount * price.getUnitPrice();
		
		price.setItemCount(itemCount);
		
		showTotalPrice(totalPrice);
	}
	
	private int getItemCount()
	{
		try
		{
			return Integer.parseInt(itemCountField.getText());
		}
		catch (NumberFormatException exception)
		{
			return 0;
		}
	}
	
	@Override
	public void showDescription(String value)
	{
		descriptionField.setText(value);
	}
	
	@Override
	public void showArticleIdentifier(String value)
	{
		artIdLabel.setText(value);
	}
	
	@Override
	public void showComment(String value)
	{
		commentField.setText(value);
	}
	
	@Override
	public void showDiscountChooser(DiscountablePrice price)
	{
		showPriceTypeView(new ComboBoxDiscountChooser(price, this));
	}
	
	private void showPriceTypeView(Node node)
	{
		List<Node> children = form.getChildren();
		
		for (Node child : children)
		{
			if (isOnPriceTypeViewPosition(child))
			{
				children.remove(child);
				break;
			}
		}
		
		form.add(node, priceTypeViewColumnIndex, priceTypeViewRowIndex);
	}
	
	private boolean isOnPriceTypeViewPosition(Node node)
	{
		if (GridPane.getRowIndex(node) == priceTypeViewRowIndex)
		{
			return GridPane.getColumnIndex(node) == priceTypeViewColumnIndex;
		}
		
		return false;
	}
	
	@Override
	public void showLabel(String text)
	{
		showPriceTypeView(new Label(text));
	}
	
	@Override
	public void showUnitPrice(double value)
	{
		unitPriceLabel.setText(Format.asPrice(value));
	}
	
	@Override
	public void showItemCount(int value)
	{
		itemCountField.setText(String.valueOf(value));
	}
	
	@Override
	public void showTotalPrice(double value)
	{
		totalPriceLabel.setText(Format.asPrice(value));
	}
	
	@Override
	public void showOn(Item item)
	{
		item.setState(artIdLabel.getText(), descriptionField.getText(), commentField.getText());
	}
}