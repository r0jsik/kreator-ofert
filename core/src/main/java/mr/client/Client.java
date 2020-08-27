package mr.client;


import mr.record.Record;


@SuppressWarnings("unused")
public class Client implements Record
{
	private int ID;
	private String receiver = "";
	private String firmName = "";
	private String vip = "";
	private String address = "";
	private String phone = "";
	private String fax = "";
	private String email = "";
	
	public void provideDataTo(ClientDataReceiver dataReceiver)
	{
		dataReceiver.setReceiver(receiver);
		dataReceiver.setFirmName(firmName);
		dataReceiver.setVIP(vip);
		dataReceiver.setAddress(address);
		dataReceiver.setPhone(phone);
		dataReceiver.setFAX(fax);
		dataReceiver.setEmail(email);
	}
	
	public void applyDataFrom(ClientDataProvider dataProvider)
	{
		receiver = dataProvider.getReceiver();
		firmName = dataProvider.getFirmName();
		vip = dataProvider.getVIP();
		address = dataProvider.getAddress();
		phone = dataProvider.getPhone();
		fax = dataProvider.getFAX();
		email = dataProvider.getEmail();
	}
	
	@Override
	public String getField(int index)
	{
		if (index == 0)
		{
			return receiver;
		}
		
		return firmName;
	}
	
	@Override
	public String toString()
	{
		String prefix = (receiver == null)? "" : receiver;
		
		if (firmName == null || firmName.equals(""))
		{
			return prefix;
		}
		else
		{
			return String.format("%s (%s)", prefix, firmName);
		}
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public String getReceiver()
	{
		return receiver;
	}
	
	public void setReceiver(String receiver)
	{
		this.receiver = receiver;
	}
	
	public String getFirmName()
	{
		return firmName;
	}
	
	public void setFirmName(String firmName)
	{
		this.firmName = firmName;
	}
	
	public String getVIP()
	{
		return vip;
	}
	
	public void setVIP(String VIP)
	{
		this.vip = VIP;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getFAX()
	{
		return fax;
	}
	
	public void setFAX(String FAX)
	{
		this.fax = FAX;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
}