package mr.security;


import javax.persistence.AttributeConverter;

import org.jasypt.util.text.StrongTextEncryptor;


public class SecureHibernateField implements AttributeConverter<String, String>
{
	private static final StrongTextEncryptor scrambler = new StrongTextEncryptor();
	
	@Override
	public String convertToDatabaseColumn(String string)
	{
		return scrambler.encrypt(string);
	}
	
	@Override
	public String convertToEntityAttribute(String string)
	{
		return scrambler.decrypt(string);
	}
	
	public static void setPassword(String password)
	{
		scrambler.setPassword(password);
	}
}