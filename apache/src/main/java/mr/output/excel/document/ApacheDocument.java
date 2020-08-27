package mr.output.excel.document;


import java.io.*;
import java.time.*;
import java.util.Date;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;


public class ApacheDocument implements Document
{
	private final Workbook workbook;
	private final Sheet sheet;
	
	ApacheDocument(File templatesFile, String templateName) throws IOException
	{
		workbook = WorkbookFactory.create(templatesFile);
		sheet = workbook.getSheet(templateName);
		
		if (sheet == null)
		{
			throw new DocumentCreationException("Nie znaleziono szablonu: " + templateName);
		}
	}
	
	@Override
	public void showDate(int rowIndex, int columnIndex, LocalDate value)
	{
		Date date = toDate(value);
		Cell cell = getCell(rowIndex, columnIndex);
		cell.setCellValue(date);
	}
	
	private Date toDate(LocalDate localDate)
	{
		ZoneId timeZone = ZoneId.systemDefault();
		ZonedDateTime zonedDate = localDate.atStartOfDay(timeZone);
		Instant instant = zonedDate.toInstant();
		
		return Date.from(instant);
	}
	
	private Cell getCell(int rowIndex, int columnIndex)
	{
		Row row = getRow(rowIndex);
		Cell cell = row.getCell(columnIndex);
		
		if (cell == null)
		{
			cell = row.createCell(columnIndex);
		}
		
		return cell;
	}
	
	private Row getRow(int rowIndex)
	{
		Row row = sheet.getRow(rowIndex);
		
		if (row == null)
		{
			row = sheet.createRow(rowIndex);
		}
		
		return row;
	}
	
	@Override
	public void showText(int rowIndex, int columnIndex, String value)
	{
		Cell cell = getCell(rowIndex, columnIndex);
		cell.setCellValue(value);
	}
	
	@Override
	public void showNumber(int rowIndex, int columnIndex, double value)
	{
		Cell cell = getCell(rowIndex, columnIndex);
		cell.setCellValue(value);
	}
	
	@Override
	public void showFormula(int rowIndex, int columnIndex, String value)
	{
		Cell cell = getCell(rowIndex, columnIndex);
		cell.setCellFormula(value);
	}
	
	@Override
	public void showImage(int rowIndex, int columnIndex, File file)
	{
		byte[] image = loadImage(file);
		int objectID = workbook.addPicture(image, Workbook.PICTURE_TYPE_PNG);
		
		Drawing<?> drawing = sheet.createDrawingPatriarch();
		ClientAnchor anchor = createAnchor(rowIndex, columnIndex);
		
		Picture picture = drawing.createPicture(anchor, objectID);
		picture.resize();
	}
	
	private byte[] loadImage(File file)
	{
		try
		{
			return IOUtils.toByteArray(new FileInputStream(file));
		}
		catch (IOException exception)
		{
			return new byte[0];
		}
	}
	
	private ClientAnchor createAnchor(int rowIndex, int columnIndex)
	{
		CreationHelper helper = workbook.getCreationHelper();
		ClientAnchor anchor = helper.createClientAnchor();
		
		anchor.setRow1(rowIndex);
		anchor.setCol1(columnIndex);
		
		return anchor;
	}
	
	@Override
	public void extendMatrix(int rowIndex, int extraRowCount)
	{
		sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), extraRowCount, true, true);
		
		for (int i = 0; i < extraRowCount + 1; i++)
		{
			Row row = getRow(rowIndex + i);
			row.setHeight((short) -1);
		}
		
		extendMatrixStyle(rowIndex, extraRowCount, getRow(rowIndex));
	}
	
	private void extendMatrixStyle(int rowIndex, int rowCount, Row predecessor)
	{
		for (int i = predecessor.getFirstCellNum(); i < predecessor.getLastCellNum(); i++)
		{
			Cell predecessorCell = predecessor.getCell(i);
			CellStyle predecessorCellStyle = predecessorCell.getCellStyle();
			
			for (int j = 0; j < rowCount; j++)
			{
				Cell cell = getCell(rowIndex + j + 1, i);
				cell.setCellStyle(predecessorCellStyle);
			}
		}
	}
	
	@Override
	public void exportTo(File file) throws IOException
	{
		FileOutputStream output = new FileOutputStream(file);
		
		workbook.write(output);
		
		output.close();
	}
	
	@Override
	public void close() throws IOException
	{
		workbook.close();
	}
}