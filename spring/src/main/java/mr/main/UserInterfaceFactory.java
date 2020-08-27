package mr.main;


import org.springframework.context.annotation.*;

import mr.price.list.PriceListRecord;
import mr.warning.Warning;
import mr.client.Client;
import mr.client.form.ClientFormController;
import mr.clients.list.dialog.ClientsListDialogController;
import mr.clients.list.dialog.ClientsListDialogMediator;
import mr.clients.service.ClientsService;
import mr.dialog.DialogFactory;
import mr.dialog.FileDialog;
import mr.item.dialog.ItemDialogController;
import mr.main.scene.MainSceneController;
import mr.main.scene.MainSceneMediator;
import mr.offer.OfferRecord;
import mr.output.excel.form.dialog.ExcelFormDialogController;
import mr.output.text.TextOutputController;
import mr.price.list.dialog.PriceListDialogController;
import mr.price.list.dialog.PriceListDialogMediator;
import mr.record.RecordsView;
import mr.security.dialog.PasswordDialogController;
import mr.settings.dialog.SettingsDialogController;


@Configuration
public interface UserInterfaceFactory
{
	@Bean
	MainSceneController mainSceneController(@Lazy MainSceneMediator mediator);
	
	@Bean
	RecordsView<OfferRecord> offerView();
	
	@Bean
	PasswordDialogController passwordDialogController();
	
	@Bean
	RecordsView<PriceListRecord> priceListView();
	
	@Bean
	PriceListDialogController priceListDialogController(@Lazy PriceListDialogMediator mediator);
	
	@Bean
	ItemDialogController itemDialogController();
	
	@Bean
	ClientsListDialogController clientsListDialogController(@Lazy ClientsListDialogMediator mediator);
	
	@Bean
	RecordsView<Client> clientsView();
	
	@Bean
	ClientFormController clientFormController();
	
	@Bean
	SettingsDialogController settingsDialogController();
	
	@Bean
	TextOutputController textOutputController();
	
	@Bean
	ExcelFormDialogController excelFormDialogController(@Lazy ClientsService clientsService);
	
	@Bean
	FileDialog fileDialog();
	
	@Bean
	DialogFactory dialogFactory();
	
	@Bean
	Warning warning();
}