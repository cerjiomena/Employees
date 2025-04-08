package com.employees.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.employees.domain.model.Employee;
import com.employees.domain.repository.EmployeeRepository;
import com.employees.domain.service.EmployeeService;
import com.employees.domain.service.MapStructMapper;
import com.employees.dto.EmployeeDTO;
import com.employees.error.AppException;
import com.employees.util.MessageError;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	@Qualifier("mainMapperImpl")
	private MapStructMapper mapStructMapper;

	
	/**
	 * @see EmployeeService#getEmployees()
	 */
	public List<EmployeeDTO> getEmployees() {
		
		if(log.isDebugEnabled())
			log.debug("Enter to EmployeeServiceImpl.getEmployees ");
		
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		

		
		return mapStructMapper.employeesToEmployeesDtos(employees);
	}

	/**
	 * @see EmployeeService#deleteEmployeeById(Integer)
	 */
	public void deleteEmployeeById(Integer id) throws AppException {
		
		if (!employeeRepository.existsById(id)) {
	        log.warn("Intent to remove employee by id: {}", id);
	        throw new AppException(MessageError.ERROR_EMPLOYEE_DOES_NOT_EXIST);
	    }
	    
	    log.info("Delete employee by ID: {}", id);
	    employeeRepository.deleteById(id);
		
	}
	
	/**
	 * @see EmployeeService#getEmployeeById(Integer)
	 */
	public EmployeeDTO getEmployeeById(Integer id) throws AppException {
	    Optional<Employee> employee = employeeRepository.findById(id);
	    
	    if (!employee.isPresent()) {
	        throw new AppException(MessageError.ERROR_EMPLOYEE_DOES_NOT_EXIST);
	    }
	    
	    return mapStructMapper.employeeToEmployeeDto(employee.get());
	}

	/**
	 * @see EmployeeService#addUsers(List)
	 */
	public List<EmployeeDTO> addUsers(List<EmployeeDTO> employeesDTO) throws AppException {
	    try {
	        List<Employee> employees = mapStructMapper.employeesDTosToEmployees(employeesDTO);
	        List<Employee> newEmployees = (List<Employee>) employeeRepository.saveAll(employees);
	        return mapStructMapper.employeesToEmployeesDtos(newEmployees);
	    } catch (DataIntegrityViolationException e) {
	        // Manejar violaciones de integridad (duplicados, restricciones, etc.)
	        log.error("Error de integridad de datos al guardar empleados: {}", e.getMessage());
	        throw new AppException("Error on save data employees: violation of data integrity", MessageError.ERROR_DATA_INTEGRITY);
	    } catch (DataAccessException e) {
	        // Manejar otros errores de acceso a datos
	        log.error("Error de acceso a datos al guardar empleados: {}", e.getMessage());
	        throw new AppException("Error on save data employees: problems with access data", MessageError.ERROR_DATA_ACCESS);
	    }
	}

	/**
	 * @see EmployeeService#updateUser(EmployeeDTO)
	 */
	public EmployeeDTO updateUser(EmployeeDTO employeeDTO) throws AppException {
		 Optional<Employee> employeeOptional = employeeRepository.findById(employeeDTO.getId());
		    
		 if (!employeeOptional.isPresent()) {
		        throw new AppException(MessageError.ERROR_EMPLOYEE_DOES_NOT_EXIST);
		 }
		 
		 
		 Employee employeeObtained = employeeOptional.get();
		 employeeObtained.setApellidoMaterno(employeeDTO.getApellidoMaterno());
		 employeeObtained.setApellidoPaterno(employeeDTO.getApellidoPaterno());
		 employeeObtained.setFechaNacimiento(employeeDTO.getFechaNacimiento());
		 employeeObtained.setPrimerNombre(employeeDTO.getPrimerNombre());
		 employeeObtained.setSegundoNombre(employeeDTO.getSegundoNombre());
		 employeeObtained.setPuesto(employeeDTO.getPuesto());
		 employeeObtained.setSexo(employeeDTO.getSexo());
		 employeeObtained.setEdad(employeeDTO.getEdad());
		 
		Employee updatedEmployee = employeeRepository.save(employeeObtained);
		 
		    
		return mapStructMapper.employeeToEmployeeDto(updatedEmployee);
	}
	

}
