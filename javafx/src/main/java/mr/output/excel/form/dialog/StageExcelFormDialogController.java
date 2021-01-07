package mr.output.excel.form.dialog;


import java.time.LocalDate;
import java.util.List;
import java.util.function.BiConsumer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import mr.client.Client;
import mr.client.form.StageClientFormController;
import mr.clients.service.ClientsService;
import mr.output.excel.document.DocumentName;
import mr.output.excel.form.ExcelFormMemento;
import mr.output.excel.form.InvalidFormContentException;
import mr.output.excel.report.ReportType;


public class StageExcelFormDialogController extends StageClientFormController implements ExcelFormDialogController
{
	private final ClientsService clientsService;
	private final ObjectProperty<Client> suggestedClient;
	
	@FXML
	private DatePicker creationDatePicker;
	
	@FXML
	private ComboBox<String> paymentTypeChooser;
	
	@FXML
	private ComboBox<String> deliveryTypeChooser;
	
	@FXML
	private ComboBox<String> deliveryTimeChooser;
	
	@FXML
	private DatePicker expirationDatePicker;
	
	@FXML
	private ComboBox<String> reportTypeChooser;
	
	@FXML
	private TextField identifierField;
	
	@FXML
	private Button clientUpdateButton;
	
	private AutoCompletionBinding<Client> clientSuggestionBinding;
	
	public StageExcelFormDialogController(ClientsService clientsService)
	{
		this.clientsService = clientsService;
		this.suggestedClient = new SimpleObjectProperty<>();
	}
	
	@FXML
	private void initialize()
	{
		clientUpdateButton.disableProperty().bind(suggestedClient.isNull());
	}
	
	@FXML
	private void updateClient()
	{
		Client client = suggestedClient.get();
		client.applyDataFrom(this);
		
		clientsService.update(client);
	}
	
	@FXML
	private void insertClient()
	{
		Client client = createClient();
		
		clientsService.insert(client);
		suggestedClient.set(client);
	}
	
	@FXML
	private void clearClientForm()
	{
		suggestedClient.set(null);
		clear();
	}
	
	@Override
	public ExcelFormMemento fetchMemento()
	{
		ExcelFormMemento memento = new ExcelFormMemento();
		
		memento.createdAt = getCreationDate();
		memento.expiresAt = getExpirationDate();
		memento.identifier = getIdentifier();
		memento.selectedPaymentType = getSelectedIndex(paymentTypeChooser);
		memento.selectedDeliveryType = getSelectedIndex(deliveryTypeChooser);
		memento.selectedDeliveryTime = getSelectedIndex(deliveryTimeChooser);
		memento.selectedReportType = getSelectedIndex(reportTypeChooser);
		memento.client = createClient();
		
		return memento;
	}
	
	private int getSelectedIndex(ComboBox<String> comboBox)
	{
		return comboBox.getSelectionModel().getSelectedIndex();
	}
	
	@Override
	public void applyMemento(ExcelFormMemento memento)
	{
		creationDatePicker.setValue(memento.createdAt);
		expirationDatePicker.setValue(memento.expiresAt);
		identifierField.setText(memento.identifier);
		
		selectOption(paymentTypeChooser, memento.selectedPaymentType);
		selectOption(deliveryTypeChooser, memento.selectedDeliveryType);
		selectOption(deliveryTimeChooser, memento.selectedDeliveryTime);
		selectOption(reportTypeChooser, memento.selectedReportType);
		
		if (memento.client != null)
		{
			memento.client.provideDataTo(this);
			
			if (memento.client.getID() == 0)
			{
				suggestedClient.set(null);
			}
		}
	}
	
	private void selectOption(ComboBox<String> comboBox, int index)
	{
		comboBox.getSelectionModel().select(index);
	}
	
	@Override
	public void setPaymentTypes(String[] options)
	{
		paymentTypeChooser.setItems(FXCollections.observableArrayList(options));
	}
	
	@Override
	public void setDeliveryTypes(String[] options)
	{
		deliveryTypeChooser.setItems(FXCollections.observableArrayList(options));
	}
	
	@Override
	public void setDeliveryTimes(String[] options)
	{
		deliveryTimeChooser.setItems(FXCollections.observableArrayList(options));
	}
	
	@Override
	public void setReportTypes(String[] options)
	{
		reportTypeChooser.setItems(FXCollections.observableArrayList(options));
	}
	
	@Override
	public void setSuggestions(List<Client> clients)
	{
		if (clientSuggestionBinding != null)
		{
			clientSuggestionBinding.dispose();
		}
		
		clientSuggestionBinding = TextFields.bindAutoCompletion(getReceiverField(), clients);
		clientSuggestionBinding.setPrefWidth(350);
		
		clientSuggestionBinding.setOnAutoCompleted(event -> {
			suggestionSelected(event.getCompletion());
		});
	}
	
	private void suggestionSelected(Client client)
	{
		suggestedClient.set(client);
		client.provideDataTo(this);
	}
	
	@Override
	public LocalDate getCreationDate()
	{
		return creationDatePicker.getValue();
	}
	
	@Override
	public String getPaymentType()
	{
		return getSelectedItem(paymentTypeChooser);
	}
	
	private String getSelectedItem(ComboBox<String> comboBox)
	{
		return comboBox.getSelectionModel().getSelectedItem();
	}
	
	@Override
	public String getDeliveryType()
	{
		return getSelectedItem(deliveryTypeChooser);
	}
	
	@Override
	public String getDeliveryTime()
	{
		return getSelectedItem(deliveryTimeChooser);
	}
	
	@Override
	public LocalDate getExpirationDate()
	{
		return expirationDatePicker.getValue();
	}
	
	@Override
	public String getOfferType()
	{
		return getSelectedItem(reportTypeChooser);
	}
	
	@Override
	public String getIdentifier()
	{
		return identifierField.getText();
	}
	
	@Override
	public void withReportData(BiConsumer<ReportType, DocumentName> consumer)
	{
		ReportType reportType = getReportType();
		DocumentName documentName = getDocumentName();
		
		consumer.accept(reportType, documentName);
	}
	
	private ReportType getReportType()
	{
		switch (reportTypeChooser.getSelectionModel().getSelectedIndex())
		{
			case -1:
				throw new InvalidFormContentException("Nie wybrano rodzaju raportu");
			
			case 0:
				return ReportType.OFFER;
			
			case 1:
				return ReportType.ORDER;
			
			default:
				throw new InvalidFormContentException("Nieznany rodzaj raportu");
		}
	}
	
	private DocumentName getDocumentName()
	{
		String reportType = reportTypeChooser.getSelectionModel().getSelectedItem();
		String identifier = identifierField.getText();
		LocalDate createdAt = creationDatePicker.getValue();
		
		return DocumentName.from(reportType, identifier, createdAt);
	}
}