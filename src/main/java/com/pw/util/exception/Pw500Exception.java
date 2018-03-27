package com.pw.util.exception;

/**
 * Created by PoemWhite on 2018/3/22.
 */
public class Pw500Exception extends PwRuntimeException{

    public Pw500Exception() {
        super();
    }

    public Pw500Exception(String message) {
        super(message);
    }

    public Pw500Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Pw500Exception(Throwable cause) {
        super(cause);
    }
}
