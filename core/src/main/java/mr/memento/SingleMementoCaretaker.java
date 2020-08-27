package mr.memento;


public class SingleMementoCaretaker<T> implements MementoCaretaker
{
	private final MementoOriginator<T> originator;
	
	private T memento;
	
	public SingleMementoCaretaker(MementoOriginator<T> originator)
	{
		this.originator = originator;
	}
	
	@Override
	public void save()
	{
		memento = originator.fetchMemento();
	}
	
	@Override
	public void undo()
	{
		if (memento == null)
		{
			throw new IllegalStateException();
		}
		
		originator.applyMemento(memento);
	}
}