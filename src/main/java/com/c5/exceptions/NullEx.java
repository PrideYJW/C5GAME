package com.c5.exceptions;

/**
 * Created by Administrator on 2017/11/4.
 */
public class NullEx extends RuntimeException {

    public NullEx() {
        super();
    }

    public NullEx(String message) {
        super(message);
    }

    public NullEx(String message, Throwable cause) {
        super(message, cause);
    }

    public NullEx(Throwable cause) {
        super(cause);
    }

    protected NullEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
