package mr.output.excel.report;


public enum ReportType
{
	OFFER("offer"),
	ORDER("order");
	
	private final String name;
	
	ReportType(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}