package com.abernathy.mediscreen.mdto.model;

import jakarta.validation.constraints.NotEmpty;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientDtoTest {

    @Test
    void patientDtoTest() {
        // Arrange
        String testLastName = RandomString.make(64);
        String testFirstName = RandomString.make(64);
        String testBirthdate = RandomString.make(64);
        String testSex = RandomString.make(64);
        String testAddress = RandomString.make(64);
        String testPhone = RandomString.make(64);

        // Act
        PatientDto testDto = new PatientDto();
        testDto.setLastName(testLastName);
        testDto.setFirstName(testFirstName);
        testDto.setBirthdate(testBirthdate);
        testDto.setSex(testSex);
        testDto.setAddress(testAddress);
        testDto.setPhone(testPhone);

        // Assert
        assertThat(testDto.getLastName()).isEqualTo(testLastName);
        assertThat(testDto.getFirstName()).isEqualTo(testFirstName);
        assertThat(testDto.getLastName()).isEqualTo(testLastName);
        assertThat(testDto.getBirthdate()).isEqualTo(testBirthdate);
        assertThat(testDto.getSex()).isEqualTo(testSex);
        assertThat(testDto.getAddress()).isEqualTo(testAddress);
        assertThat(testDto.getPhone()).isEqualTo(testPhone);




    }
}
