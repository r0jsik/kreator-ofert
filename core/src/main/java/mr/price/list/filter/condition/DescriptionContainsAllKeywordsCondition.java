package mr.price.list.filter.condition;


import mr.price.list.PriceListRecord;
import mr.record.filter.condition.FilterCondition;


public class DescriptionContainsAllKeywordsCondition implements FilterCondition<PriceListRecord>
{
	private final String[] keywords;
	
	public DescriptionContainsAllKeywordsCondition(String[] keywords)
	{
		this.keywords = keywords;
	}
	
	@Override
	public boolean matches(PriceListRecord record)
	{
		String description = record.getDescription().toUpperCase();
		
		for (String keyword : keywords)
		{
			keyword = keyword.toUpperCase();
			
			if ( !description.contains(keyword))
			{
				return false;
			}
		}
		
		return true;
	}
}