package com.abernathy.mediscreen.mdto.model;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class RiskLevelDtoTest {

    @Test
    void riskLevelDtoTest() {
        // Arrange
        int testValue = new Random().nextInt();
        String testMessage = RandomString.make(64);
        // Act
        RiskLevelDto testDto = new RiskLevelDto();
        testDto.setValue(testValue);
        testDto.setMessage(testMessage);
        // Assert
        assertThat(testDto.getValue()).isEqualTo(testValue);
        assertThat(testDto.getMessage()).isEqualTo(testMessage);

    }

}
