package mr.output.excel.form;


import java.time.LocalDate;

import mr.client.Client;


public class ExcelFormMemento
{
	public LocalDate createdAt;
	public LocalDate expiresAt;
	public int selectedPaymentType;
	public int selectedDeliveryType;
	public int selectedDeliveryTime;
	public int selectedReportType;
	public String identifier;
	public Client client;
}