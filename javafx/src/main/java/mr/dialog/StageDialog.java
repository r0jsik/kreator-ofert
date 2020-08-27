package mr.dialog;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import mr.Favicon;


public class StageDialog implements Dialog
{
	private final Stage stage;
	
	StageDialog(Parent root)
	{
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.addEventFilter(KeyEvent.KEY_PRESSED, this::closeOnEscape);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		showFavicon();
	}
	
	private void closeOnEscape(KeyEvent event)
	{
		if (event.getCode() == KeyCode.ESCAPE)
		{
			stage.close();
		}
	}
	
	private void showFavicon()
	{
		Favicon.showOn(stage);
	}
	
	@Override
	public void show()
	{
		stage.sizeToScene();
		stage.showAndWait();
	}
	
	@Override
	public void setTitle(String title)
	{
		stage.setTitle(title);
	}
}