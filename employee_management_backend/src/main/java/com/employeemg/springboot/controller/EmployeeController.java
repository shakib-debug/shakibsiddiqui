package com.employeemg.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import com.employeemg.springboot.repository.EmployeeRepository;
import com.employeemg.springboot.exception.ResourceNotFoundException;
import com.employeemg.springboot.model.Employee;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}	
	
	//create employee Rest API
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	//get employee by ID
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
	{
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with id "+id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee details
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeDetail(@PathVariable Long id,@RequestBody Employee employeeDetails)
	{
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with id "+id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setManagerName(employeeDetails.getManagerName());
		
		Employee updateEmployee=employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
		
	}
	
	//Delete Employee from database
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id)
	{
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not exist with id "+id));
		employeeRepository.delete(employee);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
