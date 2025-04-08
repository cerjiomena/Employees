package com.employees.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employees.EmployeesApplication;
import com.employees.domain.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {EmployeesApplication.class})
@Slf4j
class EmployeeTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void testGetListEmployees() {
		log.debug("Entrando a testGetListEmployees");
		
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		
		assertTrue(employees.size()>0);
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
