package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mpatient.exception.DateFormatException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.model.PatientDto;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientMapperTest {

    PatientMapper classUnderTest = new PatientMapper();

    @Test
    void dtoToPatientTest() throws DateFormatException {
        // Arrange
        PatientDto testDto = generateRandomPatientDto(1925, 12, 5);

        // Act
        Patient result = classUnderTest.dtoToPatient(testDto);

        // Assert
        assertThat(result.getLastName()).isEqualTo(testDto.getFamily());
        assertThat(result.getFirstName()).isEqualTo(testDto.getGiven());
        assertThat(result.getBirthdate()).isEqualTo(LocalDate.of(1925, 12, 5));
        assertThat(result.getSex()).isEqualTo(testDto.getSex());
        assertThat(result.getPhone()).isEqualTo(testDto.getPhone());
        assertThat(result.getAddress()).isEqualTo(testDto.getAddress());

    }

    @Test
    void dtoToPatientWithDateFormatExceptionTest()  {
        // Arrange
        PatientDto testDto = generateRandomPatientDto(1988, 13, 14);
        // Act + Assert
        assertThrows(DateFormatException.class, () -> classUnderTest.dtoToPatient(testDto));
    }


    private PatientDto generateRandomPatientDto(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        PatientDto testDto = new PatientDto();
        testDto.setFamily(RandomString.make(64));
        testDto.setGiven(RandomString.make(64));
        testDto.setDob(String.format("%d-%02d-%02d", yearOfBirth, monthOfBirth, dayOfBirth));
        testDto.setSex(RandomString.make(64));
        testDto.setAddress(RandomString.make(64));
        testDto.setPhone(RandomString.make(64));
        return testDto;
    }

}
