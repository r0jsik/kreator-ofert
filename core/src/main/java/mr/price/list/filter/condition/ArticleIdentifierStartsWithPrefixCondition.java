package mr.price.list.filter.condition;


import mr.price.list.PriceListRecord;
import mr.record.filter.condition.FilterCondition;


public class ArticleIdentifierStartsWithPrefixCondition implements FilterCondition<PriceListRecord>
{
	private final String prefix;
	
	public ArticleIdentifierStartsWithPrefixCondition(String prefix)
	{
		this.prefix = prefix;
	}
	
	@Override
	public boolean matches(PriceListRecord record)
	{
		return record.getArtID().startsWith(prefix);
	}
}