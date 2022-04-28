package com.emp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.emp.model.Department;
import com.emp.model.Employee;

public interface DepartmentRepository extends CrudRepository<Department, String> {

	List<Department> findByDeptId(String deptId);
	
	List<Employee> findByempId(String empId);
	
}