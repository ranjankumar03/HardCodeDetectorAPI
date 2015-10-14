package com.hardcode.detect.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

import com.hardcode.detect.constants.ApplicationConstant;
import com.hardcode.detect.pojo.FileDesc;

public class XLSFileWriter implements FileWriter {

	private FileOutputStream fileOut;

	@Override
	public void write(String outputFileLocation, Set<FileDesc> result) {
		;
		try {
			fileOut = new FileOutputStream(outputFileLocation);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle headerStyle = wb.createCellStyle();

		HSSFSheet sheet3 = wb.createSheet("HARDCODE_DESC");
		HSSFFont headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		headerStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
		// headerStyle.setAlignment(c);
		headerStyle.setFont(headerFont);

		int excelRowNum = 0;
		// writeheader
		HSSFRow headerRow = sheet3.createRow(excelRowNum);

		HSSFCell fileLocationCell = headerRow.createCell(0);
		fileLocationCell.setCellStyle(headerStyle);
		fileLocationCell.setCellValue("FILE LOCATION");

		HSSFCell lineNumberCell = headerRow.createCell(1);
		lineNumberCell.setCellStyle(headerStyle);
		lineNumberCell.setCellValue("LINE NUMBER");

		HSSFCell hardCodedTextell = headerRow.createCell(2);
		hardCodedTextell.setCellStyle(headerStyle);
		hardCodedTextell.setCellValue("HARD CODED TEXT");

		HSSFCell hardCodedTextel2 = headerRow.createCell(3);
		hardCodedTextel2.setCellStyle(headerStyle);
		hardCodedTextel2.setCellValue("SEVERITY");
		// end header
		excelRowNum++;

		for (FileDesc object : result) {

			HSSFRow nextrow = sheet3.createRow(excelRowNum);
			nextrow.createCell(ApplicationConstant.ZERO).setCellValue(
					object.getFileLocation());
			nextrow.createCell(ApplicationConstant.ONE).setCellValue(
					object.getLineNumber());
			nextrow.createCell(ApplicationConstant.TWO).setCellValue(
					object.getLineText());
			nextrow.createCell(ApplicationConstant.THREE).setCellValue(
					object.getSeverity());
			excelRowNum++;

		}

		sheet3.autoSizeColumn(ApplicationConstant.ZERO);
		sheet3.autoSizeColumn(ApplicationConstant.ONE);
		sheet3.autoSizeColumn(ApplicationConstant.TWO);
		sheet3.autoSizeColumn(ApplicationConstant.THREE);

		try {
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
