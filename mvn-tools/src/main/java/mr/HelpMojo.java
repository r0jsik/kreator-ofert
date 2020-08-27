package mr;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;

import mr.help.*;


@Mojo(name = "help")
public class HelpMojo extends AbstractMojo
{
	@Override
	public void execute()
	{
		HelpProvider helpProvider = new FileHelpProvider("/help.txt");
		HelpOutput helpOutput = new PrintingHelpOutput();
		
		helpOutput.show(helpProvider);
	}
}