package com.abernathy.mediscreen.massessment.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404) {
            return new PatientNotFoundException();
        }
        return defaultErrorDecoder.decode(s, response);
    }
}
