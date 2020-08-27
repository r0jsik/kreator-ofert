package mr.dialog;


public abstract class Confirmation implements Dialog
{
	private Runnable onConfirmed;
	private Runnable onCancelled;
	
	protected void confirmed()
	{
		if (onConfirmed != null)
		{
			onConfirmed.run();
		}
	}
	
	protected void cancelled()
	{
		if (onCancelled != null)
		{
			onCancelled.run();
		}
	}
	
	public void setOnConfirmed(Runnable runnable)
	{
		onConfirmed = runnable;
	}
	
	public void setOnCancelled(Runnable runnable)
	{
		onCancelled = runnable;
	}
}