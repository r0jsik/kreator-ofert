package mr.dialog;


public interface DialogFactory
{
	Dialog createDialog(String sceneName, Object controller, String title);
	Confirmation createConfirmation(String sceneName, Object controller, String title);
}