package com.abernathy.mediscreen.mgateway.service;

import com.abernathy.mediscreen.mgateway.exception.ExpiredTokenException;
import io.jsonwebtoken.Jwts;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    JwtService serviceUnderTest;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(serviceUnderTest, "secret", RandomString.make(64));
    }

    @Test
    void getSignKeyTest() {
        // Act
        Key result = serviceUnderTest.getSignKey();
        // Assert
        assertThat(result).isNotNull();
    }

}
