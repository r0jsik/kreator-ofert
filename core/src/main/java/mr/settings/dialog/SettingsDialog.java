package mr.settings.dialog;


import mr.dialog.Confirmation;
import mr.settings.Settings;


public class SettingsDialog
{
	private final Confirmation confirmation;
	private final SettingsDialogController controller;
	private final Settings settings;
	
	public SettingsDialog(Confirmation confirmation, SettingsDialogController controller, Settings settings)
	{
		this.confirmation = confirmation;
		this.controller = controller;
		this.settings = settings;
	}
	
	public void show()
	{
		controller.applyDataFrom(settings);
		
		confirmation.setOnConfirmed(() -> {
			settings.applyDataFrom(controller);
			settings.save();
		});
		
		confirmation.show();
	}
}