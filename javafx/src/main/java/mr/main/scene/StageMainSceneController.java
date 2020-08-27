package mr.main.scene;


import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import mr.offer.OfferRecord;
import mr.record.TableRecordsView;


public class StageMainSceneController implements MainSceneController
{
	private final MainSceneMediator mediator;
	private final TableRecordsView<OfferRecord> offerView;
	
	@FXML
	private Pane root;
	
	public StageMainSceneController(MainSceneMediator mediator, TableRecordsView<OfferRecord> offerView)
	{
		this.mediator = mediator;
		this.offerView = offerView;
	}
	
	@FXML
	private void initialize()
	{
		root.getChildren().add(offerView);
		root.setOnKeyPressed(this::handleKeyEvent);
		
		setUpOfferView();
	}
	
	private void handleKeyEvent(KeyEvent event)
	{
		if (event.isAltDown())
		{
			switch (event.getCode())
			{
				case D:
					showPriceListDialog();
					break;
				
				case E:
					showExcelOutput();
					break;
				
				case T:
					showTextOutput();
					break;
				
				case BACK_SPACE:
					clearOffer();
					break;
				
				case K:
					showClientsListDialog();
					break;
				
				case U:
					showSettingsDialog();
					break;
			}
		}
	}
	
	@FXML
	private void showPriceListDialog()
	{
		mediator.showPriceListDialog();
	}
	
	@FXML
	private void showTextOutput()
	{
		mediator.showTextOutput();
	}
	
	@FXML
	private void showExcelOutput()
	{
		mediator.showExcelOutput();
	}
	
	@FXML
	private void clearOffer()
	{
		mediator.clearOffer();
	}
	
	@FXML
	private void showClientsListDialog()
	{
		mediator.showClientsListDialog();
	}
	
	@FXML
	private void showSettingsDialog()
	{
		mediator.showSettingsDialog();
	}
	
	private void setUpOfferView()
	{
		offerView.setOnSelectRequest(mediator::modifyRecord);
		offerView.setOnRemoveRequest(mediator::removeRecord);
		offerView.updateColumns();
	}
}