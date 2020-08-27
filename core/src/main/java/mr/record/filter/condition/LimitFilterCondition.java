package mr.record.filter.condition;


import mr.record.Record;


public class LimitFilterCondition<T extends Record> implements FilterCondition<T>
{
	private int limit;
	
	public LimitFilterCondition(int limit)
	{
		this.limit = limit;
	}
	
	@Override
	public boolean matches(T record)
	{
		return limit-- > 0;
	}
}