package mr.alert;


import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import mr.Favicon;


public class CustomAlert extends Alert
{
	public CustomAlert(AlertType type, String message, Throwable exception)
	{
		super(type);
		
		super.setTitle("Wystąpił problem!");
		super.setHeaderText(message);
		super.setContentText(exception.getMessage());
		super.setResizable(false);
		
		showFavicon();
	}
	
	private void showFavicon()
	{
		DialogPane dialogPane = super.getDialogPane();
		Scene scene = dialogPane.getScene();
		Stage stage = (Stage) scene.getWindow();
		
		Favicon.showOn(stage);
	}
}