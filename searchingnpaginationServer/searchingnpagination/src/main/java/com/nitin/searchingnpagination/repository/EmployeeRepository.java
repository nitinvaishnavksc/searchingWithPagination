package com.nitin.searchingnpagination.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nitin.searchingnpagination.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE lower(e.firstName) LIKE :keyword% or lower(e.lastName) LIKE :keyword% order by e.firstName, e.lastName ")
	public Page<Employee> search(String keyword, Pageable pageable);
	
	@Query("SELECT e FROM Employee e WHERE CONCAT(lower(e.firstName), lower(e.lastName)) LIKE %:keyword% order by e.firstName, e.lastName ")
	public Page<Employee> searchresult(String keyword, Pageable pageable);
	
}
