package com.erm.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erm.springboot.exception.ResourceNotFoundException;
import com.erm.springboot.model.Employee;
import com.erm.springboot.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}
	
	public Employee saveEmployee(Employee employee) {
		employee = empRepository.save(employee);
		
		return employee;
	}
	
	public Employee getEmployeeById(Long empID) {
		Employee employee = empRepository.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with ID: " + empID));
		
		return employee;
	}
	
	public Employee updateEmployee(Long empID, Employee employee) {
		Employee existingEmployee = empRepository.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with ID: " + empID));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmailId(employee.getEmailId());
	
		empRepository.save(existingEmployee);
		
		return existingEmployee;
		
	}
	
	public String deleteEmployeeById(long empID) {
		Employee employee = empRepository.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with ID: " + empID));
		
		empRepository.deleteById(empID);
		
		return "Successfully deleted";
	}
}
