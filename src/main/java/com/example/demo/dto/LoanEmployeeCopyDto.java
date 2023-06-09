package com.example.demo.dto;

import com.example.demo.entity.Copy;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Loan;

public class LoanEmployeeCopyDto {

	long employeeID;
	long boekID;
	long copyID;
	String EmployeeVoornaam;
	String EmployeeAchternaam;
	
	public long getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}


	public long getBoekID() {
		return boekID;
	}


	public void setBoekID(long boekID) {
		this.boekID = boekID;
	}


	public long getCopyID() {
		return copyID;
	}


	public void setCopyID(long copyID) {
		this.copyID = copyID;
	}


	public String getEmployeeVoornaam() {
		return EmployeeVoornaam;
	}


	public void setEmployeeVoornaam(String employeeVoornaam) {
		EmployeeVoornaam = employeeVoornaam;
	}


	public String getEmployeeAchternaam() {
		return EmployeeAchternaam;
	}


	public void setEmployeeAchternaam(String employeeAchternaam) {
		EmployeeAchternaam = employeeAchternaam;
	}


	public String getBoeknaam() {
		return Boeknaam;
	}


	public void setBoeknaam(String boeknaam) {
		Boeknaam = boeknaam;
	}


	String Boeknaam;
	
	
	public LoanEmployeeCopyDto(Loan lening) {
		this.Boeknaam = lening.getCopy().getBook().getTitle();
		this.EmployeeVoornaam = lening.getEmployee().getFirstName();
		this.EmployeeAchternaam = lening.getEmployee().getLastName();
		this.boekID = lening.getCopy().getBook().getId();
		this.copyID = lening.getCopy().getId();
	}
	
	public Employee getEmployee(Employee employee) {
		employee.setFirstName(this.EmployeeVoornaam);
		employee.setLastName(this.EmployeeAchternaam);
		return employee;
	}
	
	//en andere objecten
}
