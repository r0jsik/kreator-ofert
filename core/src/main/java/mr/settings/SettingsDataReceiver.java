package mr.settings;


public interface SettingsDataReceiver
{
	default void applyDataFrom(SettingsDataProvider provider)
	{
		setTradesmanName(provider.getTradesmanName());
		setTradesmanPhone(provider.getTradesmanPhone());
		setTradesmanEmail(provider.getTradesmanEmail());
		setAssistantName(provider.getAssistantName());
		setAssistantPhone(provider.getAssistantPhone());
		setAssistantEmail(provider.getAssistantEmail());
	}
	
	void setTradesmanName(String value);
	void setTradesmanPhone(String value);
	void setTradesmanEmail(String value);
	void setAssistantName(String value);
	void setAssistantPhone(String value);
	void setAssistantEmail(String value);
}