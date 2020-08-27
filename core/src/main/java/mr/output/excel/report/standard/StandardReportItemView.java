package mr.output.excel.report.standard;


import mr.item.view.OrderedItemView;
import mr.output.excel.report.ReportView;


public class StandardReportItemView implements OrderedItemView
{
	private final ReportView reportView;
	private final int rowIndex;
	
	StandardReportItemView(ReportView reportView, int rowIndex)
	{
		this.reportView = reportView;
		this.rowIndex = rowIndex;
	}
	
	@Override
	public void showOrdinalNumber(int value)
	{
		reportView.showNumber(rowIndex, 1, value);
	}
	
	@Override
	public void showDescription(String value)
	{
		reportView.showText(rowIndex, 2, value);
	}
	
	@Override
	public void showArticleIdentifier(String value)
	{
		reportView.showText(rowIndex, 3, value);
	}
	
	@Override
	public void showUnitPrice(double value)
	{
		reportView.showNumber(rowIndex, 4, value);
	}
	
	@Override
	public void showItemCount(int value)
	{
		reportView.showNumber(rowIndex, 5, value);
	}
	
	@Override
	public void showTotalPrice(double value)
	{
		reportView.showFormula(rowIndex, 6, String.format("E%1$d*F%1$d", rowIndex + 1));
	}
	
	@Override
	public void showComment(String value)
	{
		reportView.showText(rowIndex, 7, value);
	}
}