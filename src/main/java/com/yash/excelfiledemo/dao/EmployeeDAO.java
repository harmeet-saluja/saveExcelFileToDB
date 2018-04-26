package com.yash.excelfiledemo.dao;

import java.util.List;

import com.yash.excelfiledemo.model.Employee;

public interface EmployeeDAO {

	public boolean saveEmployeeDetails(List<Employee> employees);
}
