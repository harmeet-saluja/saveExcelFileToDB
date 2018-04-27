package com.yash.excelfiledemo.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
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

	@Scheduled(cron = "0 10 16 * * * ")
	public void dailyBackup() {
		PreparedStatement pstmt = DBUtil.getPreparedStatement(
				"INSERT INTO `test`.`employee_backup` (SELECT * FROM test.`employees` WHERE `test`.`employees`.`name` NOT IN (SELECT `test`.`employees`.`name` FROM `test`.`employees` INNER JOIN `test`.`employee_backup` ON `test`.`employees`.`name`=`test`.`employee_backup`.`name`))");
		try {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
