package com.react.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.react.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
