package mr.record.filter;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mr.record.Record;
import mr.record.RefreshableRecordsView;
import mr.record.filter.condition.FilterCondition;


public class FilteredRecordsView<T extends Record> implements RefreshableRecordsView<T>
{
	private final RefreshableRecordsView<T> recordsView;
	
	private List<T> records;
	private FilterCondition<T> condition;
	
	public FilteredRecordsView(RefreshableRecordsView<T> recordsView)
	{
		this.recordsView = recordsView;
		this.records = Collections.emptyList();
		this.condition = record -> true;
	}
	
	public void clearFilter()
	{
		this.condition = record -> true;
		this.recordsView.show(records);
	}
	
	public void filter(FilterCondition<T> condition)
	{
		this.condition = condition;
		this.update();
	}
	
	private void update()
	{
		Stream<T> stream = records.stream();
		Stream<T> filteredStream = stream.filter(record -> condition.matches(record));
		List<T> filteredRecords = filteredStream.collect(Collectors.toList());
		
		recordsView.show(filteredRecords);
	}
	
	@Override
	public void show(List<T> records)
	{
		this.records = Collections.unmodifiableList(records);
		this.update();
	}
	
	@Override
	public void show(T record)
	{
		if (condition.matches(record))
		{
			recordsView.show(record);
		}
	}
	
	@Override
	public void hide(T record)
	{
		recordsView.hide(record);
	}
	
	@Override
	public void refresh(T record)
	{
		if (condition.matches(record))
		{
			recordsView.refresh(record);
		}
		else
		{
			recordsView.hide(record);
		}
	}
	
	@Override
	public void refresh()
	{
		recordsView.refresh();
	}
	
	@Override
	public void hideAll()
	{
		recordsView.show(Collections.emptyList());
	}
}