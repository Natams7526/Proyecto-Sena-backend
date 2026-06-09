package com.barberia.mapper;

import com.barberia.dto.ScheduleDTO;
import com.barberia.dto.ScheduleResponseDTO;
import com.barberia.model.Schedule;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidad Schedule y DTOs
 * Proporciona métodos para mapeo bidireccional
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Component
public class ScheduleMapper {

    /**
     * Convierte una entidad Schedule a ScheduleDTO
     * 
     * @param schedule la entidad Schedule
     * @return ScheduleDTO con los datos del horario
     */
    public ScheduleDTO scheduleToDTO(Schedule schedule) {
        if (schedule == null) {
            return null;
        }
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .diaSemana(schedule.getDiaSemana())
                .horaInicio(schedule.getHoraInicio())
                .horaFin(schedule.getHoraFin())
                .build();
    }

    /**
     * Convierte un ScheduleDTO a entidad Schedule
     * 
     * @param scheduleDTO el DTO del horario
     * @return Schedule con los datos del DTO
     */
    public Schedule dtoToSchedule(ScheduleDTO scheduleDTO) {
        if (scheduleDTO == null) {
            return null;
        }
        return Schedule.builder()
                .id(scheduleDTO.getId())
                .diaSemana(scheduleDTO.getDiaSemana())
                .horaInicio(scheduleDTO.getHoraInicio())
                .horaFin(scheduleDTO.getHoraFin())
                .build();
    }

    /**
     * Convierte una entidad Schedule a ScheduleResponseDTO
     * 
     * @param schedule la entidad Schedule
     * @return ScheduleResponseDTO con los datos del horario
     */
    public ScheduleResponseDTO scheduleToResponseDTO(Schedule schedule) {
        if (schedule == null) {
            return null;
        }
        return ScheduleResponseDTO.builder()
                .id(schedule.getId())
                .diaSemana(schedule.getDiaSemana())
                .horaInicio(schedule.getHoraInicio())
                .horaFin(schedule.getHoraFin())
                .nombreDia(ScheduleResponseDTO.obtenerNombreDia(schedule.getDiaSemana()))
                .build();
    }

    /**
     * Convierte un ScheduleDTO a ScheduleResponseDTO
     * 
     * @param scheduleDTO el DTO del horario
     * @return ScheduleResponseDTO con los datos del DTO
     */
    public ScheduleResponseDTO dtoToResponseDTO(ScheduleDTO scheduleDTO) {
        if (scheduleDTO == null) {
            return null;
        }
        return ScheduleResponseDTO.builder()
                .id(scheduleDTO.getId())
                .diaSemana(scheduleDTO.getDiaSemana())
                .horaInicio(scheduleDTO.getHoraInicio())
                .horaFin(scheduleDTO.getHoraFin())
                .nombreDia(ScheduleResponseDTO.obtenerNombreDia(scheduleDTO.getDiaSemana()))
                .build();
    }
}
