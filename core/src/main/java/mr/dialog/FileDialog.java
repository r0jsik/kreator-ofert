package mr.dialog;


import java.io.File;
import java.util.Map;
import java.util.function.Consumer;


public interface FileDialog
{
	void chooseToSave(File initialDirectory, String initialFileName, Map<String, String> extensions, Consumer<File> fileConsumer);
}