package mr.output.excel.report;


import java.time.LocalDate;

import mr.client.ClientDataProvider;


public interface ReportContentProvider extends ClientDataProvider
{
	LocalDate getCreationDate();
	String getPaymentType();
	String getDeliveryType();
	String getDeliveryTime();
	LocalDate getExpirationDate();
	String getOfferType();
	String getIdentifier();
}