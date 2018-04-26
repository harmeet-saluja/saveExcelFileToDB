package com.yash.excelfiledemo.model;

public class Employee {
	private int salary;
	private String name;
	private String email;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Name:"+this.name+"\tEmail:"+this.email+"\tSalary:"+this.salary;
	}
}
