# Employees

Proyecto que utiliza Spring Boot 3.4.4 para el manejo de empleados en conjunto con maven como manejador de dependencias


## Developer

| Nombre                   | Correo                         |
| ------------------------ | ------------------------------ |
| Sergio Mena Zamora       | cerjio@gmail.com        |


## Tecnología utilizada

- JavaSE 17
- Maven 3.9.9


## Estructura del Proyecto

El proyecto sigue una estructura modular que facilita la escalabilidad y el mantenimiento del código.

### Descripción de los Módulos

- **com/employees/controller/**: Contiene los controladores REST para exponer los endpoints
- **com/employees/domain/model/**: Entidades de dominio.
- **com/employees/domain/repository/**: Repositorio para acceder mediante los metodos a los datos.
- **com/employees/domain/service/**: Capa de servicio que puede contener logica del negocio.
- **com/employees/domain/dto/**: Clases de transporte para el manejo de datos.
- **com/employees/domain/error/**: Manejo de errores personalizados.
- **com/employees/domain/util/**: Manejo de metodos utilitarios.
- **resources/**: Manejo de recursos y archivos de propiedades.
- **resources/postman**: Colección de escenarios de prueba de postman.


## Uso

## Para ejecutar el proyecto

- Se importa el proyecto en Spring Tool Suite 
- Una vez importado se debe ejecutar como un Spring Boot App


[Repositorio en GitHub](https://github.com/cerjiomena/Employees)


