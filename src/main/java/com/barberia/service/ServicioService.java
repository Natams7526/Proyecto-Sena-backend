package com.barberia.service;

import com.barberia.dto.ServicioRequestDTO;
import com.barberia.dto.ServicioResponseDTO;
import java.util.List;

/**
 * Contrato de negocio para operaciones CRUD de servicio.
 */
public interface ServicioService {

    ServicioResponseDTO crearServicio(ServicioRequestDTO servicioRequestDTO);

    ServicioResponseDTO actualizarServicio(Long id, ServicioRequestDTO servicioRequestDTO);

    void eliminarServicio(Long id);

    ServicioResponseDTO obtenerServicioPorId(Long id);

    List<ServicioResponseDTO> listarServicios();
}
