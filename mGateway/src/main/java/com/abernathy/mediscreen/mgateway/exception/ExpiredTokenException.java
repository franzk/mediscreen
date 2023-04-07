package com.abernathy.mediscreen.mgateway.exception;

public class ExpiredTokenException extends Exception {

    public ExpiredTokenException() {
        super("Token Expired");
    }

}
