package com.barberia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO para las solicitudes de creación y actualización de un servicio.
 */
public class ServicioRequestDTO {

    @NotBlank(message = "El nombre del servicio es obligatorio")
    private String nombre;

    @NotNull(message = "El precio del servicio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a cero")
    private BigDecimal precio;

    @NotNull(message = "El tiempo del servicio es obligatorio")
    @Positive(message = "El tiempo debe ser un valor positivo")
    private Integer tiempo;

    @NotEmpty(message = "La lista de barberos no puede estar vacía")
    private List<Long> barberos;

    public ServicioRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public List<Long> getBarberos() {
        return barberos;
    }

    public void setBarberos(List<Long> barberos) {
        this.barberos = barberos;
    }
}
