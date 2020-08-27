package mr.record.filter.condition;


import mr.record.Record;


public interface FilterCondition<T extends Record>
{
	boolean matches(T record);
}