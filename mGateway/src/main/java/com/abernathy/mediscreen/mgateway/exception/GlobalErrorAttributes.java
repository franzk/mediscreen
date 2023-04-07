package com.abernathy.mediscreen.mgateway.exception;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Throwable error = this.getError(request);

        var attributes = new LinkedHashMap<String, Object>();
        attributes.put("status", HttpStatus.BAD_REQUEST.value());
        attributes.put("message", error.getMessage());
        return attributes;
    }

}
