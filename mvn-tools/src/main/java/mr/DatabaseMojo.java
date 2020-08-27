package mr;


import java.io.*;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;


public abstract class DatabaseMojo<T extends Closeable> extends AbstractMojo
{
	@Parameter(alias = "database", required = true)
	private String pathToDatabaseFile;
	
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException
	{
		File databaseFile = new File(pathToDatabaseFile);
		T tool;
		
		try
		{
			tool = createTool(databaseFile);
		}
		catch (FileNotFoundException exception)
		{
			throw new MojoExecutionException("Nie znaleziono wymaganych plików", exception);
		}
		
		invokeOnTool(tool);
		
		try
		{
			tool.close();
		}
		catch (IOException exception)
		{
			throw new MojoFailureException("Nie można zamknąć narzędzia");
		}
	}
	
	public abstract T createTool(File databaseFile) throws FileNotFoundException;
	public abstract void invokeOnTool(T tool);
}