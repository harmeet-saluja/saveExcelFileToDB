package com.yash.excelfiledemo.demo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.Part;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileDemo {
	
//	System.out.println("Hello");
//	Part filePart = request.getPart("file");
//	try {
//		Workbook workbook = WorkbookFactory.create(filePart.getInputStream());
//	} catch (EncryptedDocumentException e) {
//		e.printStackTrace();
//	} catch (InvalidFormatException e) {
//		e.printStackTrace();
//	}

	private static File file = new File("D:\\files\\demo.xlsx");

	public static void main(String[] args) {
		try {
			Workbook workbook = WorkbookFactory.create(file);
			System.out.println(workbook);
			System.out.println(workbook.getNumberOfSheets());

			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();
				System.out.println(sheet.getSheetName());
			}

			Sheet sheet = workbook.getSheetAt(0);
			System.out.println(sheet.getSheetName());

			DataFormatter dataFormatter = new DataFormatter();

			for (Row row : sheet) {
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					System.out.print(cellValue + "\t");
				}
				System.out.println();
			}
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
