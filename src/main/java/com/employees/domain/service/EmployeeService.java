package com.employees.domain.service;

import java.util.List;

import com.employees.dto.EmployeeDTO;
import com.employees.error.AppException;

public interface EmployeeService {
	/**
	 * Method to get all the employees
	 * @return
	 */
	List<EmployeeDTO> getEmployees();
	
	
	/**
	 * Method to delete the employee by the id
	 * @param id identifier
	 * @throws AppException
	 */
	void deleteEmployeeById(Integer id) throws AppException;
	
	/**
	 * Method to get de employee by id
	 * @param id identifier
	 * @return EmployeeDTO
	 * @throws AppException
	 */
	EmployeeDTO getEmployeeById(Integer id) throws AppException;
	
	
	/**
	 * 
	 * @param employees
	 * @return
	 */
	List<EmployeeDTO> addUsers(List<EmployeeDTO> employees);
	
	/**
	 * 
	 * @param employeeDTO
	 * @return
	 * @throws AppException
	 */
	EmployeeDTO updateUser(EmployeeDTO employeeDTO) throws AppException;

}
