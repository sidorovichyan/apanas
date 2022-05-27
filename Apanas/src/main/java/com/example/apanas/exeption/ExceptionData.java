package com.example.apanas.exeption;

public class ExceptionData extends Exception{
    public ExceptionData() {
        super();
    }

    public ExceptionData(String message) {
        super(message);
    }

    public ExceptionData(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionData(Throwable cause) {
        super(cause);
    }

    protected ExceptionData(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
