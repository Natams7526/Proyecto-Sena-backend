package com.barberia.controller;

import com.barberia.dto.StaffDTO;
import com.barberia.dto.StaffResponseDTO;
import com.barberia.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestión de Staff/Barberos
 * Proporciona endpoints CRUD para operaciones con barberos
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
@Slf4j
public class StaffController {

    /**
     * Inyección del servicio de Staff
     */
    private final StaffService staffService;

    /**
     * POST /api/v1/staff
     * Crea un nuevo staff/barbero
     * 
     * @param staffDTO los datos del staff a crear
     * @return ResponseEntity con el staff creado y estado 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearStaff(@Valid @RequestBody StaffDTO staffDTO) {
        log.info("POST /api/v1/staff - Creando nuevo staff");

        StaffResponseDTO staffCreado = staffService.crearStaff(staffDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Staff creado exitosamente");
        respuesta.put("staff", staffCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * GET /api/v1/staff
     * Obtiene todos los staff
     * 
     * @return ResponseEntity con lista de staff y estado 200 OK
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> listarStaff() {
        log.info("GET /api/v1/staff - Listando todos los staff");

        List<StaffResponseDTO> staff = staffService.listarStaff();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Staff obtenido exitosamente");
        respuesta.put("total", staff.size());
        respuesta.put("staff", staff);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/staff?estado=true
     * Obtiene staff filtrados por estado
     * 
     * @param estado el estado del staff (true = ACTIVO, false = INACTIVO)
     * @return ResponseEntity con lista de staff filtrados y estado 200 OK
     */
    @GetMapping(params = "estado")
    public ResponseEntity<Map<String, Object>> listarStaffPorEstado(
            @RequestParam Boolean estado) {
        log.info("GET /api/v1/staff?estado={} - Listando staff por estado", estado);

        List<StaffResponseDTO> staff = staffService.listarStaffPorEstado(estado);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Staff filtrado obtenido exitosamente");
        respuesta.put("total", staff.size());
        respuesta.put("staff", staff);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/staff/{id}
     * Obtiene un staff por su ID
     * 
     * @param id el ID del staff
     * @return ResponseEntity con el staff y estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerStaffPorId(@PathVariable Long id) {
        log.info("GET /api/v1/staff/{} - Obteniendo staff por ID", id);

        StaffResponseDTO staff = staffService.obtenerStaffPorId(id);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Staff obtenido exitosamente");
        respuesta.put("staff", staff);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * PUT /api/v1/staff/{id}
     * Actualiza un staff existente
     * 
     * @param id el ID del staff a actualizar
     * @param staffDTO los datos actualizados del staff
     * @return ResponseEntity con el staff actualizado y estado 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarStaff(
            @PathVariable Long id,
            @Valid @RequestBody StaffDTO staffDTO) {
        log.info("PUT /api/v1/staff/{} - Actualizando staff", id);

        StaffResponseDTO staffActualizado = staffService.actualizarStaff(id, staffDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Staff actualizado exitosamente");
        respuesta.put("staff", staffActualizado);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * DELETE /api/v1/staff/{id}
     * Elimina un staff
     * 
     * @param id el ID del staff a eliminar
     * @return ResponseEntity sin contenido y estado 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarStaff(@PathVariable Long id) {
        log.info("DELETE /api/v1/staff/{} - Eliminando staff", id);

        staffService.eliminarStaff(id);

        return ResponseEntity.noContent().build();
    }
}
