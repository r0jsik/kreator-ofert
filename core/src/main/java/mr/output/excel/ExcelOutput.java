package mr.output.excel;


import java.io.File;
import java.util.List;

import mr.item.Item;
import mr.output.Output;
import mr.output.excel.document.Document;
import mr.output.excel.document.DocumentGenerator;
import mr.output.excel.document.DocumentName;
import mr.output.excel.form.dialog.ExcelFormDialog;
import mr.output.excel.report.ReportContentProvider;
import mr.output.excel.report.ReportType;
import mr.output.export.FileExporter;
import mr.settings.preferences.Preferences;


public class ExcelOutput implements Output
{
	private final ExcelFormDialog excelFormDialog;
	private final DocumentGenerator documentGenerator;
	private final FileExporter fileExporter;
	private final Preferences preferences;
	
	public ExcelOutput(ExcelFormDialog excelFormDialog, DocumentGenerator documentGenerator, FileExporter fileExporter, Preferences preferences)
	{
		this.excelFormDialog = excelFormDialog;
		this.documentGenerator = documentGenerator;
		this.fileExporter = fileExporter;
		this.preferences = preferences;
	}
	
	@Override
	public void show(List<Item> items)
	{
		excelFormDialog.show((contentProvider, reportType, documentName) -> {
			export(contentProvider, reportType, documentName, items);
		});
	}
	
	private void export(ReportContentProvider contentProvider, ReportType reportType, DocumentName documentName, List<Item> items)
	{
		Agent tradesman = loadAgent("tradesman");
		Agent assistant = loadAgent("assistant");
		Document document = documentGenerator.generate(contentProvider, tradesman, assistant, items);
		
		export(document, reportType, documentName);
	}
	
	private Agent loadAgent(String name)
	{
		return new Agent(
			preferences.getString(name, "position"),
			preferences.getString(name, "name"),
			preferences.getString(name, "phone"),
			preferences.getString(name, "email")
		);
	}
	
	private void export(Document document, ReportType reportType, DocumentName documentName)
	{
		String reportTypeName = reportType.getName();
		String pathToExportDirectory = preferences.getString("export", reportTypeName);
		File exportDirectory = new File(pathToExportDirectory);
		
		fileExporter.export(document, exportDirectory, documentName.toString(), file -> {
			preferences.set("export", reportTypeName, file.getParent());
			preferences.save();
		});
	}
}