package mr.output.export;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

import mr.dialog.FileDialog;


public class DialogFileExporter implements FileExporter
{
	private final FileDialog fileDialog;
	private final Map<String, String> extensions;
	
	public DialogFileExporter(FileDialog fileDialog, Map<String, String> extensions)
	{
		this.fileDialog = fileDialog;
		this.extensions = extensions;
	}
	
	@Override
	public void export(Exportable exportable, File directory, String fileName, Consumer<File> fileConsumer)
	{
		fileDialog.chooseToSave(directory, fileName, extensions, file -> {
			export(exportable, file, fileConsumer);
		});
	}
	
	private void export(Exportable exportable, File file, Consumer<File> fileConsumer)
	{
		try
		{
			exportable.exportTo(file);
			exportable.close();
			
			fileConsumer.accept(file);
		}
		catch (IOException exception)
		{
			throw new ExportException("Nie można uzyskać dostępu do pliku", exception);
		}
	}
}