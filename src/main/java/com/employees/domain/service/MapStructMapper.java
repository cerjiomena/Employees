package com.employees.domain.service;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import com.employees.domain.model.Employee;
import com.employees.dto.EmployeeDTO;

@Mapper(
	    componentModel = "spring",
	    implementationName = "mainMapperImpl" 
	)
@Primary
public interface MapStructMapper {
	
	List<EmployeeDTO> employeesToEmployeesDtos(List<Employee> employees);
	
	EmployeeDTO employeeToEmployeeDto(Employee employee);

}
