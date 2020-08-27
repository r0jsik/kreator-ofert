package mr;


import java.io.InputStream;
import java.util.List;

import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Favicon
{
	private static final String pathToFavicon = "/favicon.png";
	
	public static void showOn(Stage stage)
	{
		InputStream imageStream = Favicon.class.getResourceAsStream(pathToFavicon);
		Image icon = new Image(imageStream);
		List<Image> icons = stage.getIcons();
		icons.add(icon);
	}
}