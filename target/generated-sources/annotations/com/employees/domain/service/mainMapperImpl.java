package com.employees.domain.service;

import com.employees.domain.model.Employee;
import com.employees.dto.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-07T20:10:53-0600",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.v20241112-0530, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class mainMapperImpl implements MapStructMapper {

    @Override
    public List<EmployeeDTO> employeesToEmployeesDtos(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeDTO> list = new ArrayList<EmployeeDTO>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( employeeToEmployeeDto( employee ) );
        }

        return list;
    }

    @Override
    public EmployeeDTO employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setApellidoMaterno( employee.getApellidoMaterno() );
        employeeDTO.setApellidoPaterno( employee.getApellidoPaterno() );
        employeeDTO.setEdad( employee.getEdad() );
        employeeDTO.setFechaNacimiento( employee.getFechaNacimiento() );
        employeeDTO.setPrimerNombre( employee.getPrimerNombre() );
        employeeDTO.setPuesto( employee.getPuesto() );
        employeeDTO.setSegundoNombre( employee.getSegundoNombre() );
        employeeDTO.setSexo( employee.getSexo() );

        return employeeDTO;
    }
}
