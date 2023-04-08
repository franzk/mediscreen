package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import com.abernathy.mediscreen.mdto.service.DtoDateUtils;
import com.abernathy.mediscreen.mpatient.GenerateTestData;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mpatient.model.PatientUrlDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientMapperTest {

    final PatientMapper classUnderTest = new PatientMapper();

    @Test
    void patientUrlDtoToPatientTest() throws DateFormatException {
        // Arrange
        PatientUrlDto testDto = GenerateTestData.patientUrlDto(1925, 12, 5);

        // Act
        Patient result = classUnderTest.patientUrlDtoToPatient(testDto);

        // Assert
        assertThat(result.getLastName()).isEqualTo(testDto.getFamily());
        assertThat(result.getFirstName()).isEqualTo(testDto.getGiven());
        assertThat(result.getBirthdate()).isEqualTo(LocalDate.of(1925, 12, 5));
        assertThat(result.getSex()).isEqualTo(testDto.getSex());
        assertThat(result.getPhone()).isEqualTo(testDto.getPhone());
        assertThat(result.getAddress()).isEqualTo(testDto.getAddress());

    }

    @Test
    void patientUrlDtoToPatientWithDateFormatExceptionTest()  {
        // Arrange
        PatientUrlDto testDto = GenerateTestData.patientUrlDto(1988, 13, 14); // 14/13/1988 is bad
        // Act + Assert
        assertThrows(DateFormatException.class, () -> classUnderTest.patientUrlDtoToPatient(testDto));
    }

    @Test
    void patientDtoToPatientTest() throws DateFormatException {
        // Arrange
        PatientDto testDto = GenerateTestData.patientDto(1958, 5, 22);
        // Act
        Patient result = classUnderTest.patientDtoToPatient(testDto);
        // Assert
        assertThat(result.getId()).isEqualTo(testDto.getId());
        assertThat(result.getFirstName()).isEqualTo(testDto.getFirstName());
        assertThat(result.getLastName()).isEqualTo(testDto.getLastName());
        assertThat(result.getBirthdate()).isEqualTo(LocalDate.of(1958, 5, 22));
        assertThat(result.getSex()).isEqualTo(testDto.getSex());
        assertThat(result.getAddress()).isEqualTo(testDto.getAddress());
        assertThat(result.getPhone()).isEqualTo(testDto.getPhone());

    }

    @Test
    void patientDtoToPatientWithDateFormatExceptionTest()  {
        // Arrange
        PatientDto testDto = GenerateTestData.patientDto(1988, 13, 14); // 14/13/1988 is bad !
        // Act + Assert
        assertThrows(DateFormatException.class, () -> classUnderTest.patientDtoToPatient(testDto));
    }

    @Test
    void patientToPatientDtoTest() {
        // Arrange
        Patient testPatient = GenerateTestData.patient();

        // Act
        PatientDto result = classUnderTest.patientToPatientDto(testPatient);

        // Assert
        assertThat(result.getId()).isEqualTo(testPatient.getId());
        assertThat(result.getFirstName()).isEqualTo(testPatient.getFirstName());
        assertThat(result.getLastName()).isEqualTo(testPatient.getLastName());
        assertThat(result.getBirthdate()).isEqualTo(DtoDateUtils.dateToString(testPatient.getBirthdate()));
        assertThat(result.getSex()).isEqualTo(testPatient.getSex());
        assertThat(result.getAddress()).isEqualTo(testPatient.getAddress());
        assertThat(result.getPhone()).isEqualTo(testPatient.getPhone());

    }


}
