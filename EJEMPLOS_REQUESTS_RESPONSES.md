# Ejemplos de Requests y Responses

## MÓDULO PRODUCTOS

### 1. Crear Producto
**Endpoint:** `POST /api/v1/productos`

**Request Body:**
```json
{
  "nombre": "Gel Profesional para Cabello",
  "estado": true,
  "stockDisponible": 50,
  "cantidad": 100,
  "precio": 25.99
}
```

**Response (201 Created):**
```json
{
  "mensaje": "Producto creado exitosamente",
  "producto": {
    "id": 1,
    "nombre": "Gel Profesional para Cabello",
    "estado": true,
    "stockDisponible": 50,
    "cantidad": 100,
    "precio": 25.99
  }
}
```

### 2. Obtener Todos los Productos
**Endpoint:** `GET /api/v1/productos`

**Response (200 OK):**
```json
{
  "mensaje": "Productos obtenidos exitosamente",
  "total": 3,
  "productos": [
    {
      "id": 1,
      "nombre": "Gel Profesional para Cabello",
      "estado": true,
      "stockDisponible": 50,
      "cantidad": 100,
      "precio": 25.99
    },
    {
      "id": 2,
      "nombre": "Espuma de Afeitar",
      "estado": true,
      "stockDisponible": 30,
      "cantidad": 60,
      "precio": 12.50
    },
    {
      "id": 3,
      "nombre": "Cera para Cabello",
      "estado": false,
      "stockDisponible": 0,
      "cantidad": 0,
      "precio": 18.00
    }
  ]
}
```

### 3. Filtrar Productos por Estado (Activos)
**Endpoint:** `GET /api/v1/productos?estado=true`

**Response (200 OK):**
```json
{
  "mensaje": "Productos filtrados obtenidos exitosamente",
  "total": 2,
  "productos": [
    {
      "id": 1,
      "nombre": "Gel Profesional para Cabello",
      "estado": true,
      "stockDisponible": 50,
      "cantidad": 100,
      "precio": 25.99
    },
    {
      "id": 2,
      "nombre": "Espuma de Afeitar",
      "estado": true,
      "stockDisponible": 30,
      "cantidad": 60,
      "precio": 12.50
    }
  ]
}
```

### 4. Obtener Producto por ID
**Endpoint:** `GET /api/v1/productos/1`

**Response (200 OK):**
```json
{
  "mensaje": "Producto obtenido exitosamente",
  "producto": {
    "id": 1,
    "nombre": "Gel Profesional para Cabello",
    "estado": true,
    "stockDisponible": 50,
    "cantidad": 100,
    "precio": 25.99
  }
}
```

### 5. Actualizar Producto
**Endpoint:** `PUT /api/v1/productos/1`

**Request Body:**
```json
{
  "nombre": "Gel Premium Profesional",
  "estado": true,
  "stockDisponible": 45,
  "cantidad": 95,
  "precio": 29.99
}
```

**Response (200 OK):**
```json
{
  "mensaje": "Producto actualizado exitosamente",
  "producto": {
    "id": 1,
    "nombre": "Gel Premium Profesional",
    "estado": true,
    "stockDisponible": 45,
    "cantidad": 95,
    "precio": 29.99
  }
}
```

### 6. Eliminar Producto
**Endpoint:** `DELETE /api/v1/productos/1`

**Response (204 No Content):**
(sin cuerpo)

### Error: Producto Duplicado
**Endpoint:** `POST /api/v1/productos`

**Request Body:**
```json
{
  "nombre": "Gel Profesional para Cabello",
  "estado": true,
  "stockDisponible": 50,
  "cantidad": 100,
  "precio": 25.99
}
```

**Response (400 Bad Request):**
```json
{
  "error": "Argumento inválido",
  "mensaje": "El producto 'Gel Profesional para Cabello' ya existe",
  "timestamp": 1654321000000
}
```

### Error: Producto No Encontrado
**Endpoint:** `GET /api/v1/productos/999`

**Response (404 Not Found):**
```json
{
  "error": "Producto no encontrado",
  "mensaje": "Producto no encontrado con ID: 999",
  "timestamp": 1654321000000
}
```

### Error: Validación
**Endpoint:** `POST /api/v1/productos`

**Request Body:**
```json
{
  "nombre": "G",
  "estado": null,
  "stockDisponible": -5,
  "cantidad": 100,
  "precio": -10
}
```

**Response (400 Bad Request):**
```json
{
  "error": "Error de validación",
  "mensaje": "Los datos enviados no son válidos",
  "errores": {
    "nombre": "El nombre debe tener entre 3 y 150 caracteres",
    "estado": "El estado es obligatorio",
    "stockDisponible": "El stock disponible no puede ser negativo",
    "precio": "El precio debe ser mayor a 0"
  },
  "timestamp": 1654321000000
}
```

---

## MÓDULO STAFF / HORARIOS

### 1. Crear Staff sin Horarios
**Endpoint:** `POST /api/v1/staff`

**Request Body:**
```json
{
  "nombre": "Juan Pérez",
  "estado": true
}
```

**Response (201 Created):**
```json
{
  "mensaje": "Staff creado exitosamente",
  "staff": {
    "id": 1,
    "nombre": "Juan Pérez",
    "estado": true,
    "horarios": []
  }
}
```

### 2. Crear Staff con Horarios
**Endpoint:** `POST /api/v1/staff`

**Request Body:**
```json
{
  "nombre": "Carlos García",
  "estado": true,
  "horarios": [
    {
      "diaSemana": 1,
      "horaInicio": 8,
      "horaFin": 17
    },
    {
      "diaSemana": 2,
      "horaInicio": 8,
      "horaFin": 17
    },
    {
      "diaSemana": 3,
      "horaInicio": 10,
      "horaFin": 19
    },
    {
      "diaSemana": 4,
      "horaInicio": 8,
      "horaFin": 17
    },
    {
      "diaSemana": 5,
      "horaInicio": 8,
      "horaFin": 18
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "mensaje": "Staff creado exitosamente",
  "staff": {
    "id": 2,
    "nombre": "Carlos García",
    "estado": true,
    "horarios": [
      {
        "id": 1,
        "diaSemana": 1,
        "horaInicio": 8,
        "horaFin": 17,
        "nombreDia": "Lunes"
      },
      {
        "id": 2,
        "diaSemana": 2,
        "horaInicio": 8,
        "horaFin": 17,
        "nombreDia": "Martes"
      },
      {
        "id": 3,
        "diaSemana": 3,
        "horaInicio": 10,
        "horaFin": 19,
        "nombreDia": "Miércoles"
      },
      {
        "id": 4,
        "diaSemana": 4,
        "horaInicio": 8,
        "horaFin": 17,
        "nombreDia": "Jueves"
      },
      {
        "id": 5,
        "diaSemana": 5,
        "horaInicio": 8,
        "horaFin": 18,
        "nombreDia": "Viernes"
      }
    ]
  }
}
```

### 3. Obtener Todos los Staff
**Endpoint:** `GET /api/v1/staff`

**Response (200 OK):**
```json
{
  "mensaje": "Staff obtenido exitosamente",
  "total": 2,
  "staff": [
    {
      "id": 1,
      "nombre": "Juan Pérez",
      "estado": true,
      "horarios": []
    },
    {
      "id": 2,
      "nombre": "Carlos García",
      "estado": true,
      "horarios": [...]
    }
  ]
}
```

### 4. Obtener Staff por ID (con Horarios)
**Endpoint:** `GET /api/v1/staff/2`

**Response (200 OK):**
```json
{
  "mensaje": "Staff obtenido exitosamente",
  "staff": {
    "id": 2,
    "nombre": "Carlos García",
    "estado": true,
    "horarios": [
      {
        "id": 1,
        "diaSemana": 1,
        "horaInicio": 8,
        "horaFin": 17,
        "nombreDia": "Lunes"
      },
      {
        "id": 2,
        "diaSemana": 2,
        "horaInicio": 8,
        "horaFin": 17,
        "nombreDia": "Martes"
      },
      {
        "id": 3,
        "diaSemana": 3,
        "horaInicio": 10,
        "horaFin": 19,
        "nombreDia": "Miércoles"
      }
    ]
  }
}
```

### 5. Filtrar Staff por Estado
**Endpoint:** `GET /api/v1/staff?estado=true`

**Response (200 OK):**
```json
{
  "mensaje": "Staff filtrado obtenido exitosamente",
  "total": 2,
  "staff": [...]
}
```

### 6. Actualizar Staff
**Endpoint:** `PUT /api/v1/staff/1`

**Request Body:**
```json
{
  "nombre": "Juan Pérez García",
  "estado": true,
  "horarios": [
    {
      "diaSemana": 0,
      "horaInicio": 10,
      "horaFin": 18
    },
    {
      "diaSemana": 6,
      "horaInicio": 9,
      "horaFin": 15
    }
  ]
}
```

**Response (200 OK):**
```json
{
  "mensaje": "Staff actualizado exitosamente",
  "staff": {
    "id": 1,
    "nombre": "Juan Pérez García",
    "estado": true,
    "horarios": [
      {
        "id": 1,
        "diaSemana": 0,
        "horaInicio": 10,
        "horaFin": 18,
        "nombreDia": "Domingo"
      },
      {
        "id": 2,
        "diaSemana": 6,
        "horaInicio": 9,
        "horaFin": 15,
        "nombreDia": "Sábado"
      }
    ]
  }
}
```

### 7. Eliminar Staff (con cascada de horarios)
**Endpoint:** `DELETE /api/v1/staff/1`

**Response (204 No Content):**
(sin cuerpo)

---

## HORARIOS

### 1. Crear Horario para un Staff
**Endpoint:** `POST /api/v1/horarios/staff/2`

**Request Body:**
```json
{
  "diaSemana": 6,
  "horaInicio": 10,
  "horaFin": 16
}
```

**Response (201 Created):**
```json
{
  "mensaje": "Horario creado exitosamente",
  "horario": {
    "id": 6,
    "diaSemana": 6,
    "horaInicio": 10,
    "horaFin": 16,
    "nombreDia": "Sábado"
  }
}
```

### 2. Obtener Horario por ID
**Endpoint:** `GET /api/v1/horarios/1`

**Response (200 OK):**
```json
{
  "mensaje": "Horario obtenido exitosamente",
  "horario": {
    "id": 1,
    "diaSemana": 1,
    "horaInicio": 8,
    "horaFin": 17,
    "nombreDia": "Lunes"
  }
}
```

### 3. Obtener Horarios de un Staff
**Endpoint:** `GET /api/v1/horarios/staff/2`

**Response (200 OK):**
```json
{
  "mensaje": "Horarios obtenidos exitosamente",
  "total": 6,
  "horarios": [
    {
      "id": 1,
      "diaSemana": 1,
      "horaInicio": 8,
      "horaFin": 17,
      "nombreDia": "Lunes"
    },
    {
      "id": 2,
      "diaSemana": 2,
      "horaInicio": 8,
      "horaFin": 17,
      "nombreDia": "Martes"
    },
    {
      "id": 3,
      "diaSemana": 3,
      "horaInicio": 10,
      "horaFin": 19,
      "nombreDia": "Miércoles"
    },
    {
      "id": 4,
      "diaSemana": 4,
      "horaInicio": 8,
      "horaFin": 17,
      "nombreDia": "Jueves"
    },
    {
      "id": 5,
      "diaSemana": 5,
      "horaInicio": 8,
      "horaFin": 18,
      "nombreDia": "Viernes"
    },
    {
      "id": 6,
      "diaSemana": 6,
      "horaInicio": 10,
      "horaFin": 16,
      "nombreDia": "Sábado"
    }
  ]
}
```

### 4. Obtener Horarios de un Día Específico
**Endpoint:** `GET /api/v1/horarios?diaSemana=1`

**Response (200 OK):**
```json
{
  "mensaje": "Horarios obtenidos exitosamente",
  "total": 2,
  "horarios": [
    {
      "id": 1,
      "diaSemana": 1,
      "horaInicio": 8,
      "horaFin": 17,
      "nombreDia": "Lunes"
    },
    {
      "id": 10,
      "diaSemana": 1,
      "horaInicio": 9,
      "horaFin": 18,
      "nombreDia": "Lunes"
    }
  ]
}
```

### 5. Obtener Horarios de un Staff en un Día Específico
**Endpoint:** `GET /api/v1/horarios/staff/2/dia/3`

**Response (200 OK):**
```json
{
  "mensaje": "Horarios obtenidos exitosamente",
  "total": 1,
  "horarios": [
    {
      "id": 3,
      "diaSemana": 3,
      "horaInicio": 10,
      "horaFin": 19,
      "nombreDia": "Miércoles"
    }
  ]
}
```

### 6. Actualizar Horario
**Endpoint:** `PUT /api/v1/horarios/1`

**Request Body:**
```json
{
  "diaSemana": 1,
  "horaInicio": 9,
  "horaFin": 18
}
```

**Response (200 OK):**
```json
{
  "mensaje": "Horario actualizado exitosamente",
  "horario": {
    "id": 1,
    "diaSemana": 1,
    "horaInicio": 9,
    "horaFin": 18,
    "nombreDia": "Lunes"
  }
}
```

### 7. Eliminar Horario
**Endpoint:** `DELETE /api/v1/horarios/1`

**Response (204 No Content):**
(sin cuerpo)

---

## ERRORES COMUNES

### Error: Staff Duplicado
**Endpoint:** `POST /api/v1/staff`

**Response (400 Bad Request):**
```json
{
  "error": "Argumento inválido",
  "mensaje": "El staff 'Juan Pérez' ya existe",
  "timestamp": 1654321000000
}
```

### Error: Staff No Encontrado
**Endpoint:** `GET /api/v1/staff/999`

**Response (404 Not Found):**
```json
{
  "error": "Staff no encontrado",
  "mensaje": "Staff no encontrado con ID: 999",
  "timestamp": 1654321000000
}
```

### Error: Horario No Encontrado
**Endpoint:** `GET /api/v1/horarios/999`

**Response (404 Not Found):**
```json
{
  "error": "Horario no encontrado",
  "mensaje": "Horario no encontrado con ID: 999",
  "timestamp": 1654321000000
}
```

### Error: Validación de Horario
**Endpoint:** `POST /api/v1/horarios/staff/1`

**Request Body:**
```json
{
  "diaSemana": 7,
  "horaInicio": 25,
  "horaFin": -1
}
```

**Response (400 Bad Request):**
```json
{
  "error": "Error de validación",
  "mensaje": "Los datos enviados no son válidos",
  "errores": {
    "diaSemana": "El día de la semana debe estar entre 0 y 6",
    "horaInicio": "La hora de inicio debe estar entre 0 y 23",
    "horaFin": "La hora de fin debe estar entre 0 y 23"
  },
  "timestamp": 1654321000000
}
```

---

**Todos los ejemplos están listos para usar con Postman, Insomnia o curl**
