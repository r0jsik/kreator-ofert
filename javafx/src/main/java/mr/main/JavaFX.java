package mr.main;


import java.util.Map;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import mr.alert.CustomAlert;
import mr.security.PasswordNotTypedException;


public class JavaFX extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		try
		{
			DependencyMap dependencyMap = new SpringDependencyMap(GraphicalUserInterfaceFactory.class);
			UserInterface userInterface = new GraphicalUserInterface(stage);
			StateInitializer stateInitializer = new StateInitializer(dependencyMap, userInterface);
			
			startInitializer(stateInitializer);
			
			stage.show();
		}
		catch (PasswordNotTypedException exception)
		{
			System.exit(-2);
		}
		catch (InitializationException | RuntimeException exception)
		{
			Alert alert = new CustomAlert(Alert.AlertType.ERROR, "Nie można uruchomić aplikacji", exception);
			alert.showAndWait();
		}
	}
	
	private void startInitializer(StateInitializer stateInitializer) throws InitializationException
	{
		Parameters parameters = getParameters();
		Map<String, String> namedParameters = parameters.getNamed();
		String password = namedParameters.get("password");
		
		if (password == null)
		{
			stateInitializer.initialize();
		}
		else
		{
			stateInitializer.initialize(password);
		}
	}
}