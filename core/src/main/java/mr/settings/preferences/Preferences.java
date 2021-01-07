package mr.settings.preferences;


public interface Preferences
{
	void set(String section, String key, Object value);
	String getString(String section, String key);
	boolean getBoolean(String section, String key);
	int getNumber(String section, String key);
	double getDouble(String section, String key);
	void setList(String section, String key, String[] value);
	String[] getList(String section, String key);
	void save();
}