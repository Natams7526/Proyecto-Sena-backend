package com.barberia.repository;

import com.barberia.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Servicio.
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
