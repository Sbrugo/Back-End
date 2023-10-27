# JDBC_H2
# Gestión de Odontólogos - Documentación

Este proyecto aborda la gestión de odontólogos en una clínica odontológica. El sistema permite registrar odontólogos y listarlos utilizando dos enfoques diferentes: uno utilizando una base de datos H2 y el otro utilizando una colección (ArrayList) en el patrón DAO.

## Requisitos

Asegúrate de que tu entorno de desarrollo tenga las siguientes dependencias:

- [H2 Database](https://www.h2database.com/html/main.html) (para la implementación con base de datos)
- [log4j](https://logging.apache.org/log4j/2.x/) (para el registro de operaciones)
- [JUnit](https://junit.org/junit5/) (para las pruebas unitarias)

## Estructura del Proyecto

El proyecto consta de las siguientes partes:

- `Odontologo`: Clase que representa a un odontólogo con número de matrícula, nombre y apellido.
- `OdontologoDAO`: Interfaz que define los métodos para acceder a los datos de odontólogos.
- `OdontologoDaoH2`: Implementación de `OdontologoDAO` que utiliza una base de datos H2.
- `OdontologoDaoMemoria`: Implementación de `OdontologoDAO` que utiliza una colección (ArrayList).
- `log4j.xml`: Archivo de configuración de log4j.
- `create-table.sql`: Script SQL para crear la tabla de odontólogos en la base de datos H2.

## Configuración

- Asegúrate de configurar las dependencias mencionadas en tu proyecto.
- Configura el archivo `log4j.xml` para la configuración de registro de log4j.

### Base de Datos H2

1. Configura la conexión a la base de datos H2 en `OdontologoDaoH2`.
2. Utiliza el script `create-table.sql` para crear la tabla de odontólogos en la base de datos.
3. Implementa el manejo de excepciones adecuado en caso de problemas de conexión.

### Colección en Memoria

1. Utiliza la implementación en `OdontologoDaoMemoria` para gestionar odontólogos en memoria.

### Pruebas Unitarias

- Utiliza JUnit para probar la funcionalidad de listar odontólogos en ambas implementaciones.
