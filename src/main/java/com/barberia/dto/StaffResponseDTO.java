package com.barberia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para respuesta de Staff - Datos del barbero sin información sensible
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffResponseDTO {

    /**
     * Identificador del staff/barbero
     */
    private Long id;

    /**
     * Nombre del barbero
     */
    private String nombre;

    /**
     * Estado del barbero (true = ACTIVO, false = INACTIVO)
     */
    private Boolean estado;

    /**
     * Lista de horarios del barbero
     */
    private List<ScheduleResponseDTO> horarios;
}
