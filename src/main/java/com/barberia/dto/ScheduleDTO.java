package com.barberia.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Schedule - Utilizado para transferencia de datos de entrada de horario
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDTO {

    /**
     * Identificador del horario
     */
    private Long id;

    /**
     * Día de la semana (0 = Domingo, 1 = Lunes, ... 6 = Sábado)
     */
    @NotNull(message = "El día de la semana es obligatorio")
    @Min(value = 0, message = "El día de la semana debe estar entre 0 y 6")
    @Max(value = 6, message = "El día de la semana debe estar entre 0 y 6")
    private Integer diaSemana;

    /**
     * Hora de inicio del turno (formato 24 horas, 0-23)
     */
    @NotNull(message = "La hora de inicio es obligatoria")
    @Min(value = 0, message = "La hora de inicio debe estar entre 0 y 23")
    @Max(value = 23, message = "La hora de inicio debe estar entre 0 y 23")
    private Integer horaInicio;

    /**
     * Hora de fin del turno (formato 24 horas, 0-23)
     */
    @NotNull(message = "La hora de fin es obligatoria")
    @Min(value = 0, message = "La hora de fin debe estar entre 0 y 23")
    @Max(value = 23, message = "La hora de fin debe estar entre 0 y 23")
    private Integer horaFin;
}
