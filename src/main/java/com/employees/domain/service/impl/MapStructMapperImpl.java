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
		
		employeeDTO.setApellidoMaterno(employee.getApellidoMaterno());
		
		return employeeDTO;
	}

}
