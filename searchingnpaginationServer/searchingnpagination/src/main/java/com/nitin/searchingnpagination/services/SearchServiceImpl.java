package com.nitin.searchingnpagination.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nitin.searchingnpagination.dom.EmployeeDom;
import com.nitin.searchingnpagination.model.Employee;
import com.nitin.searchingnpagination.repository.EmployeeRepository;
import com.nitin.searchingnpagination.util.JpaJPQLUtil;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	EmployeeRepository employeeRepo;
	
	
	public HashMap<String, Object> searchbyEnter(String skeyword ,Integer page, Integer size)
	{
		
		String queryString = "SELECT e.empNo, e.firstName, e.lastName, e.gender, e.birthDate, e.hireDate  FROM Employee e WHERE CONCAT(lower(e.firstName), ' ' ,lower(e.lastName)) LIKE :keyword order by e.firstName, e.lastName ";
		String queryCountString = "SELECT count(e) FROM Employee e WHERE CONCAT(lower(e.firstName), ' ', lower(e.lastName)) LIKE :keyword ";
		HashMap<String, Object> paramter = new HashMap<String, Object>();
		skeyword = "%"+skeyword+"%"; 
		paramter.put("keyword",skeyword);
		HashMap<String, Object> objResult =JpaJPQLUtil.JPQLNitinQueryFeatures(queryString, queryCountString, paramter, EmployeeDom.class, page, size);
		return objResult;
	}
	
	public List<Employee> searchbykeyword(String skeyword)
	{
		 return employeeRepo.search(skeyword.toLowerCase(),PageRequest.of(0, 10)).getContent(); //arcusts.getContent();
	}
	
	
	
}
