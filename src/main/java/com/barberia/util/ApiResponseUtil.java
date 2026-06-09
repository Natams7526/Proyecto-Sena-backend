package com.barberia.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utilidad para construir respuestas HTTP consistentes.
 */
public final class ApiResponseUtil {

    private ApiResponseUtil() {
        // Clase utilitaria
    }

    public static <T> ResponseEntity<T> created(T body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
