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
	 * Method to get the employee by id
	 * @param id identifier
	 * @return EmployeeDTO  the object obtained
	 * @throws AppException
	 */
	EmployeeDTO getEmployeeById(Integer id) throws AppException;
	
	
	/**
	 * Method to addUser an element or list of elements
	 * @param employees
	 * @return the list of objects including the ids
	 * @throws AppException 
	 */
	List<EmployeeDTO> addUsers(List<EmployeeDTO> employees) throws AppException;
	
	/**
	 * Method to update user
	 * @param employeeDTO class of transport with the info
	 * @return the same Object with the updated data
	 * @throws AppException
	 */
	EmployeeDTO updateUser(EmployeeDTO employeeDTO) throws AppException;

}
