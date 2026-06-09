package com.barberia.exception;

/**
 * Excepción personalizada cuando un usuario no es encontrado en la base de datos
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
public class UsuarioNotFoundException extends RuntimeException {

    /**
     * Constructor con mensaje de error
     * 
     * @param mensaje el mensaje de error
     */
    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa
     * 
     * @param mensaje el mensaje de error
     * @param causa la causa de la excepción
     */
    public UsuarioNotFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
