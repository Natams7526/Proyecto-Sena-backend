package com.barberia.mapper;

import com.barberia.dto.ServicioRequestDTO;
import com.barberia.dto.ServicioResponseDTO;
import com.barberia.model.Servicio;

/**
 * Clase de mapeo entre entidad Servicio y DTOs.
 */
public final class ServicioMapper {

    private ServicioMapper() {
        // Utilidad estática
    }

    public static ServicioResponseDTO toDto(Servicio servicio) {
        if (servicio == null) {
            return null;
        }

        ServicioResponseDTO respuesta = new ServicioResponseDTO();
        respuesta.setId(servicio.getId());
        respuesta.setNombre(servicio.getNombre());
        respuesta.setPrecio(servicio.getPrecio());
        respuesta.setTiempo(servicio.getTiempo());
        respuesta.setBarberos(servicio.getBarberos());
        return respuesta;
    }

    public static Servicio toEntity(ServicioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Servicio servicio = new Servicio();
        servicio.setNombre(dto.getNombre());
        servicio.setPrecio(dto.getPrecio());
        servicio.setTiempo(dto.getTiempo());
        servicio.setBarberos(dto.getBarberos());
        return servicio;
    }

    public static void updateEntity(Servicio servicio, ServicioRequestDTO dto) {
        servicio.setNombre(dto.getNombre());
        servicio.setPrecio(dto.getPrecio());
        servicio.setTiempo(dto.getTiempo());
        servicio.setBarberos(dto.getBarberos());
    }
}
