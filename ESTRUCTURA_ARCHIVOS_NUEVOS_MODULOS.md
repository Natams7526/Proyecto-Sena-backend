# Estructura de Archivos - Módulos Nuevos

## 📁 Módulo 1: PRODUCTOS

```
src/main/java/com/barberia/
├── model/
│   └── Product.java                    (Entidad JPA)
├── dto/
│   ├── ProductDTO.java                 (DTO de entrada)
│   └── ProductResponseDTO.java         (DTO de respuesta)
├── repository/
│   └── ProductRepository.java          (JPA Repository)
├── mapper/
│   └── ProductMapper.java              (Mapeo Entidad ↔ DTOs)
├── service/
│   ├── ProductService.java             (Interfaz)
│   └── impl/
│       └── ProductServiceImpl.java      (Implementación)
└── controller/
    └── ProductController.java          (Controlador REST)
```

### Rutas Base
- **API Base:** `/api/v1/productos`
- **Métodos:** POST (crear), GET (listar), GET/{id} (obtener), PUT/{id} (actualizar), DELETE/{id} (eliminar)

---

## 📁 Módulo 2: STAFF / HORARIOS

```
src/main/java/com/barberia/
├── model/
│   ├── Staff.java                      (Entidad JPA - Barbero)
│   └── Schedule.java                   (Entidad JPA - Horario)
├── dto/
│   ├── StaffDTO.java                   (DTO de entrada Staff)
│   ├── StaffResponseDTO.java           (DTO de respuesta Staff)
│   ├── ScheduleDTO.java                (DTO de entrada Schedule)
│   └── ScheduleResponseDTO.java        (DTO de respuesta Schedule)
├── repository/
│   ├── StaffRepository.java            (JPA Repository Staff)
│   └── ScheduleRepository.java         (JPA Repository Schedule)
├── mapper/
│   ├── StaffMapper.java                (Mapeo Entidad ↔ DTOs Staff)
│   └── ScheduleMapper.java             (Mapeo Entidad ↔ DTOs Schedule)
├── service/
│   ├── StaffService.java               (Interfaz Staff)
│   ├── ScheduleService.java            (Interfaz Schedule)
│   └── impl/
│       ├── StaffServiceImpl.java        (Implementación Staff)
│       └── ScheduleServiceImpl.java     (Implementación Schedule)
└── controller/
    ├── StaffController.java            (Controlador REST Staff)
    └── ScheduleController.java         (Controlador REST Schedule)
```

### Rutas Base
- **Staff API:** `/api/v1/staff`
- **Schedules API:** `/api/v1/horarios`

---

## 📊 Resumen de Archivos Creados

| Módulo | Tipo | Nombre | Archivo |
|--------|------|--------|---------|
| Productos | Entity | Product | `model/Product.java` |
| Productos | DTO | ProductDTO | `dto/ProductDTO.java` |
| Productos | DTO | ProductResponseDTO | `dto/ProductResponseDTO.java` |
| Productos | Repository | ProductRepository | `repository/ProductRepository.java` |
| Productos | Mapper | ProductMapper | `mapper/ProductMapper.java` |
| Productos | Service | ProductService | `service/ProductService.java` |
| Productos | Service Impl | ProductServiceImpl | `service/impl/ProductServiceImpl.java` |
| Productos | Controller | ProductController | `controller/ProductController.java` |
| Staff | Entity | Staff | `model/Staff.java` |
| Staff | Entity | Schedule | `model/Schedule.java` |
| Staff | DTO | StaffDTO | `dto/StaffDTO.java` |
| Staff | DTO | StaffResponseDTO | `dto/StaffResponseDTO.java` |
| Staff | DTO | ScheduleDTO | `dto/ScheduleDTO.java` |
| Staff | DTO | ScheduleResponseDTO | `dto/ScheduleResponseDTO.java` |
| Staff | Repository | StaffRepository | `repository/StaffRepository.java` |
| Staff | Repository | ScheduleRepository | `repository/ScheduleRepository.java` |
| Staff | Mapper | StaffMapper | `mapper/StaffMapper.java` |
| Staff | Mapper | ScheduleMapper | `mapper/ScheduleMapper.java` |
| Staff | Service | StaffService | `service/StaffService.java` |
| Staff | Service | ScheduleService | `service/ScheduleService.java` |
| Staff | Service Impl | StaffServiceImpl | `service/impl/StaffServiceImpl.java` |
| Staff | Service Impl | ScheduleServiceImpl | `service/impl/ScheduleServiceImpl.java` |
| Staff | Controller | StaffController | `controller/StaffController.java` |
| Staff | Controller | ScheduleController | `controller/ScheduleController.java` |

**Total: 24 archivos creados**

---

## 🔗 Relaciones entre Entidades

### Relación Staff ↔ Schedule
```
Staff (1) ──── (N) Schedule
  ├── OneToMany en Staff.horarios
  ├── ManyToOne en Schedule.staff
  ├── CascadeType.ALL (eliminar staff → elimina horarios)
  └── OrphanRemoval = true
```

---

## ✅ Checklist de Implementación

- [x] Entidades JPA con Lombok
- [x] DTOs de entrada y salida separados
- [x] Repositories con Spring Data JPA
- [x] Mappers bidireccionales
- [x] Servicios con @Service y @Transactional
- [x] Implementaciones completas
- [x] Controladores REST con endpoints CRUD
- [x] Validaciones con Jakarta Validation
- [x] Logging con SLF4J
- [x] Manejo de excepciones integrado
- [x] Respuestas HTTP consistentes
- [x] Relaciones OneToMany/ManyToOne configuradas
- [x] Documentación completa

---

## 🚀 Pasos Siguientes

1. **Compilar el proyecto:**
   ```bash
   mvn clean package
   ```

2. **Crear las tablas en la base de datos:**
   ```sql
   -- Productos
   CREATE TABLE productos (
       id BIGSERIAL PRIMARY KEY,
       nombre VARCHAR(150) NOT NULL UNIQUE,
       estado BOOLEAN NOT NULL,
       stock_disponible INTEGER NOT NULL,
       cantidad INTEGER NOT NULL,
       precio DECIMAL(10,2) NOT NULL
   );

   -- Staff
   CREATE TABLE staff (
       id BIGSERIAL PRIMARY KEY,
       nombre VARCHAR(100) NOT NULL UNIQUE,
       estado BOOLEAN NOT NULL
   );

   -- Horarios
   CREATE TABLE horarios (
       id BIGSERIAL PRIMARY KEY,
       dia_semana INTEGER NOT NULL,
       hora_inicio INTEGER NOT NULL,
       hora_fin INTEGER NOT NULL,
       staff_id BIGINT NOT NULL,
       FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE
   );
   ```

3. **Ejecutar la aplicación:**
   ```bash
   java -jar target/servicios-0.0.1-SNAPSHOT.jar
   ```

4. **Probar los endpoints con Postman o curl**

---

## 📝 Notas Importantes

1. **Validaciones:**
   - Todos los DTOs incluyen validaciones exhaustivas
   - Los nombres de Productos y Staff deben ser únicos
   - Los horarios tienen rangos validados (0-23 para horas, 0-6 para días)

2. **Relaciones:**
   - Al eliminar un Staff, se eliminan automáticamente sus horarios (cascada)
   - Los horarios no pueden existir sin un Staff

3. **Respuestas:**
   - Todas las respuestas siguen el patrón: `{ mensaje, total, data }`
   - Los errores son manejados por GlobalExceptionHandler

4. **Logging:**
   - Se registran todas las operaciones CRUD
   - Se registran errores y advertencias

5. **Transacciones:**
   - Las consultas son de solo lectura (@Transactional(readOnly = true))
   - Las modificaciones son transaccionales

---

**Generado:** Junio 2026
**Versión:** 1.0
