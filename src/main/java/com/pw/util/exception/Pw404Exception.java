package com.pw.util.exception;

/**
 * Created by PoemWhite on 2018/3/22.
 */
public class Pw404Exception extends PwRuntimeException{

    public Pw404Exception() {
        super();
    }

    public Pw404Exception(String message) {
        super(message);
    }

    public Pw404Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Pw404Exception(Throwable cause) {
        super(cause);
    }
}
