package mr.output.export;


import java.io.File;
import java.util.function.Consumer;


public interface FileExporter
{
	void export(Exportable exportable, File directory, String fileName, Consumer<File> callback);
}