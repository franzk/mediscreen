package com.abernathy.mediscreen.massessment.service;

import com.abernathy.mediscreen.massessment.proxy.PatientProxy;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mdto.model.RiskLevelDto;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RiskLevelServiceTest {

    @InjectMocks
    @Spy
    private RiskLevelService serviceUnderTest;

    @Mock
    private TriggersCountService triggersCountService;

    @Mock
    PatientProxy patientProxy;

    Random random = new Random();

    @Test
    void assessmentStringTest() {
        // Arrange
        PatientDto testDto = new PatientDto();
        testDto.setSex(RandomString.make(64));
        when(patientProxy.getPatientById(anyInt())).thenReturn(testDto);
        Mockito.doReturn(random.nextInt()).when(serviceUnderTest).calculateAge(any());
        // Act
        String result = serviceUnderTest.assessmentString(random.nextInt());
        // Assert
        verify(serviceUnderTest).calculateRiskLevel(anyInt(), anyInt(), anyString());
        assertThat(result).isNotEmpty();
    }

    @Test
    void assessmentRiskLevelDtoTest() {
        // Arrange
        RiskLevel testRiskLevel = RiskLevel.LEVEL_1;
        Mockito.doReturn(testRiskLevel).when(serviceUnderTest).calculateRiskLevel(anyInt());
        // Act
        RiskLevelDto result = serviceUnderTest.assessmentRiskLevelDto(random.nextInt());
        // Arrange
        assertThat(result.getValue()).isEqualTo(testRiskLevel.getValue());
        assertThat(result.getMessage()).isEqualTo(testRiskLevel.getMessage());
    }

    @Test
    void calculateRiskLevel1argTest()  {
        // Arrange
        PatientDto testDto = new PatientDto();
        testDto.setSex(RandomString.make(64));
        when(patientProxy.getPatientById(anyInt())).thenReturn(testDto);
        Mockito.doReturn(random.nextInt()).when(serviceUnderTest).calculateAge(any());
        // Act
        RiskLevel result = serviceUnderTest.calculateRiskLevel(anyInt());
        // Assert
        verify(serviceUnderTest, times(1)).calculateRiskLevel(anyInt(), anyInt(), any());
        assertThat(result).isNotNull();
    }

    @Test
    void calculateRiskLevel3argsTest()  {
        // Arrange
        when(triggersCountService.getTriggersCount(anyInt())).thenReturn(random.nextInt());
        // Act
        RiskLevel result = serviceUnderTest.calculateRiskLevel(random.nextInt(), random.nextInt(), RandomString.make(64));
        // Assert
        assertThat(result).isNotNull();
    }


}
