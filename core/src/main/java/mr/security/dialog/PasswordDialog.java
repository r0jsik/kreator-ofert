package mr.security.dialog;


import mr.dialog.Confirmation;


public class PasswordDialog
{
	private final Confirmation confirmation;
	private final PasswordDialogController controller;
	
	public PasswordDialog(Confirmation confirmation, PasswordDialogController controller)
	{
		this.confirmation = confirmation;
		this.controller = controller;
	}
	
	public void show()
	{
		confirmation.show();
	}
	
	public String getPassword()
	{
		return controller.getPassword();
	}
}