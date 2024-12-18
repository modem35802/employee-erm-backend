package com.react.springboot.controller;

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

import com.react.springboot.exception.ResourceNotFoundException;
import com.react.springboot.model.Employee;
import com.react.springboot.repository.EmployeeRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@GetMapping("")
	public List<Employee> getAllEmployees(){
		System.out.println("Fetching all employees ...");
		return empRepository.findAll();
	}
	
	// Create new employee
	@PostMapping
	public Employee createNewEmployee(@RequestBody Employee employee) {
		return empRepository.save(employee);
	}
	
	//Get Employee by ID
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
		System.out.println("Fetching employee: " + id + " details ...");
		Employee employee = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with ID: " + id));
		
		return ResponseEntity.ok(employee);
	}
	
	// Update employee by id
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee){
		System.out.println("Updating employee: " + id + " details ...");
		Employee existingEmployee = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with ID: " + id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmailId(employee.getEmailId());
		
		empRepository.save(existingEmployee);
		
		return ResponseEntity.ok(existingEmployee);
		
	}
	
	// Delete employee by id
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable long id){
		Employee employee = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with ID: " + id));
		
		System.out.println("Deleting employee: " + id + " details ...");
		empRepository.deleteById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
