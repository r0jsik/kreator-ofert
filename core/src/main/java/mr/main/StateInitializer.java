package mr.main;


import java.util.List;
import java.util.function.Consumer;

import mr.client.Client;
import mr.clients.service.ClientsService;
import mr.clients.service.ClientsServiceException;
import mr.clients.service.SecureClientsService;
import mr.database.DatabaseDecryptionException;
import mr.database.DecryptableDatabase;
import mr.offer.Offer;
import mr.offer.OfferRecord;
import mr.output.excel.form.ExcelFormMemento;
import mr.output.excel.form.dialog.ExcelFormDialogController;
import mr.price.list.PriceList;
import mr.price.list.dialog.PriceListDialogController;
import mr.record.RecordsView;
import mr.record.filter.FilteredRecordsView;
import mr.security.PasswordNotTypedException;
import mr.security.dialog.PasswordDialog;
import mr.settings.Format;
import mr.settings.preferences.Preferences;


final class StateInitializer
{
	private final DependencyMap dependencyMap;
	private final UserInterface userInterface;
	private final Preferences preferences;
	
	StateInitializer(DependencyMap dependencyMap, UserInterface userInterface)
	{
		this.dependencyMap = dependencyMap;
		this.userInterface = userInterface;
		this.preferences = dependencyMap.getMainPreferences();
	}
	
	void initialize() throws InitializationException
	{
		PasswordDialog passwordDialog = dependencyMap.getPasswordDialog();
		passwordDialog.show();
		
		String password = passwordDialog.getPassword();
		
		if (password.equals(""))
		{
			throw new PasswordNotTypedException("Nie podano hasła");
		}
		
		initialize(password);
	}
	
	void initialize(String password) throws InitializationException
	{
		initializeUserInterface();
		showOfferInView();
		showOptionsInExcelForm();
		selectDefaultOptionsInExcelForm();
		setUpFormat();
		
		decryptApplication(password);
		
		showPriceListsInDialog();
		showClientsListInDialog();
	}
	
	private void initializeUserInterface() throws InitializationException
	{
		userInterface.initialize(dependencyMap.getMainSceneController());
	}
	
	private void showOfferInView()
	{
		RecordsView<OfferRecord> offerView = dependencyMap.getOfferView();
		Offer offer = Offer.getInstance();
		offer.setView(offerView);
	}
	
	private void showOptionsInExcelForm()
	{
		ExcelFormDialogController formController = dependencyMap.getExcelFormDialogController();
		
		provideExcelOptions("payment-types", formController::setPaymentTypes);
		provideExcelOptions("delivery-types", formController::setDeliveryTypes);
		provideExcelOptions("delivery-times", formController::setDeliveryTimes);
		provideExcelOptions("report-types", formController::setReportTypes);
	}
	
	private void provideExcelOptions(String name, Consumer<String[]> optionsConsumer)
	{
		optionsConsumer.accept(preferences.getList("excel", name));
	}
	
	private void selectDefaultOptionsInExcelForm()
	{
		ExcelFormDialogController controller = dependencyMap.getExcelFormDialogController();
		ExcelFormMemento memento = dependencyMap.getDefaultExcelFormState();
		
		controller.applyMemento(memento);
	}
	
	private void setUpFormat()
	{
		provideFormat("price", Format::setPriceFormat);
		provideFormat("agent", Format::setAgentFormat);
		provideFormat("date", Format::setDateFormat);
		provideFormat("document-name", Format::setDocumentNameFormat);
	}
	
	private void provideFormat(String name, Consumer<String> formatConsumer)
	{
		formatConsumer.accept(preferences.getString("format", name));
	}
	
	private void decryptApplication(String password)
	{
		try
		{
			decryptDatabase(password);
			decryptClientsService(password);
		}
		catch (DatabaseDecryptionException | ClientsServiceException exception)
		{
			throw new SecurityException("Nieprawidłowe hasło", exception);
		}
	}
	
	private void decryptDatabase(String password)
	{
		DecryptableDatabase secureDatabase = dependencyMap.getDatabase();
		secureDatabase.decrypt(password);
	}
	
	private void decryptClientsService(String password)
	{
		SecureClientsService secureClientsService = dependencyMap.getClientsService();
		secureClientsService.setPassword(password);
	}
	
	private void showPriceListsInDialog()
	{
		List<PriceList> priceLists = dependencyMap.getPriceLists();
		PriceListDialogController controller = dependencyMap.getPriceListDialogController();
		
		controller.setPriceLists(priceLists);
		controller.selectFirstPriceList();
	}
	
	private void showClientsListInDialog() throws InitializationException
	{
		FilteredRecordsView<Client> clientsView = dependencyMap.getClientsView();
		ClientsService clientsService = dependencyMap.getClientsService();
		
		try
		{
			clientsService.withAll(clientsView::show);
		}
		catch (ClientsServiceException exception)
		{
			throw new InitializationException("Nie można załadować listy klientów", exception);
		}
	}
}