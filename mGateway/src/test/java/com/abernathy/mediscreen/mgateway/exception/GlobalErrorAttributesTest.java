package com.abernathy.mediscreen.mgateway.exception;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalErrorAttributesTest {

    //@InjectMocks
    //GlobalErrorAttributes classUnderTest;

    //@Mock
    //ServerRequest request;

    @Mock
    ErrorAttributeOptions options;

    @Test
    void getErrorAttributesTest() {
        // Arrange
        GlobalErrorAttributes classUnderTest = new GlobalErrorAttributes();
        ServerRequest request = mock(ServerRequest.class);
        when(request.attribute(any())).thenReturn(Optional.of(new Error()));

        // Act
        Map<String, Object> result = classUnderTest.getErrorAttributes(request, options);

        // Assert
        assertThat(result).containsEntry("status", HttpStatus.BAD_REQUEST.value());

    }
}
