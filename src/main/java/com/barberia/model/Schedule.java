package com.barberia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Schedule - Representa el horario de un barbero
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Entity
@Table(name = "horarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    /**
     * Identificador único del horario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Día de la semana (0 = Domingo, 1 = Lunes, ... 6 = Sábado)
     */
    @NotNull(message = "El día de la semana es obligatorio")
    @Min(value = 0, message = "El día de la semana debe estar entre 0 y 6")
    @Max(value = 6, message = "El día de la semana debe estar entre 0 y 6")
    @Column(name = "dia_semana", nullable = false)
    private Integer diaSemana;

    /**
     * Hora de inicio del turno (formato 24 horas, 0-23)
     */
    @NotNull(message = "La hora de inicio es obligatoria")
    @Min(value = 0, message = "La hora de inicio debe estar entre 0 y 23")
    @Max(value = 23, message = "La hora de inicio debe estar entre 0 y 23")
    @Column(name = "hora_inicio", nullable = false)
    private Integer horaInicio;

    /**
     * Hora de fin del turno (formato 24 horas, 0-23)
     */
    @NotNull(message = "La hora de fin es obligatoria")
    @Min(value = 0, message = "La hora de fin debe estar entre 0 y 23")
    @Max(value = 23, message = "La hora de fin debe estar entre 0 y 23")
    @Column(name = "hora_fin", nullable = false)
    private Integer horaFin;

    /**
     * Referencia al Staff propietario del horario
     * Relación ManyToOne
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    @NotNull(message = "El Staff es obligatorio")
    private Staff staff;
}
