package com.nitin.searchingnpagination.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nitin.searchingnpagination.model.Employee;

@Service
public interface SearchService {
	
	public List<Employee> searchbykeyword(String skeyword);
	public HashMap<String, Object> searchbyEnter(String skeyword,Integer page, Integer size);
	

}
