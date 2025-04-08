package com.employees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.domain.service.EmployeeService;
import com.employees.dto.EmployeeDTO;
import com.employees.error.AppException;
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
	
	@Autowired
	private MessageSource messageSource;
	
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
	
	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
	    if (log.isDebugEnabled())
	        log.debug(">> Enter to EmployeeController.deleteUser <<");

	    try {
	        employeeService.deleteEmployeeById(id);
	        
	        // Obtener el mensaje de éxito del archivo de propiedades
	        String successMessage = messageSource.getMessage("user.delete.sucess", null, LocaleContextHolder.getLocale());
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put(Constants.STATUS, Constants.SUCCESS);
	        response.put(Constants.MESSAGE, successMessage);
	        
	        return new ResponseEntity<>(response, HttpStatus.OK);
	        
	    } catch (AppException e) {
	        return handleAppException(e);
	    }
	}
	
	@PutMapping(value = "/employee")
	public ResponseEntity<?> updateUser(@RequestBody EmployeeDTO employeeDTO) {
		 if (log.isDebugEnabled())
		        log.debug(">> Enter to EmployeeController.updateUser <<");
		 
		 Map<String, Object> response = new HashMap<>();
		 HttpHeaders responseHeaders = new HttpHeaders();
		 
		 try {
			 
			 EmployeeDTO updatedEmployee = employeeService.updateUser(employeeDTO);
			 
			 // Obtener el mensaje de exito del archivo de propiedades
		     String successMessage = messageSource.getMessage("user.update.sucess", null, LocaleContextHolder.getLocale());
		        
		     // Agregar mensaje tanto en los headers como en el cuerpo
		     responseHeaders.set(Constants.MESSAGE, successMessage);
		     response.put(Constants.STATUS, Constants.SUCCESS);
		     response.put(Constants.MESSAGE, successMessage);
		     response.put(Constants.EMPLOYEE, updatedEmployee);
		        
		     return new ResponseEntity<>(response, HttpStatus.OK);			 
			 
		 } catch(AppException e) {
			return handleAppException(e);
		 }
		    
		
	}
	
	private ResponseEntity<?> handleAppException(AppException e) {
	    Map<String, Object> response = new HashMap<>();
	    HttpHeaders responseHeaders = new HttpHeaders();
	    
	    // Determinar qué mensaje de error usar
	    String errorKey = "error.user_not_found";
	    
	    if (e.getCodeError() != null) {
	        // Si hay un código de error específico, úsalo para obtener el mensaje
	        errorKey = e.getCodeError().getKeyMessage();
	    }
	    
	    String errorMessage = messageSource.getMessage(errorKey, null, LocaleContextHolder.getLocale());
	    
	    // Agregar mensaje tanto en los headers como en el cuerpo
	    responseHeaders.set(Constants.MESSAGE, errorMessage);
	    response.put(Constants.STATUS, Constants.ERROR_VALIDATION);
	    response.put(Constants.MESSAGE, errorMessage);
	    
	    HttpStatus status = errorKey.equals("error.user_not_found") 
	        ? HttpStatus.NOT_FOUND 
	        : HttpStatus.BAD_REQUEST;
	        
	    return new ResponseEntity<>(response, responseHeaders, status);
	}

}
