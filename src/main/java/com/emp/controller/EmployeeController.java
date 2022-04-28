package com.emp.controller;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.emp.dao.DepartmentRepository;
import com.emp.dao.EmployeeRepository;
import com.emp.model.Department;
import com.emp.model.Employee;
import com.emp.model.Project;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    
    @Autowired
    DepartmentRepository repository;
    
    @Autowired
    EmployeeRepository emprepository;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/emplist")
    public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = emprepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/department/{departmentId}/with-projects")
    public List<Department> findByDepartmentWithprojects(@PathVariable("departmentId") String departmentId) {
        LOGGER.info("Department find: departmentId={}", departmentId);
        List<Department> depts = repository.findByDeptId(departmentId);
        depts.forEach(d -> d.setProjects(findProjectsByDepartment(d.getId())));
        return depts;
    }
    
    private List<Project> findProjectsByDepartment(String projectId) {
    	Project[] projects = restTemplate
                .getForObject("http://department//project/{projectId}", Project[].class, projectId);
        return Arrays.asList(projects);
    }
    
    @GetMapping("/employee/{employeesId}/with-projects")
    public List<Employee> findByEmployeeWithprojects(@PathVariable("empId") String empId) {
        LOGGER.info("Department find: employeeId={}", empId);
        List<Employee> employees = repository.findByempId(empId);
        employees.forEach(d -> d.setProjects(findProjectsByemployee(d.getId())));
        return employees;
    }
    
    private List<Project> findProjectsByemployee(String projectId) {
    	Project[] projects = restTemplate
                .getForObject("http://employee//project/{projectId}", Project[].class, projectId);
        return Arrays.asList(projects);
    }
    
    @GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);
		return emprepository.findByDepartmentId(departmentId);
	}
   
    @GetMapping("/employee/{employeesId}/with-departments")
    public List<Employee> findByEmployeeWithDepartments(@PathVariable("empId") String empId) {
        LOGGER.info("Department find: employeeId={}", empId);
        List<Employee> employees = repository.findByempId(empId);
        employees.forEach(d -> d.setProjects(findDepartmentsByemployee(d.getId())));
        return employees;
    }
    
    private List<Project> findDepartmentsByemployee(String deptId) {
    	Project[] projects = restTemplate
                .getForObject("http://employee//department/{departmentId}", Project[].class, deptId);
        return Arrays.asList(projects);
    }
    
}