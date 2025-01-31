package com.nitin.searchingnpagination.dom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EmployeeDom {
	
	private Integer empNo;
	private String firstName;
	private String lastName;
	private String gender;
	private String birthDate;
	private String hireDate;
	
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public EmployeeDom(Object[] obj) {
		this.empNo = (Integer) obj[0];
		this.firstName = (String) obj[1];
		this.lastName = (String) obj[2];
		this.gender = (String) obj[3];
		this.birthDate = datatoString((Date) obj[4]);
		this.hireDate = datatoString((Date) obj[5]);
	}
	private String datatoString(Date date) 
	{
		if(date!=null) {
		return new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault()).format(date);
		}
		else {return "";}
	}
	
}
