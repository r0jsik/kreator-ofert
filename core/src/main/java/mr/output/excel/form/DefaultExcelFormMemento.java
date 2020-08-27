package mr.output.excel.form;


import java.time.LocalDate;

import mr.settings.preferences.Preferences;


public class DefaultExcelFormMemento extends ExcelFormMemento
{
	public DefaultExcelFormMemento(Preferences preferences)
	{
		int reportLifetime = preferences.getNumber("excel", "report-lifetime");
		LocalDate createdAt = LocalDate.now();
		LocalDate expiresAt = createdAt.plusDays(reportLifetime);
		
		super.createdAt = createdAt;
		super.expiresAt = expiresAt;
		super.selectedPaymentType = preferences.getNumber("excel", "default-payment-type");
		super.selectedDeliveryType = preferences.getNumber("excel", "default-delivery-type");
		super.selectedDeliveryTime = preferences.getNumber("excel", "default-delivery-time");
		super.selectedReportType = preferences.getNumber("excel", "default-report-type");
	}
}