package mr.output.export;


import java.io.Closeable;
import java.io.File;
import java.io.IOException;


public interface Exportable extends Closeable
{
	void exportTo(File file) throws IOException;
}