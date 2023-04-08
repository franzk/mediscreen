package com.abernathy.mediscreen.massessment.controller;

import com.abernathy.mediscreen.massessment.service.RiskLevelService;
import com.abernathy.mediscreen.mdto.model.RiskLevelDto;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssessmentControllerTest {

    @InjectMocks
    private AssessmentController controllerUnderTest;

    @Mock
    private RiskLevelService riskLevelService;

    Random random = new Random();

    @Test
    void assessmentTest() {
        // Arrange
        String expectedResult = RandomString.make(64);
        when(riskLevelService.assessmentString(anyInt())).thenReturn(expectedResult);
        // Act
        ResponseEntity<String> result = controllerUnderTest.assessment(random.nextInt());
        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    void riskLevelTest() {
        // Arrange
        RiskLevelDto expectedResult = new RiskLevelDto();
        expectedResult.setMessage(RandomString.make(64));
        expectedResult.setValue(random.nextInt());
        when(riskLevelService.assessmentRiskLevelDto(anyInt())).thenReturn(expectedResult);
        // Act
        ResponseEntity<RiskLevelDto> result = controllerUnderTest.riskLevel(random.nextInt());
        // Arrange
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(expectedResult);
    }

}
