package mr.clients.list.dialog;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import mr.client.Client;
import mr.record.TableRecordsView;


public class StageClientsListDialogController implements ClientsListDialogController
{
	private final ClientsListDialogMediator mediator;
	private final TableRecordsView<Client> clientsView;
	
	@FXML
	private VBox root;
	
	@FXML
	private HBox selectedClientMenu;
	
	@FXML
	private TextField filterField;
	
	public StageClientsListDialogController(ClientsListDialogMediator mediator, TableRecordsView<Client> clientsView)
	{
		this.mediator = mediator;
		this.clientsView = clientsView;
	}
	
	@FXML
	private void initialize()
	{
		root.getChildren().add(clientsView);
		
		setUpClientsView();
		setUpFilterField();
		setUpMenuDisability();
	}
	
	private void setUpClientsView()
	{
		clientsView.setOnSelectRequest(mediator::modifyClient);
		clientsView.updateColumns();
	}
	
	private void setUpFilterField()
	{
		filterField.textProperty().addListener((property, oldValue, newValue) -> {
			mediator.filterClientsView(newValue);
		});
	}
	
	private void setUpMenuDisability()
	{
		selectedClientMenu.disableProperty().bind(clientsView.getSelectionModel().selectedItemProperty().isNull());
	}
	
	@FXML
	private void clearFilter()
	{
		filterField.clear();
		filterField.requestFocus();
	}
	
	@FXML
	private void createClient()
	{
		mediator.createClient();
	}
	
	@FXML
	private void modifyClient()
	{
		clientsView.invokeAction(mediator::modifyClient);
	}
	
	@FXML
	private void removeClient()
	{
		clientsView.invokeAction(mediator::removeClient);
	}
}