package com.github.supermoonie.wcferry4j.exception;

/**
 * @author super_wang
 * @since 2024/6/8
 */
public class WarnException extends RuntimeException {

    public WarnException() {
        super();
    }

    public WarnException(String message) {
        super(message);
    }

    public WarnException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarnException(Throwable cause) {
        super(cause);
    }

    protected WarnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
