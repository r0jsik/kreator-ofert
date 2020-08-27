package mr.record.filter.condition;


import mr.record.Record;


public class AndFilterCondition<T extends Record> implements FilterCondition<T>
{
	private final FilterCondition<T>[] conditions;
	
	@SafeVarargs
	public AndFilterCondition(FilterCondition<T> ... conditions)
	{
		this.conditions = conditions;
	}
	
	@Override
	public boolean matches(T record)
	{
		for (FilterCondition<T> condition : conditions)
		{
			if ( !condition.matches(record))
			{
				return false;
			}
		}
		
		return true;
	}
}