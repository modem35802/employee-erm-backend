package com.erm.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erm.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
