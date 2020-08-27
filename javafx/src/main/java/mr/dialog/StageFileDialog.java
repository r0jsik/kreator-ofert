package mr.dialog;


import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static javafx.stage.FileChooser.ExtensionFilter;


public class StageFileDialog implements FileDialog
{
	private final Stage stage;
	
	public StageFileDialog(Stage stage)
	{
		this.stage = stage;
	}
	
	@Override
	public void chooseToSave(File initialDirectory, String initialFileName, Map<String, String> extensions, Consumer<File> fileConsumer)
	{
		FileChooser fileChooser = createFileChooser(initialDirectory, initialFileName, extensions);
		File file = fileChooser.showSaveDialog(stage);
		
		if (file != null)
		{
			fileConsumer.accept(file);
		}
	}
	
	private FileChooser createFileChooser(File initialDirectory, String initialFileName, Map<String, String> extensions)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialFileName(initialFileName);
		
		if (initialDirectory.isDirectory())
		{
			fileChooser.setInitialDirectory(initialDirectory);
		}
		
		setFileExtensions(fileChooser, extensions);
		
		return fileChooser;
	}
	
	private void setFileExtensions(FileChooser fileChooser, Map<String, String> extensions)
	{
		List<ExtensionFilter> extensionFilters = fileChooser.getExtensionFilters();
		
		for (Map.Entry<String, String> entry : extensions.entrySet())
		{
			extensionFilters.add(new ExtensionFilter(entry.getValue(), entry.getKey()));
		}
	}
}