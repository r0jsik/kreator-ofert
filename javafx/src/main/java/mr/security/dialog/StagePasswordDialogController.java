package mr.security.dialog;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;


public class StagePasswordDialogController implements PasswordDialogController
{
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private void initialize()
	{
		Platform.runLater(() -> {
			passwordField.requestFocus();
		});
	}
	
	@Override
	public String getPassword()
	{
		return passwordField.getText();
	}
}