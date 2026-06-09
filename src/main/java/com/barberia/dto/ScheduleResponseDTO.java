package com.barberia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuesta de Schedule - Datos del horario sin información sensible
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDTO {

    /**
     * Identificador del horario
     */
    private Long id;

    /**
     * Día de la semana (0 = Domingo, 1 = Lunes, ... 6 = Sábado)
     */
    private Integer diaSemana;

    /**
     * Hora de inicio del turno (formato 24 horas, 0-23)
     */
    private Integer horaInicio;

    /**
     * Hora de fin del turno (formato 24 horas, 0-23)
     */
    private Integer horaFin;

    /**
     * Nombre del día de la semana
     */
    private String nombreDia;

    /**
     * Obtiene el nombre del día de la semana basado en el número
     * 
     * @param diaSemana número del día (0-6)
     * @return nombre del día en español
     */
    public static String obtenerNombreDia(Integer diaSemana) {
        if (diaSemana == null) {
            return null;
        }
        switch (diaSemana) {
            case 0:
                return "Domingo";
            case 1:
                return "Lunes";
            case 2:
                return "Martes";
            case 3:
                return "Miércoles";
            case 4:
                return "Jueves";
            case 5:
                return "Viernes";
            case 6:
                return "Sábado";
            default:
                return "Desconocido";
        }
    }
}
