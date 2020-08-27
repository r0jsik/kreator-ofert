package mr.output.excel.document;


import java.util.List;

import mr.item.Item;
import mr.output.excel.Agent;
import mr.output.excel.report.Report;
import mr.output.excel.report.ReportContentProvider;
import mr.output.excel.report.standard.StandardReport;


public class DocumentGenerator
{
	private final DocumentFactory documentFactory;
	
	public DocumentGenerator(DocumentFactory documentFactory)
	{
		this.documentFactory = documentFactory;
	}
	
	public Document generate(ReportContentProvider contentProvider, Agent tradesman, Agent assistant, List<Item> items)
	{
		Document document = documentFactory.create();
		Report report = new StandardReport(document);
		
		provideContent(report, contentProvider);
		
		report.showAgents(tradesman, assistant);
		report.showItems(items);
		report.showTotalPrice();
		report.showFooter();
		
		return document;
	}
	
	public void provideContent(Report report, ReportContentProvider contentProvider)
	{
		String firmName = contentProvider.getFirmName();
		String vip = contentProvider.getVIP();
		String firmLabel = toFirmLabel(firmName, vip);
		
		report.showCreationDate(contentProvider.getCreationDate());
		report.showPaymentType(contentProvider.getPaymentType());
		report.showDeliveryType(contentProvider.getDeliveryType());
		report.showDeliveryTime(contentProvider.getDeliveryTime());
		report.showExpirationDate(contentProvider.getExpirationDate());
		report.showReceiver(contentProvider.getReceiver());
		report.showFirmLabel(firmLabel);
		report.showAddress(contentProvider.getAddress());
		report.showPhone(contentProvider.getPhone());
		report.showFAX(contentProvider.getFAX());
		report.showEmail(contentProvider.getEmail());
		report.showOfferType(contentProvider.getOfferType());
		report.showIdentifier(contentProvider.getIdentifier());
	}
	
	private String toFirmLabel(String firmName, String vip)
	{
		if (vip.equals(""))
		{
			if (firmName.equals(""))
			{
				return "";
			}
			
			return firmName;
		}
		
		if (firmName.equals(""))
		{
			return String.format("VIP: %s", vip);
		}
		
		return String.format("%s (VIP: %s)", firmName, vip);
	}
}