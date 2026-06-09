package com.barberia.exception;

/**
 * Excepción personalizada cuando las credenciales de usuario son inválidas
 * 
 * @author Natalia Muñoz Junior Developer
 * @version 1.0
 */
public class CredencialesInvalidasException extends RuntimeException {

    /**
     * Constructor con mensaje de error
     * 
     * @param mensaje el mensaje de error
     */
    public CredencialesInvalidasException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa
     * 
     * @param mensaje el mensaje de error
     * @param causa la causa de la excepción
     */
    public CredencialesInvalidasException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
