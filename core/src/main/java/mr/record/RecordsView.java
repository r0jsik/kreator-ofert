package mr.record;


import java.util.List;


public interface RecordsView<T extends Record>
{
	void show(List<T> records);
	void show(T record);
	void hide(T record);
	void hideAll();
}