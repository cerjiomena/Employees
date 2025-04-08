package com.employees.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.employees.domain.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
