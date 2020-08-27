package mr.main;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mr.client.Client;
import mr.clients.service.SecureClientsService;
import mr.database.DecryptableDatabase;
import mr.main.scene.MainSceneController;
import mr.offer.OfferRecord;
import mr.output.excel.form.DefaultExcelFormMemento;
import mr.output.excel.form.ExcelFormMemento;
import mr.output.excel.form.dialog.ExcelFormDialogController;
import mr.price.list.PriceList;
import mr.price.list.dialog.PriceListDialogController;
import mr.record.RecordsView;
import mr.record.filter.FilteredRecordsView;
import mr.security.dialog.PasswordDialog;
import mr.settings.preferences.Preferences;


public class SpringDependencyMap implements DependencyMap
{
	private final ApplicationContext context;
	
	SpringDependencyMap(Class<? extends UserInterfaceFactory> configuration)
	{
		context = new AnnotationConfigApplicationContext(configuration);
	}
	
	@Override
	public MainSceneController getMainSceneController()
	{
		return context.getBean(MainSceneController.class);
	}
	
	@Override
	public PasswordDialog getPasswordDialog()
	{
		return context.getBean(PasswordDialog.class);
	}
	
	@Override
	public DecryptableDatabase getDatabase()
	{
		return context.getBean(DecryptableDatabase.class);
	}
	
	@Override
	public SecureClientsService getClientsService()
	{
		return context.getBean(SecureClientsService.class);
	}
	
	@Override
	public PriceListDialogController getPriceListDialogController()
	{
		return context.getBean(PriceListDialogController.class);
	}
	
	@Override
	public List<PriceList> getPriceLists()
	{
		return (List<PriceList>) context.getBean("priceLists");
	}
	
	@Override
	public FilteredRecordsView<Client> getClientsView()
	{
		return (FilteredRecordsView<Client>) context.getBean("filteredClientsView");
	}
	
	@Override
	public ExcelFormDialogController getExcelFormDialogController()
	{
		return context.getBean(ExcelFormDialogController.class);
	}
	
	@Override
	public ExcelFormMemento getDefaultExcelFormState()
	{
		return context.getBean(DefaultExcelFormMemento.class);
	}
	
	@Override
	public RecordsView<OfferRecord> getOfferView()
	{
		return (RecordsView<OfferRecord>) context.getBean("offerView");
	}
	
	@Override
	public Preferences getMainPreferences()
	{
		return (Preferences) context.getBean("mainPreferences");
	}
}