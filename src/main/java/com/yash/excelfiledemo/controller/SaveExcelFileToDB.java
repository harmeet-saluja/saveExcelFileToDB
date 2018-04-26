package com.yash.excelfiledemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.excelfiledemo.service.EmployeeService;

@RestController
@RequestMapping("/file")
public class SaveExcelFileToDB {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
	public void saveFile(@RequestAttribute("file") MultipartFile file) {
		if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
			employeeService.extractEmployeeDetailsFromFile(file);
		else
			System.out.println("Invalid File Type");

	}
}
