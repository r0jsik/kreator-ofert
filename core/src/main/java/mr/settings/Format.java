package mr.settings;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Format
{
	private static DecimalFormat priceFormat;
	private static String agentFormat;
	private static DateTimeFormatter dateFormat;
	private static String documentNameFormat;
	
	public static String asPrice(double value)
	{
		return priceFormat.format(value);
	}
	
	public static String asAgent(String name, String phone, String email)
	{
		return String.format(agentFormat, name, phone, email);
	}
	
	public static String asDate(LocalDate date)
	{
		return dateFormat.format(date);
	}
	
	public static String asDocumentName(String reportType, String identifier, String formattedDate)
	{
		return String.format(documentNameFormat, reportType, identifier, formattedDate);
	}
	
	public static void setPriceFormat(String format)
	{
		priceFormat = new DecimalFormat(format);
	}
	
	public static void setAgentFormat(String format)
	{
		agentFormat = format;
	}
	
	public static void setDateFormat(String format)
	{
		dateFormat = DateTimeFormatter.ofPattern(format);
	}
	
	public static void setDocumentNameFormat(String format)
	{
		documentNameFormat = format;
	}
}