package com.barberia.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO para la respuesta de servicio.
 */
public class ServicioResponseDTO {

    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer tiempo;
    private List<Long> barberos;

    public ServicioResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
