package mr.settings;


import mr.settings.preferences.Preferences;


public class Settings implements SettingsDataReceiver, SettingsDataProvider
{
	private final Preferences preferences;
	
	public Settings(Preferences preferences)
	{
		this.preferences = preferences;
	}
	
	public void save()
	{
		preferences.save();
	}
	
	@Override
	public void setTradesmanName(String value)
	{
		preferences.set("tradesman", "name", value);
	}
	
	@Override
	public String getTradesmanName()
	{
		return preferences.getString("tradesman", "name");
	}
	
	@Override
	public void setTradesmanPhone(String value)
	{
		preferences.set("tradesman", "phone", value);
	}
	
	@Override
	public String getTradesmanPhone()
	{
		return preferences.getString("tradesman", "phone");
	}
	
	@Override
	public void setTradesmanEmail(String value)
	{
		preferences.set("tradesman", "email", value);
	}
	
	@Override
	public String getTradesmanEmail()
	{
		return preferences.getString("tradesman", "email");
	}
	
	@Override
	public void setAssistantName(String value)
	{
		preferences.set("assistant", "name", value);
	}
	
	@Override
	public String getAssistantName()
	{
		return preferences.getString("assistant", "name");
	}
	
	@Override
	public void setAssistantPhone(String value)
	{
		preferences.set("assistant", "phone", value);
	}
	
	@Override
	public String getAssistantPhone()
	{
		return preferences.getString("assistant", "phone");
	}
	
	@Override
	public void setAssistantEmail(String value)
	{
		preferences.set("assistant", "email", value);
	}
	
	@Override
	public String getAssistantEmail()
	{
		return preferences.getString("assistant", "email");
	}
}