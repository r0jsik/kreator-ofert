package mr.settings.dialog;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class StageSettingsDialogController implements SettingsDialogController
{
	@FXML
	private TextField tradesmanNameField;
	
	@FXML
	private TextField tradesmanPhoneField;
	
	@FXML
	private TextField tradesmanEmailField;
	
	@FXML
	private TextField assistantNameField;
	
	@FXML
	private TextField assistantPhoneField;
	
	@FXML
	private TextField assistantEmailField;
	
	@Override
	public void setTradesmanName(String value)
	{
		tradesmanNameField.setText(value);
	}
	
	@Override
	public String getTradesmanName()
	{
		return tradesmanNameField.getText();
	}
	
	@Override
	public void setTradesmanPhone(String value)
	{
		tradesmanPhoneField.setText(value);
	}
	
	@Override
	public String getTradesmanPhone()
	{
		return tradesmanPhoneField.getText();
	}
	
	@Override
	public void setTradesmanEmail(String value)
	{
		tradesmanEmailField.setText(value);
	}
	
	@Override
	public String getTradesmanEmail()
	{
		return tradesmanEmailField.getText();
	}
	
	@Override
	public void setAssistantName(String value)
	{
		assistantNameField.setText(value);
	}
	
	@Override
	public String getAssistantName()
	{
		return assistantNameField.getText();
	}
	
	@Override
	public void setAssistantPhone(String value)
	{
		assistantPhoneField.setText(value);
	}
	
	@Override
	public String getAssistantPhone()
	{
		return assistantPhoneField.getText();
	}
	
	@Override
	public void setAssistantEmail(String value)
	{
		assistantEmailField.setText(value);
	}
	
	@Override
	public String getAssistantEmail()
	{
		return assistantEmailField.getText();
	}
}