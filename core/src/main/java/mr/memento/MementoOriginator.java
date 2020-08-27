package mr.memento;


public interface MementoOriginator<T>
{
	T fetchMemento();
	void applyMemento(T memento);
}