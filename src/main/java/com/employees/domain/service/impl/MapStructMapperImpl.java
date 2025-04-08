package com.employees.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.employees.domain.model.Employee;
import com.employees.domain.service.MapStructMapper;
import com.employees.dto.EmployeeDTO;

@Component
public class MapStructMapperImpl implements MapStructMapper {

	@Override
	public List<EmployeeDTO> employeesToEmployeesDtos(List<Employee> employees) {
		
		List<EmployeeDTO> list = null;
		
		if(employees == null) {
			return list;
		}
		
		list = new ArrayList<EmployeeDTO>(employees.size());
		
		for (Employee employee: employees) {
			
			list.add(employeeToEmployeeDto(employee));
			
		}
		
		return list;
	}

	@Override
	public EmployeeDTO employeeToEmployeeDto(Employee employee) {
		
		EmployeeDTO employeeDTO = null;
		
		if(employee == null) {
			return employeeDTO;
		}
		
		
		employeeDTO = new EmployeeDTO();
		
		employeeDTO.setId(employee.getId());
		employeeDTO.setApellidoMaterno(employee.getApellidoMaterno());
		employeeDTO.setEdad(employee.getEdad());
		
		return employeeDTO;
	}

	@Override
	public List<Employee> employeesDTosToEmployees(List<EmployeeDTO> employeesDtos) {
		List<Employee> list = null;
		
		if(employeesDtos == null) {
			return list;
		}
		
		list = new ArrayList<Employee>(employeesDtos.size());
		
		for (EmployeeDTO employeeDTO: employeesDtos) {
			
			list.add(employeeDtoToEmployee(employeeDTO));
			
		}
		
		return list;
	}
	@Override
	public Employee employeeDtoToEmployee(EmployeeDTO employeeDTO) {
		
		Employee employee = null;
		
		if(employeeDTO == null) {
			return employee;
		}
		
		
		employee = new Employee();
		
		employee.setId(employeeDTO.getId());
		employee.setApellidoMaterno(employeeDTO.getApellidoMaterno());
		employee.setEdad(employeeDTO.getEdad());
		
		
		return employee;
	}

}
