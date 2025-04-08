package com.employees.domain.service;

import java.util.List;

import com.employees.dto.EmployeeDTO;
import com.employees.error.AppException;

public interface EmployeeService {
	
	List<EmployeeDTO> getEmployees();
	
	void deleteEmployeeById(Integer id) throws AppException;
	
	EmployeeDTO getEmployeeById(Integer id) throws AppException;
	
	List<EmployeeDTO> addUsers(List<EmployeeDTO> employees);

}
