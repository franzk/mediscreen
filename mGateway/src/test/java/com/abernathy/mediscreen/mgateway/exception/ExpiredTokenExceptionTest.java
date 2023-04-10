package com.abernathy.mediscreen.mgateway.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExpiredTokenExceptionTest {

    @Test
    void expiredTokenExceptionTest() {
        ExpiredTokenException exception = new ExpiredTokenException();
        assertThat(exception.getMessage()).isNotNull();

    }
}
