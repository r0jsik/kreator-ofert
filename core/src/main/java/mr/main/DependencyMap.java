package mr.main;


import java.util.List;

import mr.client.Client;
import mr.clients.service.SecureClientsService;
import mr.database.DecryptableDatabase;
import mr.main.scene.MainSceneController;
import mr.offer.OfferRecord;
import mr.output.excel.form.ExcelFormMemento;
import mr.output.excel.form.dialog.ExcelFormDialogController;
import mr.price.list.PriceList;
import mr.price.list.dialog.PriceListDialogController;
import mr.record.RecordsView;
import mr.record.filter.FilteredRecordsView;
import mr.security.dialog.PasswordDialog;
import mr.settings.preferences.Preferences;


public interface DependencyMap
{
	MainSceneController getMainSceneController();
	PasswordDialog getPasswordDialog();
	DecryptableDatabase getDatabase();
	SecureClientsService getClientsService();
	PriceListDialogController getPriceListDialogController();
	List<PriceList> getPriceLists();
	FilteredRecordsView<Client> getClientsView();
	ExcelFormDialogController getExcelFormDialogController();
	ExcelFormMemento getDefaultExcelFormState();
	RecordsView<OfferRecord> getOfferView();
	Preferences getMainPreferences();
}