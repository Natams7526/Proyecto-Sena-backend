package com.barberia.mapper;

import com.barberia.dto.StaffDTO;
import com.barberia.dto.StaffResponseDTO;
import com.barberia.model.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper para convertir entre entidad Staff y DTOs
 * Proporciona métodos para mapeo bidireccional
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class StaffMapper {

    /**
     * Inyección del mapper de Schedule
     */
    private final ScheduleMapper scheduleMapper;

    /**
     * Convierte una entidad Staff a StaffDTO
     * 
     * @param staff la entidad Staff
     * @return StaffDTO con los datos del staff
     */
    public StaffDTO staffToDTO(Staff staff) {
        if (staff == null) {
            return null;
        }
        return StaffDTO.builder()
                .id(staff.getId())
                .nombre(staff.getNombre())
                .estado(staff.getEstado())
                .horarios(staff.getHorarios() != null 
                    ? staff.getHorarios().stream()
                        .map(scheduleMapper::scheduleToDTO)
                        .collect(Collectors.toList())
                    : null)
                .build();
    }

    /**
     * Convierte un StaffDTO a entidad Staff
     * 
     * @param staffDTO el DTO del staff
     * @return Staff con los datos del DTO
     */
    public Staff dtoToStaff(StaffDTO staffDTO) {
        if (staffDTO == null) {
            return null;
        }
        Staff staff = Staff.builder()
                .id(staffDTO.getId())
                .nombre(staffDTO.getNombre())
                .estado(staffDTO.getEstado())
                .build();

        // Procesar horarios si existen
        if (staffDTO.getHorarios() != null) {
            staff.setHorarios(staffDTO.getHorarios().stream()
                    .map(scheduleDto -> {
                        var schedule = scheduleMapper.dtoToSchedule(scheduleDto);
                        schedule.setStaff(staff);
                        return schedule;
                    })
                    .collect(Collectors.toList()));
        }

        return staff;
    }

    /**
     * Convierte una entidad Staff a StaffResponseDTO
     * 
     * @param staff la entidad Staff
     * @return StaffResponseDTO con los datos del staff
     */
    public StaffResponseDTO staffToResponseDTO(Staff staff) {
        if (staff == null) {
            return null;
        }
        return StaffResponseDTO.builder()
                .id(staff.getId())
                .nombre(staff.getNombre())
                .estado(staff.getEstado())
                .horarios(staff.getHorarios() != null
                    ? staff.getHorarios().stream()
                        .map(scheduleMapper::scheduleToResponseDTO)
                        .collect(Collectors.toList())
                    : null)
                .build();
    }

    /**
     * Convierte un StaffDTO a StaffResponseDTO
     * 
     * @param staffDTO el DTO del staff
     * @return StaffResponseDTO con los datos del DTO
     */
    public StaffResponseDTO dtoToResponseDTO(StaffDTO staffDTO) {
        if (staffDTO == null) {
            return null;
        }
        return StaffResponseDTO.builder()
                .id(staffDTO.getId())
                .nombre(staffDTO.getNombre())
                .estado(staffDTO.getEstado())
                .horarios(staffDTO.getHorarios() != null
                    ? staffDTO.getHorarios().stream()
                        .map(scheduleMapper::dtoToResponseDTO)
                        .collect(Collectors.toList())
                    : null)
                .build();
    }
}
