package com.barberia.repository;

import com.barberia.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para la entidad Schedule
 * Proporciona operaciones CRUD y consultas personalizadas para horarios
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * Busca todos los horarios de un staff específico
     * 
     * @param staffId el ID del staff
     * @return Lista de horarios del staff
     */
    List<Schedule> findByStaffId(Long staffId);

    /**
     * Busca todos los horarios para un día específico
     * 
     * @param diaSemana el día de la semana (0-6)
     * @return Lista de horarios para ese día
     */
    List<Schedule> findByDiaSemana(Integer diaSemana);

    /**
     * Busca todos los horarios de un staff para un día específico
     * 
     * @param staffId el ID del staff
     * @param diaSemana el día de la semana (0-6)
     * @return Lista de horarios del staff para ese día
     */
    List<Schedule> findByStaffIdAndDiaSemana(Long staffId, Integer diaSemana);
}
