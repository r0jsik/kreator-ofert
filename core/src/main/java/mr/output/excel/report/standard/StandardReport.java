package mr.output.excel.report.standard;


import java.io.File;
import java.time.LocalDate;
import java.util.List;

import mr.item.Item;
import mr.item.view.OrderedItemView;
import mr.output.excel.Agent;
import mr.output.excel.report.Report;
import mr.output.excel.report.ReportView;


public class StandardReport implements Report
{
	private static final File footerFile = new File("report-footer.png");
	
	private final ReportView reportView;
	
	private int itemsRowsShifted;
	
	public StandardReport(ReportView reportView)
	{
		this.reportView = reportView;
	}
	
	@Override
	public void showCreationDate(LocalDate value)
	{
		reportView.showDate(6, 7, value);
	}
	
	@Override
	public void showPaymentType(String value)
	{
		reportView.showText(7, 7, value);
	}
	
	@Override
	public void showDeliveryType(String value)
	{
		reportView.showText(8, 7, value);
	}
	
	@Override
	public void showDeliveryTime(String value)
	{
		reportView.showText(9, 7, value);
	}
	
	@Override
	public void showExpirationDate(LocalDate value)
	{
		reportView.showDate(10, 7, value);
	}
	
	@Override
	public void showReceiver(String value)
	{
		reportView.showText(17, 1, value.equals("")? "" : "Sz.P. " + value);
	}
	
	@Override
	public void showFirmLabel(String value)
	{
		reportView.showText(18, 1, value);
	}
	
	@Override
	public void showAddress(String value)
	{
		reportView.showText(19, 1, value);
	}
	
	@Override
	public void showPhone(String value)
	{
		reportView.showText(17, 4, value);
	}
	
	@Override
	public void showFAX(String value)
	{
		reportView.showText(18, 4, value);
	}
	
	@Override
	public void showEmail(String value)
	{
		reportView.showText(19, 4, value);
	}
	
	@Override
	public void showOfferType(String value)
	{
		reportView.showText(23, 2, value);
	}
	
	@Override
	public void showIdentifier(String value)
	{
		reportView.showText(23, 3, "Nr " + value);
	}
	
	@Override
	public void showAgents(Agent tradesman, Agent assistant)
	{
		showAgent(tradesman, 13);
		showAgent(assistant, 14);
	}
	
	private void showAgent(Agent agent, int rowIndex)
	{
		agent.withPersonalData((position, label) -> {
			reportView.showText(rowIndex, 1, position);
			reportView.showText(rowIndex, 3, label);
		});
	}
	
	@Override
	public void showItems(List<Item> items)
	{
		if (items.size() > 1)
		{
			reportView.extendMatrix(25, items.size() - 1);
			itemsRowsShifted += items.size() - 1;
		}
		
		for (int i = 0; i < items.size(); i++)
		{
			OrderedItemView orderedItemView = new StandardReportItemView(reportView, i + 25);
			orderedItemView.showOrdinalNumber(i + 1);
			
			Item item = items.get(i);
			item.showOn(orderedItemView);
		}
	}
	
	@Override
	public void showTotalPrice()
	{
		reportView.showFormula(26 + itemsRowsShifted, 6, String.format("SUM(G26:G%d)", 26 + itemsRowsShifted));
	}
	
	@Override
	public void showFooter()
	{
		reportView.showImage(49 + itemsRowsShifted, 1, footerFile);
	}
}