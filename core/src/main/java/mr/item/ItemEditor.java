package mr.item;


import mr.dialog.Confirmation;
import mr.item.dialog.ItemDialogController;
import mr.memento.MementoCaretaker;
import mr.offer.OfferRecord;
import mr.record.RefreshableRecordsView;


public class ItemEditor
{
	private final Confirmation confirmation;
	private final ItemDialogController controller;
	private final RefreshableRecordsView<OfferRecord> offerView;
	
	public ItemEditor(Confirmation confirmation, ItemDialogController controller, RefreshableRecordsView<OfferRecord> offerView)
	{
		this.confirmation = confirmation;
		this.controller = controller;
		this.offerView = offerView;
	}
	
	public void edit(Item item, OfferRecord record, MementoCaretaker mementoCaretaker)
	{
		controller.show(item);
		
		confirmation.setOnConfirmed(() -> {
			controller.showOn(item);
			item.showOn(record);
			offerView.refresh(record);
		});
		
		confirmation.setOnCancelled(() -> {
			mementoCaretaker.undo();
		});
		
		confirmation.setTitle("Edytuj element oferty");
		confirmation.show();
	}
}