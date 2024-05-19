package com.example.entrega.exception;

import java.io.Serial;

public class EntregaException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public EntregaException(String message, Exception e) {super(message, e);}

    public EntregaException(String message) {super(message);}
}
