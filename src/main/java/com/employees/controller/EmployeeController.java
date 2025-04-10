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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.domain.service.EmployeeService;
import com.employees.dto.EmployeeDTO;
import com.employees.error.AppException;
import com.employees.util.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
@Tag(name = "Employees", description = "API to manage the info of employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService  employeeService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Operation(summary = "Get the list of employees", 
            description = "Return all the list of employees")
	 @ApiResponses({
	     @ApiResponse(responseCode = "200", description = "The list retrived successfully"),
	     @ApiResponse(responseCode = "500", description = "Internal server error")
	 })
	@GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	/**
	 * Method of controller to get the list of employees
	 * @return ResponseEntity
	 */
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
	
	@Operation(summary = "Delete the user by id", 
            description = "Remove the user from the system")
	 @ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
	     @ApiResponse(responseCode = "500", description = "Internal server error")
	 })
	@DeleteMapping(value = "/employee/{id}")
	/**
	 * Method of controller to delete user by id
	 * @param id Identity of Employee
	 * @return ResponseEntity
	 */
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
	

	@Operation(summary = "Update the employee by the information", 
            description = "Update employee with the info given")
	 @ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
	     @ApiResponse(responseCode = "500", description = "Internal server error")
	 })
	@PutMapping(value = "/employee")
	/**
	 * Method of controller to update an Employee
	 * @param employeeDTO Class of Transport with the info
	 * @return ResponseEntity
	 */
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
		        
		     return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);		 
			 
		 } catch(AppException e) {
			return handleAppException(e);
		 }
		    
		
	}
	
	@Operation(
		    summary = "Add the employee(s) with the information",
		    description = "Add employee(s) with info given",
		    requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
		        content = @Content(
		            mediaType = "application/json",
		            array = @ArraySchema(
		                schema = @Schema(ref = "#/components/schemas/EmployeeCreation")
		            )
		        )
		    )
		)
	 @ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Employee added successfully"),
	     @ApiResponse(responseCode = "500", description = "Internal server error")
	 })
	@PostMapping(value = "/employee")
	/**
	 * Method of the controller to add user(s)
	 * @param employees List of employees
	 * @return ResponseEnity
	 */
	public ResponseEntity<?> addUsers(@RequestBody List<EmployeeDTO> employees) {
		if (log.isDebugEnabled())
	        log.debug(">> Enter to EmployeeController.addUsers <<");
		Map<String, Object> response = new HashMap<>();
		HttpHeaders responseHeaders = new HttpHeaders();
		
		try {
			
			List<EmployeeDTO>  addedEmployees = employeeService.addUsers(employees);
			
			// Obtener el mensaje de exito del archivo de propiedades
		    String successMessage = messageSource.getMessage("user.add.sucess", null, LocaleContextHolder.getLocale());
		    
		     // Agregar mensaje tanto en los headers como en el cuerpo
		     responseHeaders.set(Constants.MESSAGE, successMessage);
		     response.put(Constants.STATUS, Constants.SUCCESS);
		     response.put(Constants.MESSAGE, successMessage);
		     response.put(Constants.EMPLOYEES, addedEmployees);
		        
		     return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
		    
			
			
		} catch(AppException e) {
			return handleAppException(e);
		}
		
		
	}
	
	/**
	 * Method helper to manage the exception of the logic os the services
	 * @param e AppException with the code error and message
	 * @return ResponseEntity
	 */
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
