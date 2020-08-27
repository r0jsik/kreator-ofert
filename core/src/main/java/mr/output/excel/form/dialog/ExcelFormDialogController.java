package mr.output.excel.form.dialog;


import java.util.List;
import java.util.function.BiConsumer;

import mr.client.Client;
import mr.memento.MementoOriginator;
import mr.output.excel.document.DocumentName;
import mr.output.excel.form.ExcelFormMemento;
import mr.output.excel.report.ReportContentProvider;
import mr.output.excel.report.ReportType;


public interface ExcelFormDialogController extends ReportContentProvider, MementoOriginator<ExcelFormMemento>
{
	void setPaymentTypes(String[] options);
	void setDeliveryTypes(String[] options);
	void setDeliveryTimes(String[] options);
	void setReportTypes(String[] options);
	void setSuggestions(List<Client> clients);
	void withReportData(BiConsumer<ReportType, DocumentName> reportDataConsumer);
}