package mr.help;


public class PrintingHelpOutput implements HelpOutput
{
	@Override
	public void show(HelpProvider helpProvider)
	{
		helpProvider.provide(System.out::println);
	}
}