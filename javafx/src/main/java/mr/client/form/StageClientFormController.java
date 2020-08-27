package mr.client.form;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class StageClientFormController implements ClientFormController
{
	@FXML
	private TextField receiverField;
	
	@FXML
	private TextField firmNameField;
	
	@FXML
	private TextField vipField;
	
	@FXML
	private TextField addressField;
	
	@FXML
	private TextField phoneField;
	
	@FXML
	private TextField faxField;
	
	@FXML
	private TextField emailField;
	
	@Override
	public void setReceiver(String receiver)
	{
		receiverField.setText(receiver);
	}
	
	@Override
	public String getReceiver()
	{
		return receiverField.getText();
	}
	
	@Override
	public void setFirmName(String firmName)
	{
		firmNameField.setText(firmName);
	}
	
	@Override
	public String getFirmName()
	{
		return firmNameField.getText();
	}
	
	@Override
	public void setVIP(String vip)
	{
		vipField.setText(vip);
	}
	
	@Override
	public String getVIP()
	{
		return vipField.getText();
	}
	
	@Override
	public void setAddress(String address)
	{
		addressField.setText(address);
	}
	
	@Override
	public String getAddress()
	{
		return addressField.getText();
	}
	
	@Override
	public void setPhone(String phone)
	{
		phoneField.setText(phone);
	}
	
	@Override
	public String getPhone()
	{
		return phoneField.getText();
	}
	
	@Override
	public void setFAX(String fax)
	{
		faxField.setText(fax);
	}
	
	@Override
	public String getFAX()
	{
		return faxField.getText();
	}
	
	@Override
	public void setEmail(String email)
	{
		emailField.setText(email);
	}
	
	@Override
	public String getEmail()
	{
		return emailField.getText();
	}
	
	@Override
	public void clear()
	{
		receiverField.clear();
		firmNameField.clear();
		vipField.clear();
		addressField.clear();
		phoneField.clear();
		faxField.clear();
		emailField.clear();
	}
	
	protected TextField getReceiverField()
	{
		return receiverField;
	}
}