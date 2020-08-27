package mr.item;


import mr.dialog.Confirmation;
import mr.item.dialog.ItemDialogController;
import mr.offer.Offer;


public class ItemCollector
{
	private final Confirmation confirmation;
	private final ItemDialogController controller;
	
	public ItemCollector(Confirmation confirmation, ItemDialogController controller)
	{
		this.confirmation = confirmation;
		this.controller = controller;
	}
	
	public void collect(Item item)
	{
		controller.show(item);
		
		confirmation.setOnConfirmed(() -> {
			controller.showOn(item);
			
			Offer offer = Offer.getInstance();
			offer.insert(item);
		});
		
		confirmation.setTitle("Dodaj artykuł do oferty");
		confirmation.show();
	}
}