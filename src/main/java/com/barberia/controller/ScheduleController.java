package com.barberia.controller;

import com.barberia.dto.ScheduleDTO;
import com.barberia.dto.ScheduleResponseDTO;
import com.barberia.service.ScheduleService;
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
 * Controlador REST para gestión de Horarios (Schedule)
 * Proporciona endpoints CRUD para operaciones con horarios
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/horarios")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    /**
     * Inyección del servicio de Schedule
     */
    private final ScheduleService scheduleService;

    /**
     * POST /api/v1/horarios/staff/{staffId}
     * Crea un nuevo horario para un staff
     * 
     * @param staffId el ID del staff propietario del horario
     * @param scheduleDTO los datos del horario a crear
     * @return ResponseEntity con el horario creado y estado 201 CREATED
     */
    @PostMapping("/staff/{staffId}")
    public ResponseEntity<Map<String, Object>> crearHorario(
            @PathVariable Long staffId,
            @Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("POST /api/v1/horarios/staff/{} - Creando nuevo horario", staffId);

        ScheduleResponseDTO horarioCreado = scheduleService.crearHorario(staffId, scheduleDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Horario creado exitosamente");
        respuesta.put("horario", horarioCreado);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * GET /api/v1/horarios/{id}
     * Obtiene un horario por su ID
     * 
     * @param id el ID del horario
     * @return ResponseEntity con el horario y estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerHorarioPorId(@PathVariable Long id) {
        log.info("GET /api/v1/horarios/{} - Obteniendo horario por ID", id);

        ScheduleResponseDTO horario = scheduleService.obtenerHorarioPorId(id);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Horario obtenido exitosamente");
        respuesta.put("horario", horario);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/horarios/staff/{staffId}
     * Obtiene todos los horarios de un staff
     * 
     * @param staffId el ID del staff
     * @return ResponseEntity con lista de horarios y estado 200 OK
     */
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<Map<String, Object>> listarHorariosPorStaff(@PathVariable Long staffId) {
        log.info("GET /api/v1/horarios/staff/{} - Listando horarios por staff", staffId);

        List<ScheduleResponseDTO> horarios = scheduleService.listarHorariosPorStaff(staffId);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Horarios obtenidos exitosamente");
        respuesta.put("total", horarios.size());
        respuesta.put("horarios", horarios);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/horarios?diaSemana=1
     * Obtiene todos los horarios para un día específico
     * 
     * @param diaSemana el día de la semana (0-6)
     * @return ResponseEntity con lista de horarios filtrados y estado 200 OK
     */
    @GetMapping(params = "diaSemana")
    public ResponseEntity<Map<String, Object>> listarHorariosPorDia(
            @RequestParam Integer diaSemana) {
        log.info("GET /api/v1/horarios?diaSemana={} - Listando horarios por día", diaSemana);

        List<ScheduleResponseDTO> horarios = scheduleService.listarHorariosPorDia(diaSemana);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Horarios obtenidos exitosamente");
        respuesta.put("total", horarios.size());
        respuesta.put("horarios", horarios);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * GET /api/v1/horarios/staff/{staffId}/dia/{diaSemana}
     * Obtiene todos los horarios de un staff para un día específico
     * 
     * @param staffId el ID del staff
     * @param diaSemana el día de la semana (0-6)
     * @return ResponseEntity con lista de horarios filtrados y estado 200 OK
     */
    @GetMapping("/staff/{staffId}/dia/{diaSemana}")
    public ResponseEntity<Map<String, Object>> listarHorariosPorStaffYDia(
            @PathVariable Long staffId,
            @PathVariable Integer diaSemana) {
        log.info("GET /api/v1/horarios/staff/{}/dia/{} - Listando horarios por staff y día", staffId, diaSemana);

        List<ScheduleResponseDTO> horarios = scheduleService.listarHorariosPorStaffYDia(staffId, diaSemana);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Horarios obtenidos exitosamente");
        respuesta.put("total", horarios.size());
        respuesta.put("horarios", horarios);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * PUT /api/v1/horarios/{id}
     * Actualiza un horario existente
     * 
     * @param id el ID del horario a actualizar
     * @param scheduleDTO los datos actualizados del horario
     * @return ResponseEntity con el horario actualizado y estado 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarHorario(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("PUT /api/v1/horarios/{} - Actualizando horario", id);

        ScheduleResponseDTO horarioActualizado = scheduleService.actualizarHorario(id, scheduleDTO);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Horario actualizado exitosamente");
        respuesta.put("horario", horarioActualizado);

        return ResponseEntity.ok(respuesta);
    }

    /**
     * DELETE /api/v1/horarios/{id}
     * Elimina un horario
     * 
     * @param id el ID del horario a eliminar
     * @return ResponseEntity sin contenido y estado 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorario(@PathVariable Long id) {
        log.info("DELETE /api/v1/horarios/{} - Eliminando horario", id);

        scheduleService.eliminarHorario(id);

        return ResponseEntity.noContent().build();
    }
}
