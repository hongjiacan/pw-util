package com.pw.util.exception;

/**
 * Created by PoemWhite on 2018/3/22.
 */
public class Pw401Exception extends PwRuntimeException{

    public Pw401Exception() {
        super();
    }

    public Pw401Exception(String message) {
        super(message);
    }

    public Pw401Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Pw401Exception(Throwable cause) {
        super(cause);
    }
}
