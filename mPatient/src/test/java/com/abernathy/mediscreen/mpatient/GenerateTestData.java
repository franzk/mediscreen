package com.abernathy.mediscreen.mpatient;

import com.abernathy.mediscreen.mpatient.model.Patient;
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

}
