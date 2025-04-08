package com.employees.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.employees.domain.model.Employee;
import com.employees.domain.repository.EmployeeRepository;
import com.employees.domain.service.EmployeeService;
import com.employees.domain.service.MapStructMapper;
import com.employees.dto.EmployeeDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("mainMapperImpl")
	private MapStructMapper mapStructMapper;

	@Override
	public List<EmployeeDTO> getEmployees() {
		
		if(log.isDebugEnabled())
			log.debug("Enter to EmployeeServiceImpl.getEmployees ");
		
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		
		
		return mapStructMapper.employeesToEmployeesDtos(employees);
	}

}
