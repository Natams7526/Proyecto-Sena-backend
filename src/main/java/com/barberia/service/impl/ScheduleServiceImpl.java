package com.barberia.service.impl;

import com.barberia.dto.ScheduleDTO;
import com.barberia.dto.ScheduleResponseDTO;
import com.barberia.exception.ResourceNotFoundException;
import com.barberia.mapper.ScheduleMapper;
import com.barberia.model.Schedule;
import com.barberia.model.Staff;
import com.barberia.repository.ScheduleRepository;
import com.barberia.repository.StaffRepository;
import com.barberia.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Schedule
 * Proporciona la lógica de negocio para operaciones CRUD de horarios
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    /**
     * Inyección del repositorio de Schedule
     */
    private final ScheduleRepository scheduleRepository;

    /**
     * Inyección del repositorio de Staff
     */
    private final StaffRepository staffRepository;

    /**
     * Inyección del mapper de Schedule
     */
    private final ScheduleMapper scheduleMapper;

    /**
     * Crea un nuevo horario para un staff
     */
    @Override
    public ScheduleResponseDTO crearHorario(Long staffId, ScheduleDTO scheduleDTO) {
        log.info("Creando nuevo horario para staff con ID: {}", staffId);

        // Buscar el staff
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> {
                    log.error("Staff no encontrado con ID: {}", staffId);
                    return new ResourceNotFoundException("Staff no encontrado con ID: " + staffId);
                });

        // Crear el horario
        Schedule schedule = scheduleMapper.dtoToSchedule(scheduleDTO);
        schedule.setStaff(staff);
        Schedule scheduleGuardado = scheduleRepository.save(schedule);

        log.info("Horario creado exitosamente con ID: {}", scheduleGuardado.getId());
        return scheduleMapper.scheduleToResponseDTO(scheduleGuardado);
    }

    /**
     * Actualiza los datos de un horario existente
     */
    @Override
    public ScheduleResponseDTO actualizarHorario(Long id, ScheduleDTO scheduleDTO) {
        log.info("Actualizando horario con ID: {}", id);

        // Buscar el horario
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Horario no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Horario no encontrado con ID: " + id);
                });

        // Actualizar los datos
        schedule.setDiaSemana(scheduleDTO.getDiaSemana());
        schedule.setHoraInicio(scheduleDTO.getHoraInicio());
        schedule.setHoraFin(scheduleDTO.getHoraFin());

        Schedule scheduleActualizado = scheduleRepository.save(schedule);

        log.info("Horario actualizado exitosamente con ID: {}", id);
        return scheduleMapper.scheduleToResponseDTO(scheduleActualizado);
    }

    /**
     * Obtiene un horario por su ID
     */
    @Override
    @Transactional(readOnly = true)
    public ScheduleResponseDTO obtenerHorarioPorId(Long id) {
        log.info("Buscando horario con ID: {}", id);

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Horario no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Horario no encontrado con ID: " + id);
                });

        return scheduleMapper.scheduleToResponseDTO(schedule);
    }

    /**
     * Obtiene todos los horarios de un staff específico
     */
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponseDTO> listarHorariosPorStaff(Long staffId) {
        log.info("Obteniendo horarios para staff con ID: {}", staffId);

        // Verificar que el staff existe
        if (!staffRepository.existsById(staffId)) {
            log.error("Staff no encontrado con ID: {}", staffId);
            throw new ResourceNotFoundException("Staff no encontrado con ID: " + staffId);
        }

        return scheduleRepository.findByStaffId(staffId)
                .stream()
                .map(scheduleMapper::scheduleToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los horarios para un día específico
     */
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponseDTO> listarHorariosPorDia(Integer diaSemana) {
        log.info("Obteniendo horarios para día: {}", diaSemana);

        return scheduleRepository.findByDiaSemana(diaSemana)
                .stream()
                .map(scheduleMapper::scheduleToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los horarios de un staff para un día específico
     */
    @Override
    @Transactional(readOnly = true)
    public List<ScheduleResponseDTO> listarHorariosPorStaffYDia(Long staffId, Integer diaSemana) {
        log.info("Obteniendo horarios para staff {} en día {}", staffId, diaSemana);

        // Verificar que el staff existe
        if (!staffRepository.existsById(staffId)) {
            log.error("Staff no encontrado con ID: {}", staffId);
            throw new ResourceNotFoundException("Staff no encontrado con ID: " + staffId);
        }

        return scheduleRepository.findByStaffIdAndDiaSemana(staffId, diaSemana)
                .stream()
                .map(scheduleMapper::scheduleToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Elimina un horario de la base de datos
     */
    @Override
    public void eliminarHorario(Long id) {
        log.info("Eliminando horario con ID: {}", id);

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Horario no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Horario no encontrado con ID: " + id);
                });

        scheduleRepository.delete(schedule);
        log.info("Horario eliminado exitosamente con ID: {}", id);
    }
}
