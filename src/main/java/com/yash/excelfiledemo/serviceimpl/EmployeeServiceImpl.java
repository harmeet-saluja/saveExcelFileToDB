package com.yash.excelfiledemo.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.excelfiledemo.dao.EmployeeDAO;
import com.yash.excelfiledemo.model.Employee;
import com.yash.excelfiledemo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;

	private List<Employee> employeeList = null;

	public void extractEmployeeDetailsFromFile(MultipartFile file) {
		employeeList = new ArrayList<Employee>();
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();

			for (Row row : sheet) {
				Employee employee = new Employee();
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					if (cell.getColumnIndex() == 0)
						employee.setName(cellValue);
					if (cell.getColumnIndex() == 1)
						employee.setEmail(cellValue);
					if (cell.getColumnIndex() == 2)
						employee.setSalary(Integer.parseInt(cellValue));
				}
				employeeList.add(employee);
			}

			this.saveEmployeeDetails(employeeList);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean saveEmployeeDetails(List<Employee> employeeList) {
		return employeeDAO.saveEmployeeDetails(employeeList);
	}

}
