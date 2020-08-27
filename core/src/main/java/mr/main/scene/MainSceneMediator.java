package mr.main.scene;


import mr.dialog.Dialog;
import mr.item.Item;
import mr.item.ItemEditor;
import mr.memento.MementoCaretaker;
import mr.memento.SingleMementoCaretaker;
import mr.offer.Offer;
import mr.offer.OfferRecord;
import mr.output.Output;
import mr.price.Price;
import mr.settings.dialog.SettingsDialog;


public class MainSceneMediator
{
	private final Dialog priceListDialog;
	private final ItemEditor itemEditor;
	private final Dialog clientsListDialog;
	private final SettingsDialog settingsDialog;
	private final Output[] outputs;
	private final Offer offer;
	
	public MainSceneMediator(Dialog priceListDialog, ItemEditor itemEditor, Dialog clientsListDialog, SettingsDialog settingsDialog, Output[] outputs)
	{
		this.priceListDialog = priceListDialog;
		this.itemEditor = itemEditor;
		this.clientsListDialog = clientsListDialog;
		this.settingsDialog = settingsDialog;
		this.outputs = outputs;
		
		offer = Offer.getInstance();
	}
	
	void modifyRecord(OfferRecord record)
	{
		Item item = offer.getItem(record);
		
		Price price = item.getPrice();
		MementoCaretaker mementoCaretaker = new SingleMementoCaretaker<>(price);
		mementoCaretaker.save();
		
		itemEditor.edit(item, record, mementoCaretaker);
	}
	
	void removeRecord(OfferRecord record)
	{
		offer.remove(record);
	}
	
	void showPriceListDialog()
	{
		priceListDialog.show();
	}
	
	void showTextOutput()
	{
		outputs[0].show(offer.getItems());
	}
	
	void showExcelOutput()
	{
		outputs[1].show(offer.getItems());
	}
	
	void clearOffer()
	{
		offer.clear();
	}
	
	void showClientsListDialog()
	{
		clientsListDialog.show();
	}
	
	void showSettingsDialog()
	{
		settingsDialog.show();
	}
}