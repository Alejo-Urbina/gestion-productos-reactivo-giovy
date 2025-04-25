# Gestión de Productos - API Spring Boot

Este proyecto es una API REST construida con **Spring Boot**, diseñada para la gestión básica de productos. Soporta operaciones CRUD sobre productos que tienen un nombre y precio. Se utiliza una base de datos **H2 en memoria** para pruebas y desarrollo.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5
- Mockito
- MockMvc (para pruebas de integración)
- Gradle

---

## 🧪 Pruebas

El proyecto incluye dos tipos de pruebas automatizadas:

### 🔹 Pruebas Unitarias (Mockito)

Ubicadas en `ProductoServiceTest.java`, estas pruebas verifican el comportamiento del servicio (`ProductoServicioImpl`) de forma aislada, simulando el repositorio con Mockito. Se prueban los métodos:

- `crearProducto()`
- `consultarProducto()`
- `eliminarProducto()`

✅ Validan lógica de negocio sin necesidad de levantar el contexto de Spring.

---

### 🔸 Pruebas de Integración (MockMvc)

Ubicadas en `ProductoIntegrationTest.java`, estas pruebas simulan peticiones HTTP reales contra la API REST usando `MockMvc`. Se prueban los endpoints:

- `POST /producto` → Crear un producto
- `GET /producto/{id}` → Obtener un producto
- `DELETE /producto/{id}` → Eliminar un producto

✅ Validan que el controlador, el servicio, y la persistencia trabajen correctamente en conjunto.

---

## ▶️ Cómo correr las pruebas

### Opción 1: Desde IntelliJ IDEA
1. Haz clic derecho sobre el proyecto o clase de prueba.
2. Selecciona **Run 'Tests in ...'**.

### Opción 2: Desde línea de comandos

```bash
./gradlew test