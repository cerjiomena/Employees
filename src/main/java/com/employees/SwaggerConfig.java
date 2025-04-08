package com.employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.DateSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
    	
    	// Crear un esquema para creación de empleado sin ID
        Schema employeeCreationSchema = new Schema<Object>()
            .type("object")
            .addProperty("primerNombre", new StringSchema().example("Juan"))
            .addProperty("segundoNombre", new StringSchema().example("Carlos"))
            // Otros campos, pero sin incluir el ID
            .addProperty("apellidoPaterno", new StringSchema().example("García"))
            .addProperty("apellidoMaterno", new StringSchema().example("López"))
            .addProperty("edad", new IntegerSchema().example(30))
            .addProperty("sexo", new StringSchema().example("M"))
            .addProperty("fechaNacimiento", new DateSchema().example("1990-01-01"))
            .addProperty("puesto", new StringSchema().example("Desarrollador"));
    	
        return new OpenAPI()
        		.components(new Components()
                        .addSchemas("EmployeeCreation", employeeCreationSchema))
                .info(new Info()
                        .title("Employees API")
                        .description("API to managed the information of Employees")
                        .version("1.0"));
    }
}