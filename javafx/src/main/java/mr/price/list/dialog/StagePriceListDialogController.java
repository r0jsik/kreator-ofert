package mr.price.list.dialog;


import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import mr.price.list.PriceList;
import mr.price.list.PriceListRecord;
import mr.record.TableRecordsView;


public class StagePriceListDialogController implements PriceListDialogController
{
	private final PriceListDialogMediator mediator;
	private final TableRecordsView<PriceListRecord> priceListView;
	
	@FXML
	private Pane root;
	
	@FXML
	private TextField articleIdentifierField;
	
	@FXML
	private TextField descriptionField;
	
	@FXML
	private ComboBox<PriceList> priceListChooser;
	
	public StagePriceListDialogController(PriceListDialogMediator mediator, TableRecordsView<PriceListRecord> priceListView)
	{
		this.mediator = mediator;
		this.priceListView = priceListView;
	}
	
	@FXML
	private void initialize()
	{
		root.getChildren().add(priceListView);
		root.setOnKeyPressed(this::handleKeyEvent);
		
		setUpFilterFields();
		setUpPriceListChooser();
		setUpPriceListView();
	}
	
	private void handleKeyEvent(KeyEvent event)
	{
		if (event.isAltDown() && event.getCode() == KeyCode.BACK_SPACE)
		{
			clearFilter();
		}
	}
	
	private void setUpFilterFields()
	{
		articleIdentifierField.setOnKeyReleased(this::handleKeyEventFromFilterField);
		descriptionField.setOnKeyReleased(this::handleKeyEventFromFilterField);
	}
	
	private void handleKeyEventFromFilterField(KeyEvent event)
	{
		if (event.getCode() == KeyCode.ENTER)
		{
			priceListView.focus();
		}
		else
		{
			updatePriceListFilter();
		}
	}
	
	private void updatePriceListFilter()
	{
		String artID = articleIdentifierField.getText();
		String description = descriptionField.getText();
		
		mediator.filterPriceList(artID, description);
	}
	
	private void setUpPriceListChooser()
	{
		priceListChooser.getSelectionModel().selectedItemProperty().addListener((property, oldValue, newValue) -> {
			mediator.selectPriceList(newValue);
		});
	}
	
	private void setUpPriceListView()
	{
		priceListView.setOnSelectRequest(mediator::collectRecord);
		priceListView.updateColumns();
	}
	
	@Override
	public void setPriceLists(List<PriceList> priceLists)
	{
		priceListChooser.setItems(FXCollections.observableArrayList(priceLists));
	}
	
	@Override
	public void selectFirstPriceList()
	{
		priceListChooser.getSelectionModel().selectFirst();
	}
	
	@Override
	public void clearFilter()
	{
		articleIdentifierField.clear();
		articleIdentifierField.requestFocus();
		descriptionField.clear();
	}
}