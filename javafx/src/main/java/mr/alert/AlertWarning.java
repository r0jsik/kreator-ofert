package mr.alert;


import javafx.scene.control.Alert;

import mr.warning.Warning;


public class AlertWarning implements Warning
{
	@Override
	public void show(String message, Exception exception)
	{
		Alert alert = new CustomAlert(Alert.AlertType.WARNING, message, exception);
		alert.showAndWait();
	}
}