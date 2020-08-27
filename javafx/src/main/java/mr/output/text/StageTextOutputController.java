package mr.output.text;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class StageTextOutputController implements TextOutputController
{
	@FXML
	private TextArea textArea;
	
	@Override
	public void show(String text)
	{
		Platform.runLater(() -> {
			textArea.setText(text);
			textArea.requestFocus();
			textArea.selectAll();
		});
	}
}