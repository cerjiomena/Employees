package com.employees.domain.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employees.EmployeesApplication;
import com.employees.domain.service.EmployeeService;
import com.employees.dto.EmployeeDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {EmployeesApplication.class})
@Slf4j
class EmployeeServiceImplTest {
	
	@Autowired
	private EmployeeService employeeService;

	@Test
	void testGetEmployees() {
		
		log.debug("Entrando a EmployeeServiceImplTest.testGetListEmployees");
		
		List<EmployeeDTO> employees = employeeService.getEmployees();
		
		assertTrue(employees.size()>0);
	}

}
