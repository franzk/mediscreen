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

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RiskLevelServiceTest {

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
        testCalculateRiskLevel(10, 50, "M", RiskLevel.LEVEL_3);
        testCalculateRiskLevel(7, 50, "M", RiskLevel.LEVEL_2);
        testCalculateRiskLevel(4, 50, "M", RiskLevel.LEVEL_1);
        testCalculateRiskLevel(10, 25, "M", RiskLevel.LEVEL_3);
        testCalculateRiskLevel(4, 25, "M", RiskLevel.LEVEL_2);
        testCalculateRiskLevel(10, 25, "F", RiskLevel.LEVEL_3);
        testCalculateRiskLevel(6, 25, "F", RiskLevel.LEVEL_2);
        testCalculateRiskLevel(0, 25, "F", RiskLevel.LEVEL_0);

    }


    private void testCalculateRiskLevel(int triggersCount, int patientAge, String patientSex, RiskLevel riskLevelExpected) {
        // Arrange
        when(triggersCountService.getTriggersCount(anyInt())).thenReturn(triggersCount);
        // Act
        RiskLevel result = serviceUnderTest.calculateRiskLevel(random.nextInt(), patientAge, patientSex);
        // Assert
        assertThat(result).isEqualTo(riskLevelExpected);

    }

    @Test
    void calculateAgeTest() {
        // Arrange
        String dob = "2012-12-21";
        int realAge = Period.between(LocalDate.of(2012, 12, 21), LocalDate.now()).getYears();
        // Act
        int age = serviceUnderTest.calculateAge(dob);
        // Assert
        assertThat(age).isEqualTo(realAge);
    }

    @Test
    void calculateAgeWithDateFormatExceptionTest() {
        // Arrange
        String dob = "2012-12-32";
        // Act
        int age = serviceUnderTest.calculateAge(dob);
        // Assert
        assertThat(age).isZero();
    }

}
