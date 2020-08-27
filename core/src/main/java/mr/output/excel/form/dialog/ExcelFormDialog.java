package mr.output.excel.form.dialog;


import java.util.Collections;

import mr.clients.service.ClientsService;
import mr.clients.service.ClientsServiceException;
import mr.dialog.Confirmation;
import mr.memento.MementoCaretaker;
import mr.memento.SingleMementoCaretaker;
import mr.output.excel.document.DocumentName;
import mr.output.excel.report.ReportContentProvider;
import mr.output.excel.report.ReportType;


public class ExcelFormDialog
{
	private final Confirmation confirmation;
	private final ExcelFormDialogController controller;
	private final ClientsService clientsService;
	
	public ExcelFormDialog(Confirmation confirmation, ExcelFormDialogController controller, ClientsService clientsService)
	{
		this.confirmation = confirmation;
		this.controller = controller;
		this.clientsService = clientsService;
	}
	
	public void show(ExcelFormConsumer excelFormConsumer)
	{
		MementoCaretaker mementoCaretaker = new SingleMementoCaretaker<>(controller);
		mementoCaretaker.save();
		
		updateClientsSuggestions();
		
		confirmation.setOnConfirmed(() -> {
			controller.withReportData((reportType, documentName) -> {
				excelFormConsumer.accept(controller, reportType, documentName);
			});
		});
		
		confirmation.setOnCancelled(() -> {
			mementoCaretaker.undo();
		});
		
		confirmation.show();
	}
	
	private void updateClientsSuggestions()
	{
		try
		{
			clientsService.withAll(controller::setSuggestions);
		}
		catch (ClientsServiceException exception)
		{
			controller.setSuggestions(Collections.emptyList());
		}
	}
	
	@FunctionalInterface
	public interface ExcelFormConsumer
	{
		void accept(ReportContentProvider contentProvider, ReportType reportType, DocumentName documentName);
	}
}