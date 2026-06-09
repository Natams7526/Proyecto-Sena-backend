package com.barberia.service.impl;

import com.barberia.dto.StaffDTO;
import com.barberia.dto.StaffResponseDTO;
import com.barberia.exception.ResourceNotFoundException;
import com.barberia.mapper.StaffMapper;
import com.barberia.model.Staff;
import com.barberia.repository.StaffRepository;
import com.barberia.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Staff
 * Proporciona la lógica de negocio para operaciones CRUD de barberos
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StaffServiceImpl implements StaffService {

    /**
     * Inyección del repositorio de Staff
     */
    private final StaffRepository staffRepository;

    /**
     * Inyección del mapper de Staff
     */
    private final StaffMapper staffMapper;

    /**
     * Crea un nuevo staff/barbero en la base de datos
     */
    @Override
    public StaffResponseDTO crearStaff(StaffDTO staffDTO) {
        log.info("Creando nuevo staff: {}", staffDTO.getNombre());

        // Validar que el staff no exista
        if (staffRepository.existsByNombre(staffDTO.getNombre())) {
            log.warn("Intento de crear staff con nombre duplicado: {}", staffDTO.getNombre());
            throw new IllegalArgumentException("El staff '" + staffDTO.getNombre() + "' ya existe");
        }

        // Crear el staff
        Staff staff = staffMapper.dtoToStaff(staffDTO);
        Staff staffGuardado = staffRepository.save(staff);

        log.info("Staff creado exitosamente con ID: {}", staffGuardado.getId());
        return staffMapper.staffToResponseDTO(staffGuardado);
    }

    /**
     * Actualiza los datos de un staff existente
     */
    @Override
    public StaffResponseDTO actualizarStaff(Long id, StaffDTO staffDTO) {
        log.info("Actualizando staff con ID: {}", id);

        // Buscar el staff
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Staff no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Staff no encontrado con ID: " + id);
                });

        // Validar si el nombre cambió y si el nuevo ya existe
        if (!staff.getNombre().equals(staffDTO.getNombre()) &&
                staffRepository.existsByNombre(staffDTO.getNombre())) {
            log.warn("Intento de actualizar a nombre duplicado: {}", staffDTO.getNombre());
            throw new IllegalArgumentException("El staff '" + staffDTO.getNombre() + "' ya existe");
        }

        // Actualizar los datos
        staff.setNombre(staffDTO.getNombre());
        staff.setEstado(staffDTO.getEstado());

        // Actualizar horarios si se proporcionan
        if (staffDTO.getHorarios() != null) {
            staff.getHorarios().clear();
            staffDTO.getHorarios().forEach(scheduleDto -> {
                var schedule = new com.barberia.model.Schedule();
                schedule.setDiaSemana(scheduleDto.getDiaSemana());
                schedule.setHoraInicio(scheduleDto.getHoraInicio());
                schedule.setHoraFin(scheduleDto.getHoraFin());
                schedule.setStaff(staff);
                staff.getHorarios().add(schedule);
            });
        }

        Staff staffActualizado = staffRepository.save(staff);

        log.info("Staff actualizado exitosamente con ID: {}", id);
        return staffMapper.staffToResponseDTO(staffActualizado);
    }

    /**
     * Obtiene un staff por su ID
     */
    @Override
    @Transactional(readOnly = true)
    public StaffResponseDTO obtenerStaffPorId(Long id) {
        log.info("Buscando staff con ID: {}", id);

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Staff no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Staff no encontrado con ID: " + id);
                });

        return staffMapper.staffToResponseDTO(staff);
    }

    /**
     * Obtiene todos los staff del sistema
     */
    @Override
    @Transactional(readOnly = true)
    public List<StaffResponseDTO> listarStaff() {
        log.info("Obteniendo todos los staff");

        return staffRepository.findAll()
                .stream()
                .map(staffMapper::staffToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los staff filtrados por estado
     */
    @Override
    @Transactional(readOnly = true)
    public List<StaffResponseDTO> listarStaffPorEstado(Boolean estado) {
        log.info("Obteniendo staff con estado: {}", estado);

        return staffRepository.findByEstado(estado)
                .stream()
                .map(staffMapper::staffToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Elimina un staff de la base de datos
     */
    @Override
    public void eliminarStaff(Long id) {
        log.info("Eliminando staff con ID: {}", id);

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Staff no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Staff no encontrado con ID: " + id);
                });

        staffRepository.delete(staff);
        log.info("Staff eliminado exitosamente con ID: {}", id);
    }
}
