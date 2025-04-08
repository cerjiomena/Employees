package com.employees.dto;

import java.util.Date;
import lombok.Data;

@Data
public class EmployeeDTO {

	private Integer id;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Integer Edad;
	private String sexo;
	private Date fechaNacimiento;
	private String puesto;
}
