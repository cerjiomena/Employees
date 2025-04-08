package com.employees.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.employees.EmployeesApplication;
import com.employees.domain.service.EmployeeService;
import com.employees.dto.EmployeeDTO;
import com.employees.error.AppException;
import com.employees.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {EmployeesApplication.class})
@AutoConfigureMockMvc
@Slf4j
class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EmployeeService employeeService;
	
	private List<EmployeeDTO> addedEmployeesDTOs = null;
	
	@BeforeEach
	void setUp() {
		log.debug("Executing config before each test");
		EmployeeDTO employeeDTO =new EmployeeDTO();
		employeeDTO.setApellidoMaterno("Hernandez");
		employeeDTO.setApellidoPaterno("Hernandez");
		employeeDTO.setFechaNacimiento(new Date());
		employeeDTO.setPrimerNombre("Luis");
		employeeDTO.setSegundoNombre("Angel");
		employeeDTO.setPuesto("Contador");
		employeeDTO.setSexo("Masculino");
		List<EmployeeDTO> employeesToAdd = new ArrayList<>();
	    employeesToAdd.add(employeeDTO);
	    this.addedEmployeesDTOs = employeeService.addUsers(employeesToAdd);
		
	}
	
	
	@AfterEach
	void tearDown() {
		log.debug("Executing tearDown ");
		
		try {
			
			if(this.addedEmployeesDTOs != null && !this.addedEmployeesDTOs.isEmpty()) {
				EmployeeDTO employeeDTO = this.addedEmployeesDTOs.get(0);
				
				try {
					
					employeeService.getEmployeeById(employeeDTO.getId());
					
					employeeService.deleteEmployeeById(employeeDTO.getId());
					
				} catch(AppException e) {
					log.debug("The employee was removed");
				}
			}
			
		} catch (Exception e) {
			log.error("Error inside tearDown");
			
		}
		
	}

	@Test
	void testGetEmployees() throws Exception {
		log.debug("Enter to EmployeeControllerTest.testGetEmployees");
		MvcResult result =  mockMvc.perform(get("/api/v1/employees")).andExpect(status().isOk()).andReturn();
				
		MockHttpServletResponse response = result.getResponse();
				 
		JSONObject json = new JSONObject(response.getContentAsString());
				 
		log.debug(response.getContentAsString());
		
		assertTrue(json.has("status"), "the response should be have the field 'status'");
		assertTrue(json.has("employees"), "the response should be have the field 'employees'");
		
	}
	
	@Test
	void testDeleteUser() throws Exception {
		log.debug("Enter to EmployeeControllerTest.testDeleteUser");
		EmployeeDTO employeeDTO =  this.addedEmployeesDTOs.get(0);
		MvcResult result =  mockMvc.perform(delete("/api/v1/employee/{id}", 
				String.valueOf(employeeDTO.getId()))).andExpect(status().isOk()).andReturn();
		
	    // Obtener la respuesta
	    MockHttpServletResponse response = result.getResponse();
	    String content = response.getContentAsString();
	    log.debug("Respuesta: " + content);
	    
	    // Analizar el JSON de respuesta
	    JSONObject jsonResponse = new JSONObject(content);
	    
	    // Verificar el contenido de la respuesta
	    assertTrue(jsonResponse.has("status"), "La respuesta debe tener un campo 'status'");
	    assertTrue(jsonResponse.has("message"), "La respuesta debe tener un campo 'message'");
	    assertEquals(Constants.SUCCESS, jsonResponse.getInt("status"), "El status debe ser SUCCESS");
	    
	    // Verificar que el mensaje indica éxito
	    String expectedMessage = "Employee deleted successfully";
	    assertEquals(expectedMessage, jsonResponse.getString("message"), 
	                "El mensaje debe indicar que el empleado se eliminó correctamente");
	}
	
	@Test
	void updateUser() throws Exception {
	    log.debug("Enter to EmployeeControllerTest.updateUser");
	    
	    // Obtener el empleado de prueba
	    EmployeeDTO employeeDTO = this.addedEmployeesDTOs.get(0);
	    
	    // Modificar algunos datos del empleado
	    employeeDTO.setPrimerNombre("NombreActualizado");
	    employeeDTO.setPuesto("PuestoActualizado");
	    
	    // Convertir el objeto a JSON usando una biblioteca adecuada (Jackson, Gson, etc.)
	    ObjectMapper objectMapper = new ObjectMapper();
	    String employeeJson = objectMapper.writeValueAsString(employeeDTO);
	    
	    // Ejecutar la solicitud PUT con el JSON del empleado actualizado
	    MvcResult result = mockMvc.perform(put("/api/v1/employee")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(employeeJson))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andReturn();
	    
	    // Verificar la respuesta
	    MockHttpServletResponse response = result.getResponse();
	    String content = response.getContentAsString();
	    log.debug("Respuesta: " + content);
	    
	    // Analizar el JSON de respuesta
	    JSONObject jsonResponse = new JSONObject(content);
	    
	    // Verificar que la actualización fue exitosa
	    assertEquals(Constants.SUCCESS, jsonResponse.getInt("status"), "El status debe ser SUCCESS");
	    
	    // Verificar que el mensaje es el esperado
	    String expectedMessage = "Employee updated successfully"; 
	    assertTrue(jsonResponse.getString("message").contains("success"), 
	            "El mensaje debe indicar actualización exitosa");
	    
	    // Opcionalmente, verificar que los datos se actualizaron realmente
	    // Obtener el empleado actualizado
	    /*MvcResult getResult = mockMvc.perform(get("/api/v1/employee/" + employeeDTO.getId()))
	            .andExpect(status().isOk())
	            .andReturn();
	    
	    JSONObject getResponse = new JSONObject(getResult.getResponse().getContentAsString());
	    JSONObject updatedEmployee = getResponse.getJSONObject("data");
	    
	    assertEquals("NombreActualizado", updatedEmployee.getString("primerNombre"), 
	            "El primer nombre debería haberse actualizado");
	    assertEquals("PuestoActualizado", updatedEmployee.getString("puesto"), 
	            "El puesto debería haberse actualizado");*/
	}
}
