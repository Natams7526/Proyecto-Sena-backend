package com.barberia.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.barberia.dto.ServicioRequestDTO;
import com.barberia.dto.ServicioResponseDTO;
import com.barberia.exception.ResourceNotFoundException;
import com.barberia.model.Servicio;
import com.barberia.repository.ServicioRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Pruebas unitarias para ServicioServiceImpl usando mocks del repositorio.
 */
@ExtendWith(MockitoExtension.class)
class ServicioServiceImplTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioServiceImpl servicioService;

    @Test
    void crearServicio_debeGuardarYRetornarDTOConDatosCorrectos() {
        ServicioRequestDTO request = new ServicioRequestDTO();
        request.setNombre("Corte de cabello");
        request.setPrecio(new BigDecimal("25000"));
        request.setTiempo(30);

        Servicio servicioGuardado = new Servicio();
        servicioGuardado.setId(1L);
        servicioGuardado.setNombre("Corte de cabello");
        servicioGuardado.setPrecio(new BigDecimal("25000"));
        servicioGuardado.setTiempo(30);

        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioGuardado);

        ServicioResponseDTO resultado = servicioService.crearServicio(request);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Corte de cabello");
        assertThat(resultado.getPrecio()).isEqualByComparingTo("25000");
        assertThat(resultado.getTiempo()).isEqualTo(30);

        System.out.println("Servicio creado exitosamente: id=" + resultado.getId()
                + ", nombre=" + resultado.getNombre()
                + ", precio=" + resultado.getPrecio()
                + ", tiempo=" + resultado.getTiempo());
    }

    @Test
    void obtenerServicioPorId_cuandoNoExiste_debeLanzarResourceNotFoundException() {
        when(servicioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> servicioService.obtenerServicioPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(servicioRepository, never()).save(any());

        System.out.println("Excepcion ResourceNotFoundException lanzada correctamente para id=99");
    }
}
