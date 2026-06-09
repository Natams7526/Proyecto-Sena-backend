package com.barberia.service.impl;

import com.barberia.dto.ServicioRequestDTO;
import com.barberia.dto.ServicioResponseDTO;
import com.barberia.exception.ResourceNotFoundException;
import com.barberia.mapper.ServicioMapper;
import com.barberia.model.Servicio;
import com.barberia.repository.ServicioRepository;
import com.barberia.service.ServicioService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para la entidad Servicio.
 */
@Service
@Transactional
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public ServicioResponseDTO crearServicio(ServicioRequestDTO servicioRequestDTO) {
        Servicio servicio = ServicioMapper.toEntity(servicioRequestDTO);
        Servicio servicioGuardado = servicioRepository.save(servicio);
        return ServicioMapper.toDto(servicioGuardado);
    }

    @Override
    public ServicioResponseDTO actualizarServicio(Long id, ServicioRequestDTO servicioRequestDTO) {
        Servicio servicioExistente = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", id));

        ServicioMapper.updateEntity(servicioExistente, servicioRequestDTO);
        Servicio servicioActualizado = servicioRepository.save(servicioExistente);
        return ServicioMapper.toDto(servicioActualizado);
    }

    @Override
    public void eliminarServicio(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", id));
        servicioRepository.delete(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public ServicioResponseDTO obtenerServicioPorId(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", id));
        return ServicioMapper.toDto(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioResponseDTO> listarServicios() {
        return servicioRepository.findAll().stream()
                .map(ServicioMapper::toDto)
                .collect(Collectors.toList());
    }
}
