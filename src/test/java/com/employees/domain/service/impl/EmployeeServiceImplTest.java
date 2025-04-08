package com.employees.domain.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
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
		
		log.debug("Enter to EmployeeServiceImplTest.testGetListEmployees");
		
		List<EmployeeDTO> employees = employeeService.getEmployees();
		
		assertTrue(employees.size()>0);
	}
	
	@Test
	void testDeleteEmployeeById() throws AppException {
	    log.debug("Enter to EmployeeServiceImplTest.testDeleteEmployeeById");

	    // be sure that the employee exists
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
	                "The code should be ERROR_EMPLOYEE_DOES_NOT_EXIST");
	}
	
	@Test
	void testAddUsers() throws AppException {
	    log.debug("Entrando a EmployeeServiceImplTest.testAddUsers");

	    // Crear el primer empleado DTO
	    EmployeeDTO emp1 = new EmployeeDTO();
	    emp1.setPrimerNombre("Juan");
	    emp1.setSegundoNombre("Antonio");
	    emp1.setApellidoPaterno("García");
	    emp1.setApellidoMaterno("López");
	    emp1.setEdad(30);
	    emp1.setSexo("M");
	    emp1.setFechaNacimiento(new Date());
	    emp1.setPuesto("Desarrollador");

	    // Crear el segundo empleado DTO
	    EmployeeDTO emp2 = new EmployeeDTO();
	    emp2.setPrimerNombre("María");
	    emp2.setSegundoNombre("Guadalupe");
	    emp2.setApellidoPaterno("Rodríguez");
	    emp2.setApellidoMaterno("Sánchez");
	    emp2.setEdad(28);
	    emp2.setSexo("F");
	    emp2.setFechaNacimiento(new Date());
	    emp2.setPuesto("Diseñadora");

	    // Añadir los empleados a una lista
	    List<EmployeeDTO> employeesToAdd = new ArrayList<>();
	    employeesToAdd.add(emp1);
	    employeesToAdd.add(emp2);

	    // Llamar al servicio para añadir los usuarios
	    List<EmployeeDTO> addedEmployees = employeeService.addUsers(employeesToAdd);

	    // Verificar que la lista devuelta no es nula y tiene el tamaño correcto
	    assertNotNull(addedEmployees, "La lista de empleados añadidos no debería ser nula");
	    assertEquals(2, addedEmployees.size(), "Deberían haberse añadido 2 empleados");

	    // Verificar que los empleados tienen IDs asignados (no nulos)
	    assertNotNull(addedEmployees.get(0).getId(), "El primer empleado debería tener un ID asignado");
	    assertNotNull(addedEmployees.get(1).getId(), "El segundo empleado debería tener un ID asignado");

	    // Si quieres, puedes verificar que puedes recuperar los empleados por ID
	    EmployeeDTO retrievedEmp1 = employeeService.getEmployeeById(addedEmployees.get(0).getId());
	    assertNotNull(retrievedEmp1, "El primer empleado debería poder recuperarse por ID");
	    assertEquals("Juan", retrievedEmp1.getPrimerNombre(), "El nombre del primer empleado debería ser Juan");
	}

}
