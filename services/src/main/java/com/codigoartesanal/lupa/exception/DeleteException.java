package com.codigoartesanal.lupa.exception;

/**
 * Created by betuzo on 19/02/16.
 */
public class DeleteException extends RuntimeException  {
    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }
}
