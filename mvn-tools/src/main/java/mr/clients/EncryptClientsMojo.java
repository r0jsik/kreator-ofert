package mr.clients;


import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


@Mojo(name = "encryptClients")
public class EncryptClientsMojo extends ClientsMojo
{
	@Parameter(alias = "password", required = true)
	private String password;
	
	@Override
	public void invokeOnTool(ClientsTool tool)
	{
		tool.encrypt(password);
	}
}