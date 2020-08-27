package mr.clients;


import org.apache.maven.plugins.annotations.Mojo;


@Mojo(name = "clearClients")
public class ClearClientsMojo extends ClientsMojo
{
	@Override
	public void invokeOnTool(ClientsTool tool)
	{
		tool.clear();
	}
}