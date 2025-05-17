# Proyecto Recetario

## Descripción

Proyecto en Java para la gestión de recetas culinarias utilizando una base de datos Oracle.  
Permite registrar usuarios, crear y modificar recetas, buscar recetas por nombre o etiquetas, mostrar detalles completos de recetas, puntuar recetas y más.

---

## Características principales

- Registro e inicio de sesión de usuarios.
- Gestión de recetas con nombre, descripción, tiempo, dificultad, calorías y dibujo opcional.
- Visualización completa de recetas con ingredientes y pasos de preparación.
- Modificación de recetas con control de permisos según tipo de usuario.
- Puntuar recetas del 1 al 5.
- Visualización de usuarios registrados.
- Soporte para usuarios normales y administradores.

---

## Requisitos

- Java 8 o superior.
- Oracle Database (probado con Oracle XE).
- Driver JDBC Oracle (`ojdbc8.jar`).
- IDE de Java o terminal para compilar y ejecutar.

---

## Configuración

1. **Base de datos:**  
   Crear las tablas necesarias (`usuarios`, `recetas`, `ingredientes`, `pasospreparacion`, `etiquetas`) en Oracle.  
   Ajusta las credenciales en la clase `Basededatos.java`:

   ```java
   final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
   final String username = "nasaro";
   final String pass = "1234";
    ```
## Agregar driver JDBC:
Añade el archivo ojdbc8.jar al classpath del proyecto.

## Uso
Ejecuta la clase principal que controla el flujo del programa y sigue las instrucciones por consola para:

Registrar nuevos usuarios.

Iniciar sesión.

Buscar recetas.

Crear y modificar recetas.

Puntuar recetas.

## Seguridad y mejoras
Se recomienda usar PreparedStatement para evitar inyección SQL.

Implementar hashing para almacenar contraseñas de forma segura.

Mejorar manejo de excepciones y cierre de recursos JDBC.

Separar la lógica de negocio de la interfaz de usuario.

Validar entradas para evitar errores y ataques.
