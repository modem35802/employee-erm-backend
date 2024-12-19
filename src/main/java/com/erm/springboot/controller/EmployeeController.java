package com.erm.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erm.springboot.model.Employee;
import com.erm.springboot.service.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	// Create new employee
	@PostMapping
	public Employee createNewEmployee(@RequestBody Employee employee) {
		return empService.saveEmployee(employee);
	}
	
	//Get Employee by ID
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
		Employee employee = empService.getEmployeeById(id);
		
		return ResponseEntity.ok(employee);
	}
	
	// Update employee by id
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee){
		Employee updatedEmployee = empService.updateEmployee(id, employee);
		
		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}
	
	// Delete employee by id
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable long id){
		String message = empService.deleteEmployeeById(id);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
