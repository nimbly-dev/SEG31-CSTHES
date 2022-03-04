package com.yorme.fdma.core.model.exceptions;

public class SQLDataNotFound extends RuntimeException{

    public SQLDataNotFound(String errorMessage) {
        super(errorMessage);
    }
}
