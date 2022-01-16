package com.github.veronicazzzz.storageserverclient.Exception;

public class NonSuccessResponseException extends Exception {
    public NonSuccessResponseException(String errorMessage) {
        super(errorMessage);
    }
}