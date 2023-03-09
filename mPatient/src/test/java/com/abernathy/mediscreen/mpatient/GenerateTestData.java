package com.abernathy.mediscreen.mpatient;

import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.model.PatientDto;
import com.abernathy.mediscreen.mpatient.model.PatientUrlDto;
import net.bytebuddy.utility.RandomString;

import java.time.LocalDate;
import java.util.Random;

public class GenerateTestData {

    public static Patient patient()  {
        Random random = new Random();
        Patient patient = new Patient();
        patient.setLastName(RandomString.make(64));
        patient.setFirstName(RandomString.make(64));
        patient.setBirthdate(LocalDate.now().minusYears(random.nextInt(100)));
        patient.setAddress(RandomString.make(64));
        patient.setPhone(RandomString.make(64));
        return patient;
    }

    public static PatientUrlDto patientUrlDto(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        PatientUrlDto testDto = new PatientUrlDto();
        testDto.setFamily(RandomString.make(64));
        testDto.setGiven(RandomString.make(64));
        testDto.setDob(String.format("%d-%02d-%02d", yearOfBirth, monthOfBirth, dayOfBirth));
        testDto.setSex(RandomString.make(64));
        testDto.setAddress(RandomString.make(64));
        testDto.setPhone(RandomString.make(64));
        return testDto;
    }

    public static PatientDto patientDto() {
        return patientDto(1999, 12 ,12);
    }

    public static PatientDto patientDto(int yearOfBirth, int monthOfBirth, int dayOfBirth)  {
        PatientDto patientDto = new PatientDto();
        patientDto.setLastName(RandomString.make(64));
        patientDto.setFirstName(RandomString.make(64));
        patientDto.setBirthdate(String.format("%d-%02d-%02d", yearOfBirth, monthOfBirth, dayOfBirth));
        patientDto.setAddress(RandomString.make(64));
        patientDto.setPhone(RandomString.make(64));
        return patientDto;
    }

}
