package com.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.domain.service.EmployeeService;
import com.employees.dto.EmployeeDTO;
import com.employees.util.Constants;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class EmployeeController {
	
	@Autowired
	private EmployeeService  employeeService;
	
	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getListEmployees() {
		if (log.isDebugEnabled())
			log.debug(">> Enter to EmployeeController.getListEmployees << ");
		
		List<EmployeeDTO> list = null;
		Map<String, Object> response = new HashMap<>();
		list = employeeService.getEmployees();
		response.put(Constants.STATUS, HttpStatus.OK.value());
		response.put(Constants.EMPLOYEES, list);
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	
	}

}
