package mr.dialog;


import java.io.IOException;

import javafx.scene.Parent;

import mr.LayoutBuilder;


public class StageDialogFactory implements DialogFactory
{
	@Override
	public Dialog createDialog(String sceneName, Object controller, String title)
	{
		Parent root = loadRoot(sceneName, controller);
		
		Dialog dialog = new StageDialog(root);
		dialog.setTitle(title);
		
		return dialog;
	}
	
	private Parent loadRoot(String sceneName, Object controller)
	{
		try
		{
			return LayoutBuilder.loadParent(sceneName, controller);
		}
		catch (IOException exception)
		{
			throw new DialogBuildException("Nie można zbudować okna dialogowego: " + sceneName, exception);
		}
	}
	
	@Override
	public Confirmation createConfirmation(String sceneName, Object controller, String title)
	{
		Parent root = loadRoot(sceneName, controller);
		
		Confirmation confirmation = new StageConfirmation(root);
		confirmation.setTitle(title);
		
		return confirmation;
	}
}