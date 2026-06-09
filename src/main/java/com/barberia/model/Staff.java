package com.barberia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Staff - Representa un barbero en el sistema
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {

    /**
     * Identificador único del staff/barbero
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Nombre del barbero
     */
    @NotBlank(message = "El nombre del barbero es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Estado del barbero (true = ACTIVO, false = INACTIVO)
     */
    @NotNull(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    /**
     * Relación OneToMany con Schedule
     * Un Staff puede tener múltiples horarios
     * Cascada: Si se elimina el Staff, se eliminan sus horarios
     */
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Schedule> horarios = new ArrayList<>();
}
