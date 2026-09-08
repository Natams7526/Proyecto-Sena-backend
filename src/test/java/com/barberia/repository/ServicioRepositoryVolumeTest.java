package com.barberia.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.api.servicios.ServiciosApplication;
import com.barberia.model.Servicio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * Pruebas de volumen para ServicioRepository: verifican que el repositorio
 * soporta operaciones de creación, consulta y eliminación sobre un gran
 * número de registros dentro de límites de tiempo razonables.
 */
@DataJpaTest
@ContextConfiguration(classes = ServiciosApplication.class)
@ActiveProfiles("test")
class ServicioRepositoryVolumeTest {

    private static final int VOLUMEN = 5000;
    private static final long LIMITE_MS = 10000;

    @Autowired
    private ServicioRepository servicioRepository;

    private List<Servicio> generarServicios(int cantidad) {
        List<Servicio> servicios = new ArrayList<>(cantidad);
        for (int i = 0; i < cantidad; i++) {
            Servicio servicio = new Servicio();
            servicio.setNombre("Servicio " + i);
            servicio.setPrecio(BigDecimal.valueOf(10000 + i));
            servicio.setTiempo(15 + (i % 60));
            servicios.add(servicio);
        }
        return servicios;
    }

    @Test
    void debeGuardarGranVolumenDeServiciosCorrectamente() {
        List<Servicio> servicios = generarServicios(VOLUMEN);

        long inicio = System.currentTimeMillis();
        servicioRepository.saveAll(servicios);
        servicioRepository.flush();
        long duracion = System.currentTimeMillis() - inicio;

        long registrosExitosos = servicioRepository.count();
        System.out.println("Registros guardados exitosamente: " + registrosExitosos + "/" + VOLUMEN
                + " (" + duracion + " ms)");

        assertThat(registrosExitosos).isEqualTo(VOLUMEN);
        assertThat(duracion).isLessThan(LIMITE_MS);
    }

    @Test
    void debeConsultarGranVolumenDeServiciosDentroDeTiempoAceptable() {
        servicioRepository.saveAll(generarServicios(VOLUMEN));
        servicioRepository.flush();

        long inicio = System.currentTimeMillis();
        List<Servicio> resultado = servicioRepository.findAll();
        long duracion = System.currentTimeMillis() - inicio;

        System.out.println("Registros consultados exitosamente: " + resultado.size() + "/" + VOLUMEN
                + " (" + duracion + " ms)");

        assertThat(resultado).hasSize(VOLUMEN);
        assertThat(duracion).isLessThan(LIMITE_MS);
    }

    @Test
    void debeEliminarGranVolumenDeServiciosCorrectamente() {
        servicioRepository.saveAll(generarServicios(VOLUMEN));
        servicioRepository.flush();
        assertThat(servicioRepository.count()).isEqualTo(VOLUMEN);

        long inicio = System.currentTimeMillis();
        servicioRepository.deleteAll();
        servicioRepository.flush();
        long duracion = System.currentTimeMillis() - inicio;

        long registrosRestantes = servicioRepository.count();
        long registrosEliminados = VOLUMEN - registrosRestantes;
        System.out.println("Registros eliminados exitosamente: " + registrosEliminados + "/" + VOLUMEN
                + " (" + duracion + " ms)");

        assertThat(registrosRestantes).isZero();
        assertThat(duracion).isLessThan(LIMITE_MS);
    }
}
