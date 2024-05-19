package com.example.entrega.exception;

import java.io.Serial;

public class EntregadorException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public EntregadorException(String message, Exception e) {super(message, e);}

    public EntregadorException(String message) {super(message);}
}
