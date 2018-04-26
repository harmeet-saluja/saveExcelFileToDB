package com.yash.excelfiledemo.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yash.excelfiledemo.dao.EmployeeDAO;
import com.yash.excelfiledemo.model.Employee;
import com.yash.excelfiledemo.util.DBUtil;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	public boolean saveEmployeeDetails(List<Employee> employees) {
		for (Employee employee : employees) {
			PreparedStatement pstmt = DBUtil
					.getPreparedStatement("INSERT INTO employees(name,email,salary) VALUES (?,?,?)");
			try {
				pstmt.setString(1, employee.getName());
				pstmt.setString(2, employee.getEmail());
				pstmt.setInt(3, employee.getSalary());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
