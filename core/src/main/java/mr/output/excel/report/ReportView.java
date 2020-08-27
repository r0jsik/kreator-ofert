package mr.output.excel.report;


import java.io.File;
import java.time.LocalDate;


public interface ReportView
{
	void showDate(int rowIndex, int columnIndex, LocalDate value);
	void showText(int rowIndex, int columnIndex, String value);
	void showNumber(int rowIndex, int columnIndex, double value);
	void showFormula(int rowIndex, int columnIndex, String value);
	void showImage(int rowIndex, int columnIndex, File file);
	void extendMatrix(int rowIndex, int extraRowCount);
}