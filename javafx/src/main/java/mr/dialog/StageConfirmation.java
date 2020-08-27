package mr.dialog;


import java.util.List;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import mr.Favicon;


public class StageConfirmation extends Confirmation
{
	private final Dialog<ButtonType> dialog;
	
	StageConfirmation(Parent root)
	{
		dialog = new Dialog<>();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setResizable(false);
		
		setUpDialogPane(root);
		showFavicon();
	}
	
	private void setUpDialogPane(Parent root)
	{
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.addEventFilter(KeyEvent.KEY_PRESSED, this::closeOnEscape);
		dialogPane.setContent(root);
		
		List<ButtonType> buttons = dialogPane.getButtonTypes();
		buttons.add(ButtonType.OK);
	}
	
	private void closeOnEscape(KeyEvent event)
	{
		if (event.getCode() == KeyCode.ESCAPE)
		{
			dialog.close();
		}
	}
	
	private void showFavicon()
	{
		DialogPane dialogPane = dialog.getDialogPane();
		Scene scene = dialogPane.getScene();
		Stage stage = (Stage) scene.getWindow();
		
		Favicon.showOn(stage);
	}
	
	@Override
	public void show()
	{
		updateSize();
		
		dialog.setResult(ButtonType.CANCEL);
		dialog.showAndWait();
		
		if (dialog.getResult() == ButtonType.OK)
		{
			confirmed();
		}
		else
		{
			cancelled();
		}
	}
	
	private void updateSize()
	{
		Platform.runLater(() -> {
			dialog.getDialogPane().getScene().getWindow().sizeToScene();
		});
	}
	
	@Override
	public void setTitle(String title)
	{
		dialog.setTitle(title);
	}
}