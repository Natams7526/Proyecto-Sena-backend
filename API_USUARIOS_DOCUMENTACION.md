# Documentación API - Módulo de Usuarios y Autenticación

## Base URL
```
http://localhost:8080/api/v1/usuarios
```

## Arquitectura Implementada
```
com/barberia/
├── controller/
│   └── UsuarioController.java
├── service/
│   ├── UsuarioService.java (Interface)
│   └── impl/
│       └── UsuarioServiceImpl.java
├── repository/
│   └── UsuarioRepository.java
├── model/
│   └── Usuario.java
├── dto/
│   ├── UsuarioDTO.java
│   ├── UsuarioResponseDTO.java
│   ├── LoginRequest.java
│   └── LoginResponse.java
├── mapper/
│   └── UsuarioMapper.java
├── exception/
│   ├── UsuarioNotFoundException.java
│   ├── CredencialesInvalidasException.java
│   └── GlobalExceptionHandler.java
└── util/ (otros)
```

---

## Endpoints de la API

### 1. CREAR USUARIO
**POST** `/api/v1/usuarios`

**Descripción:** Crea un nuevo usuario en el sistema.

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "nombre": "Juan Carlos Pérez",
  "email": "juan@example.com",
  "username": "juancarlos",
  "password": "123456",
  "rol": "USER",
  "activo": true
}
```

**Validaciones:**
- `nombre`: 3-100 caracteres, obligatorio
- `email`: Formato válido, único, obligatorio
- `username`: 3-50 caracteres, único, obligatorio
- `password`: Mínimo 6 caracteres, obligatorio
- `rol`: Obligatorio (valores: ADMIN, USER, BARBERO, etc.)
- `activo`: Boolean, por defecto true

**Response 201 Created:**
```json
{
  "mensaje": "Usuario creado exitosamente",
  "usuario": {
    "id": 1,
    "nombre": "Juan Carlos Pérez",
    "email": "juan@example.com",
    "username": "juancarlos",
    "rol": "USER",
    "activo": true
  }
}
```

**Response 400 Bad Request (Email duplicado):**
```json
{
  "error": "Argumento inválido",
  "mensaje": "El email 'juan@example.com' ya está registrado",
  "timestamp": 1716028260000
}
```

**Response 400 Bad Request (Username duplicado):**
```json
{
  "error": "Argumento inválido",
  "mensaje": "El username 'juancarlos' ya está en uso",
  "timestamp": 1716028260000
}
```

---

### 2. OBTENER TODOS LOS USUARIOS
**GET** `/api/v1/usuarios`

**Descripción:** Obtiene la lista de todos los usuarios en el sistema.

**Headers:**
```
Accept: application/json
```

**Response 200 OK:**
```json
{
  "total": 2,
  "usuarios": [
    {
      "id": 1,
      "nombre": "Juan Carlos Pérez",
      "email": "juan@example.com",
      "username": "juancarlos",
      "rol": "USER",
      "activo": true
    },
    {
      "id": 2,
      "nombre": "Admin Usuario",
      "email": "admin@example.com",
      "username": "admin",
      "rol": "ADMIN",
      "activo": true
    }
  ]
}
```

---

### 3. OBTENER USUARIO POR ID
**GET** `/api/v1/usuarios/{id}`

**Descripción:** Obtiene la información de un usuario específico por su ID.

**Path Parameters:**
- `id`: ID del usuario (Long)

**Ejemplo:**
```
GET http://localhost:8080/api/v1/usuarios/1
```

**Response 200 OK:**
```json
{
  "usuario": {
    "id": 1,
    "nombre": "Juan Carlos Pérez",
    "email": "juan@example.com",
    "username": "juancarlos",
    "rol": "USER",
    "activo": true
  }
}
```

**Response 404 Not Found:**
```json
{
  "error": "Usuario no encontrado",
  "mensaje": "Usuario no encontrado con ID: 999",
  "timestamp": 1716028260000
}
```

---

### 4. ACTUALIZAR USUARIO
**PUT** `/api/v1/usuarios/{id}`

**Descripción:** Actualiza los datos de un usuario existente.

**Path Parameters:**
- `id`: ID del usuario (Long)

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "nombre": "Juan Carlos Pérez Actualizado",
  "email": "juan.nuevo@example.com",
  "username": "juancarlos_v2",
  "password": "654321",
  "rol": "BARBERO",
  "activo": true
}
```

**Ejemplo:**
```
PUT http://localhost:8080/api/v1/usuarios/1
```

**Response 200 OK:**
```json
{
  "mensaje": "Usuario actualizado exitosamente",
  "usuario": {
    "id": 1,
    "nombre": "Juan Carlos Pérez Actualizado",
    "email": "juan.nuevo@example.com",
    "username": "juancarlos_v2",
    "rol": "BARBERO",
    "activo": true
  }
}
```

**Response 404 Not Found:**
```json
{
  "error": "Usuario no encontrado",
  "mensaje": "Usuario no encontrado con ID: 999",
  "timestamp": 1716028260000
}
```

---

### 5. ELIMINAR USUARIO
**DELETE** `/api/v1/usuarios/{id}`

**Descripción:** Elimina un usuario del sistema.

**Path Parameters:**
- `id`: ID del usuario (Long)

**Ejemplo:**
```
DELETE http://localhost:8080/api/v1/usuarios/1
```

**Response 200 OK:**
```json
{
  "mensaje": "Usuario eliminado exitosamente"
}
```

**Response 404 Not Found:**
```json
{
  "error": "Usuario no encontrado",
  "mensaje": "Usuario no encontrado con ID: 999",
  "timestamp": 1716028260000
}
```

---

### 6. LOGIN DE USUARIO
**POST** `/api/v1/usuarios/login`

**Descripción:** Autentica un usuario con sus credenciales (username y password).

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
  "username": "admin",
  "password": "123456"
}
```

**Response 200 OK (Login Exitoso):**
```json
{
  "mensaje": "Usuario loggeado correctamente",
  "exitoso": true,
  "usuario": {
    "id": 2,
    "nombre": "Admin Usuario",
    "email": "admin@example.com",
    "username": "admin",
    "rol": "ADMIN",
    "activo": true
  }
}
```

**Response 200 OK (Credenciales Inválidas):**
```json
{
  "mensaje": "Usuario o contraseña incorrecta",
  "exitoso": false,
  "usuario": null
}
```

**Response 200 OK (Usuario Inactivo):**
```json
{
  "mensaje": "Usuario inactivo",
  "exitoso": false,
  "usuario": null
}
```

**Response 400 Bad Request (Validación fallida):**
```json
{
  "error": "Error de validación",
  "mensaje": "Los datos enviados no son válidos",
  "errores": {
    "username": "El username es obligatorio",
    "password": "La contraseña es obligatoria"
  },
  "timestamp": 1716028260000
}
```

---

## Colección Postman JSON

Puedes importar la siguiente colección en Postman:

```json
{
  "info": {
    "name": "API Barbería - Usuarios y Autenticación",
    "description": "Colección de endpoints para gestión de usuarios",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Crear Usuario",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"nombre\": \"Juan Carlos Pérez\", \"email\": \"juan@example.com\", \"username\": \"juancarlos\", \"password\": \"123456\", \"rol\": \"USER\", \"activo\": true}"
        },
        "url": {
          "raw": "http://localhost:8080/api/v1/usuarios",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "v1", "usuarios"]
        }
      }
    },
    {
      "name": "Obtener Todos los Usuarios",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/v1/usuarios",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "v1", "usuarios"]
        }
      }
    },
    {
      "name": "Obtener Usuario por ID",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/v1/usuarios/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "v1", "usuarios", "1"]
        }
      }
    },
    {
      "name": "Actualizar Usuario",
      "request": {
        "method": "PUT",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"nombre\": \"Juan Carlos Pérez Actualizado\", \"email\": \"juan.nuevo@example.com\", \"username\": \"juancarlos_v2\", \"password\": \"654321\", \"rol\": \"BARBERO\", \"activo\": true}"
        },
        "url": {
          "raw": "http://localhost:8080/api/v1/usuarios/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "v1", "usuarios", "1"]
        }
      }
    },
    {
      "name": "Eliminar Usuario",
      "request": {
        "method": "DELETE",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/v1/usuarios/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "v1", "usuarios", "1"]
        }
      }
    },
    {
      "name": "Login Usuario",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"username\": \"admin\", \"password\": \"123456\"}"
        },
        "url": {
          "raw": "http://localhost:8080/api/v1/usuarios/login",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8080",
          "path": ["api", "v1", "usuarios", "login"]
        }
      }
    }
  ]
}
```

---

## Datos de Prueba

Para pruebas iniciales, crea estos usuarios:

### Usuario Admin
```json
{
  "nombre": "Administrador Sistema",
  "email": "admin@barberia.com",
  "username": "admin",
  "password": "123456",
  "rol": "ADMIN",
  "activo": true
}
```

### Usuario Barbero
```json
{
  "nombre": "Carlos Barbero",
  "email": "carlos@barberia.com",
  "username": "carlos_barbero",
  "password": "123456",
  "rol": "BARBERO",
  "activo": true
}
```

### Usuario Cliente
```json
{
  "nombre": "Juan Cliente",
  "email": "juan@barberia.com",
  "username": "juan_cliente",
  "password": "123456",
  "rol": "USER",
  "activo": true
}
```

---

## Códigos de Estado HTTP

| Código | Descripción |
|--------|-------------|
| 200 | OK - Solicitud exitosa |
| 201 | CREATED - Recurso creado exitosamente |
| 400 | BAD REQUEST - Datos inválidos o validación fallida |
| 401 | UNAUTHORIZED - Credenciales inválidas |
| 404 | NOT FOUND - Usuario no encontrado |
| 500 | INTERNAL SERVER ERROR - Error en el servidor |

---

## Validaciones de Entrada

### Campo: nombre
- **Requerido**: Sí
- **Tipo**: String
- **Longitud**: 3-100 caracteres
- **Patrón**: Acepta letras, espacios y caracteres especiales

### Campo: email
- **Requerido**: Sí
- **Tipo**: Email
- **Único**: Sí
- **Patrón**: debe@ejemplo.com

### Campo: username
- **Requerido**: Sí
- **Tipo**: String
- **Longitud**: 3-50 caracteres
- **Único**: Sí
- **Patrón**: Solo letras, números y guiones bajos

### Campo: password
- **Requerido**: Sí
- **Tipo**: String
- **Longitud mínima**: 6 caracteres
- **Nota**: Almacenado en texto plano (sin encriptación)

### Campo: rol
- **Requerido**: Sí
- **Tipo**: String
- **Valores permitidos**: ADMIN, USER, BARBERO (extendible)

### Campo: activo
- **Requerido**: No
- **Tipo**: Boolean
- **Valor por defecto**: true
- **Efecto**: Si es false, el usuario no puede hacer login

---

## Características Implementadas

✅ Entidad Usuario con atributos completos
✅ CRUD completo funcional
✅ Endpoint de login con autenticación básica
✅ DTOs para request y response
✅ Mapper para conversión de objetos
✅ Validaciones con Jakarta Validation
✅ Excepciones personalizadas
✅ Manejador global de excepciones
✅ Logs con SLF4J
✅ JpaRepository para persistencia
✅ Arquitectura en capas
✅ Documentación completa
✅ Compatible con PostgreSQL
✅ Listo para ejecutar en Eclipse IDE

---

## Notas de Implementación

1. **Seguridad**: La contraseña se almacena en texto plano. En producción, usar encriptación BCrypt.
2. **Autenticación**: Actualmente es básica (sin JWT). Para producción, implementar JWT.
3. **Base de datos**: Usa PostgreSQL en producción y H2 en pruebas.
4. **Transacciones**: El servicio usa `@Transactional` para operaciones CRUD.
5. **Logging**: Usa Lombok `@Slf4j` para logging automático.

---

Versión: 1.0
Fecha: 2026-05-18
Autor: Backend Senior Developer
