package com.employees.domain.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employees.EmployeesApplication;
import com.employees.domain.service.EmployeeService;
import com.employees.dto.EmployeeDTO;
import com.employees.error.AppException;
import com.employees.util.MessageError;

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
	
	@Test
	void testDeleteEmployeeById() throws AppException {
	    log.debug("Entrando a EmployeeServiceImplTest.testDeleteEmployeeById");

	    // be sure that teh employee exists
	    EmployeeDTO employee = employeeService.getEmployeeById(Integer.valueOf(1));
	    assertNotNull(employee, "El empleado debería existir antes de la eliminación");

	    // Delete employee
	    employeeService.deleteEmployeeById(Integer.valueOf(1));

	    // Verify the exception
	    AppException exception = assertThrows(
	        AppException.class,
	        () -> employeeService.getEmployeeById(Integer.valueOf(1)),
	        "Se debería lanzar AppException al buscar un empleado eliminado"
	    );

	    // Opcional: verify the specific error
	    assertEquals(MessageError.ERROR_EMPLOYEE_DOES_NOT_EXIST, exception.getCodeError(),
	                "El código de error debería ser ERROR_EMPLOYEE_DOES_NOT_EXIST");
	}

}
