package com.pw.util.exception;

/**
 * Created by PoemWhite on 2018/3/22.
 */
public class Pw403Exception extends PwRuntimeException{

    public Pw403Exception() {
        super();
    }

    public Pw403Exception(String message) {
        super(message);
    }

    public Pw403Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Pw403Exception(Throwable cause) {
        super(cause);
    }
}
