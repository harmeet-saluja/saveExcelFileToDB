package com.yash.excelfiledemo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.excelfiledemo.model.Employee;

public interface EmployeeService {

	public void extractEmployeeDetailsFromFile(MultipartFile file);

	public boolean saveEmployeeDetails(List<Employee> employeeList);
}
