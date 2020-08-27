package mr.settings.preferences;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.Ini;


public class Ini4jPreferences implements Preferences
{
	private static final String listSplitDelimiter = ";";
	
	private final Ini ini;
	private final File file;
	
	public Ini4jPreferences(File file) throws IOException
	{
		if ( !file.exists())
		{
			throw new FileNotFoundException("Nie znaleziono pliku konfiguracyjengo: " + file.getAbsolutePath());
		}
		
		this.ini = new Ini(file);
		this.file = file;
	}
	
	@Override
	public void set(String section, String key, Object value)
	{
		ini.put(section, key, value);
	}
	
	@Override
	public String getString(String section, String key)
	{
		return ini.get(section, key);
	}
	
	@Override
	public boolean getBoolean(String section, String key)
	{
		return ini.get(section, key, Boolean.class);
	}
	
	@Override
	public int getNumber(String section, String key)
	{
		return ini.get(section, key, Integer.class);
	}
	
	@Override
	public void setList(String section, String key, String[] list)
	{
		set(section, key, String.join(listSplitDelimiter, list));
	}
	
	@Override
	public String[] getList(String section, String key)
	{
		return getString(section, key).split(listSplitDelimiter);
	}
	
	@Override
	public void save()
	{
		try
		{
			ini.store(file);
		}
		catch (IOException exception)
		{
			throw new PreferencesUpdateException("Nie można zapisać pliku", exception);
		}
	}
}