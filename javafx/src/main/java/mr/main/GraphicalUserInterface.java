package mr.main;


import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import mr.Favicon;
import mr.LayoutBuilder;
import mr.main.scene.MainSceneController;


public class GraphicalUserInterface implements UserInterface
{
	private final Stage stage;
	
	GraphicalUserInterface(Stage stage)
	{
		this.stage = stage;
	}
	
	@Override
	public void initialize(MainSceneController controller) throws InitializationException
	{
		GraphicalUserInterfaceFactory.setStage(stage);
		Favicon.showOn(stage);
		
		try
		{
			Scene scene = loadMainScene(controller);
			stage.setScene(scene);
			stage.sizeToScene();
			
			stage.setTitle("Kreator ofert");
			stage.setResizable(false);
		}
		catch (IOException exception)
		{
			throw new InitializationException("Nie można zainicjować interfejsu JavaFX", exception);
		}
	}
	
	private Scene loadMainScene(MainSceneController controller) throws IOException
	{
		Parent root = LayoutBuilder.loadParent("main-scene", controller);
		Scene scene = new Scene(root);
		
		return scene;
	}
}