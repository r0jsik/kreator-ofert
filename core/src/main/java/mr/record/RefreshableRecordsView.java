package mr.record;


public interface RefreshableRecordsView<T extends Record> extends RecordsView<T>
{
	void refresh(T record);
	void refresh();
}