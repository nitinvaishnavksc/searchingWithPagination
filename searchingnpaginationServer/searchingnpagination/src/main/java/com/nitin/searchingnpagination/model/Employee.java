package com.nitin.searchingnpagination.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees",
indexes = { 
        @Index( 
          name = "index_first_name", 
          columnList="first_name", 
          unique = false 
        ), 

        @Index( 
          name = "index_last_name", 
          columnList="last_name", 
          unique = false 
        ) 
      }
		)
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="emp_no")
	private Integer empNo;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="hire_date")
	private Date hireDate;

	@Column(name="last_name")
	private String lastName;

	public Employee() {
	}

	public Integer getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}