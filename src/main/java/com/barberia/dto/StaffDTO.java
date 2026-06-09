package com.barberia.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para Staff - Utilizado para transferencia de datos de entrada de barbero
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDTO {

    /**
     * Identificador del staff/barbero
     */
    private Long id;

    /**
     * Nombre del barbero
     */
    @NotBlank(message = "El nombre del barbero es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    /**
     * Estado del barbero (true = ACTIVO, false = INACTIVO)
     */
    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    /**
     * Lista de horarios del barbero
     */
    @Valid
    private List<ScheduleDTO> horarios;
}
