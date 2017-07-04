package com.pw.util.exception;

/**
 * Created by PoemWhite on 2017/4/24.
 */
public class PwRuntimeException extends RuntimeException{

    public PwRuntimeException() {
        super();
    }

    public PwRuntimeException(String message) {
        super(message);
    }

    public PwRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PwRuntimeException(Throwable cause) {
        super(cause);
    }
}
