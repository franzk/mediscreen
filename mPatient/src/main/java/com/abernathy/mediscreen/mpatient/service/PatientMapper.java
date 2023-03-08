package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mpatient.exception.DateFormatException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.model.PatientDto;
import com.abernathy.mediscreen.mpatient.model.PatientImportDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class PatientMapper {

    public Patient patientImportDtoToPatient(PatientImportDto patientDto) throws DateFormatException {

        Patient patient = new Patient();

        patient.setLastName(patientDto.getFamily());
        patient.setFirstName(patientDto.getGiven());

        // Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate birthDate = LocalDate.parse(patientDto.getDob(), formatter);
            patient.setBirthdate(birthDate);
        }
        catch(DateTimeParseException exception) {
            throw new DateFormatException();
        }

        patient.setSex(patientDto.getSex());
        patient.setAddress(patientDto.getAddress());
        patient.setPhone(patientDto.getPhone());

        return patient;
    }

    public Patient patientDtoToPatient(PatientDto patientDto) throws DateFormatException {

        Patient patient = new Patient();

        patient.setId(patientDto.getId());

        patient.setLastName(patientDto.getLastName());
        patient.setFirstName(patientDto.getFirstName());

        // Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate birthDate = LocalDate.parse(patientDto.getBirthdate(), formatter);
            patient.setBirthdate(birthDate);
        }
        catch(DateTimeParseException exception) {
            throw new DateFormatException();
        }

        patient.setSex(patientDto.getSex());
        patient.setAddress(patientDto.getAddress());
        patient.setPhone(patientDto.getPhone());

        return patient;
    }

}
