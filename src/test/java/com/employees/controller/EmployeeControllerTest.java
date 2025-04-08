package com.employees.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.employees.EmployeesApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {EmployeesApplication.class})
@AutoConfigureMockMvc
@Slf4j
class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetEmployees() throws Exception {
		log.debug("Enter to EmployeeControllerTest.testGetEmployees");
		MvcResult result =  mockMvc.perform(get("/api/v1/employees")).andExpect(status().isOk()).andReturn();
				
		MockHttpServletResponse response = result.getResponse();
				 
		JSONObject json = new JSONObject(response.getContentAsString());
				 
		log.debug(response.getContentAsString());
		
	}

}
