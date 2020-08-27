package mr.main;


import java.io.IOException;

import javafx.scene.Parent;
import javafx.stage.Stage;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.*;

import mr.LayoutBuilder;
import mr.alert.AlertWarning;
import mr.client.Client;
import mr.client.form.ClientFormController;
import mr.client.form.StageClientFormController;
import mr.clients.list.dialog.ClientsListDialogController;
import mr.clients.list.dialog.ClientsListDialogMediator;
import mr.clients.list.dialog.StageClientsListDialogController;
import mr.clients.service.ClientsService;
import mr.dialog.*;
import mr.item.dialog.ItemDialogController;
import mr.item.dialog.StageItemDialogController;
import mr.main.scene.MainSceneController;
import mr.main.scene.MainSceneMediator;
import mr.main.scene.StageMainSceneController;
import mr.offer.OfferRecord;
import mr.output.excel.form.dialog.ExcelFormDialogController;
import mr.output.excel.form.dialog.StageExcelFormDialogController;
import mr.output.text.StageTextOutputController;
import mr.output.text.TextOutputController;
import mr.price.list.PriceListRecord;
import mr.price.list.dialog.PriceListDialogController;
import mr.price.list.dialog.PriceListDialogMediator;
import mr.price.list.dialog.StagePriceListDialogController;
import mr.record.TableRecordsView;
import mr.security.dialog.PasswordDialogController;
import mr.security.dialog.StagePasswordDialogController;
import mr.settings.dialog.SettingsDialogController;
import mr.settings.dialog.StageSettingsDialogController;
import mr.warning.Warning;


@Configuration
@EnableAspectJAutoProxy
@ImportResource("classpath:build.xml")
public class GraphicalUserInterfaceFactory implements UserInterfaceFactory
{
	private static final String pathToPriceListViewLayoutFile = "/price-list-dialog/price-list-view.fxml";
	private static final String pathToOfferViewLayoutFile = "/main-scene/offer-view.fxml";
	private static final String pathToClientsViewLayoutFile = "/clients-list-dialog/clients-view.fxml";
	
	private static Stage stage;
	
	@Override
	@Bean
	public MainSceneController mainSceneController(@Lazy MainSceneMediator mediator)
	{
		return new StageMainSceneController(mediator, offerView());
	}
	
	@Override
	@Bean
	public TableRecordsView<OfferRecord> offerView()
	{
		return (TableRecordsView<OfferRecord>) loadTableRecordsView(pathToOfferViewLayoutFile);
	}
	
	private Parent loadTableRecordsView(String pathToLayoutFile)
	{
		try
		{
			return LayoutBuilder.loadParent(pathToLayoutFile);
		}
		catch (IOException exception)
		{
			throw new BeanInitializationException("Nie można załadować pliku: " + pathToLayoutFile, exception);
		}
	}
	
	@Override
	@Bean
	public PasswordDialogController passwordDialogController()
	{
		return new StagePasswordDialogController();
	}
	
	@Bean
	public TableRecordsView<PriceListRecord> priceListView()
	{
		return (TableRecordsView<PriceListRecord>) loadTableRecordsView(pathToPriceListViewLayoutFile);
	}
	
	@Override
	@Bean
	public PriceListDialogController priceListDialogController(@Lazy PriceListDialogMediator mediator)
	{
		return new StagePriceListDialogController(mediator, priceListView());
	}
	
	@Override
	@Bean
	public ItemDialogController itemDialogController()
	{
		return new StageItemDialogController();
	}
	
	@Override
	@Bean
	public ClientsListDialogController clientsListDialogController(@Lazy ClientsListDialogMediator mediator)
	{
		return new StageClientsListDialogController(mediator, clientsView());
	}
	
	@Override
	@Bean
	public TableRecordsView<Client> clientsView()
	{
		return (TableRecordsView<Client>) loadTableRecordsView(pathToClientsViewLayoutFile);
	}
	
	@Override
	@Bean
	public ClientFormController clientFormController()
	{
		return new StageClientFormController();
	}
	
	@Override
	@Bean
	public SettingsDialogController settingsDialogController()
	{
		return new StageSettingsDialogController();
	}
	
	@Override
	@Bean
	public TextOutputController textOutputController()
	{
		return new StageTextOutputController();
	}
	
	@Override
	@Bean
	public ExcelFormDialogController excelFormDialogController(@Lazy ClientsService clientsService)
	{
		return new StageExcelFormDialogController(clientsService);
	}
	
	@Override
	@Bean
	public FileDialog fileDialog()
	{
		return new StageFileDialog(stage);
	}
	
	@Override
	@Bean
	public DialogFactory dialogFactory()
	{
		return new StageDialogFactory();
	}
	
	@Override
	@Bean
	public Warning warning()
	{
		return new AlertWarning();
	}
	
	static void setStage(Stage stage)
	{
		GraphicalUserInterfaceFactory.stage = stage;
	}
}