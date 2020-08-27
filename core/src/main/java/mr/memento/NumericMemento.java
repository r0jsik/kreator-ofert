package mr.memento;


public class NumericMemento
{
	private final int[] state;
	
	public NumericMemento(int ... state)
	{
		this.state = state;
	}
	
	public int get(int index)
	{
		return state[index];
	}
}