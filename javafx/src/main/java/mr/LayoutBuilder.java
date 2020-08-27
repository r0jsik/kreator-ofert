package mr;


import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class LayoutBuilder
{
	public static Parent loadParent(String pathToFXML) throws IOException
	{
		URL location = toURL(pathToFXML);
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(location);
		
		return fxmlLoader.load();
	}
	
	private static URL toURL(String path)
	{
		return LayoutBuilder.class.getResource(path);
	}
	
	public static Parent loadParent(String name, Object controller) throws IOException
	{
		String pathToFXML = String.format("/%s/layout.fxml", name);
		URL location = toURL(pathToFXML);
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(location);
		fxmlLoader.setController(controller);
		
		return fxmlLoader.load();
	}
}