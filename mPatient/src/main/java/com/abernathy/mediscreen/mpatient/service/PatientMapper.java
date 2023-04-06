package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mdto.service.DtoDateUtils;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.model.PatientUrlDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class PatientMapper {

    public Patient patientUrlDtoToPatient(PatientUrlDto patientUrlDto) throws DateFormatException {

        Patient patient = new Patient();

        patient.setLastName(patientUrlDto.getFamily());
        patient.setFirstName(patientUrlDto.getGiven());

        // Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate birthDate = LocalDate.parse(patientUrlDto.getDob(), formatter);
            patient.setBirthdate(birthDate);
        }
        catch(DateTimeParseException exception) {
            throw new DateFormatException();
        }

        patient.setSex(patientUrlDto.getSex());
        patient.setAddress(patientUrlDto.getAddress());
        patient.setPhone(patientUrlDto.getPhone());

        return patient;
    }

    public Patient patientDtoToPatient(PatientDto patientDto) throws DateFormatException {

        Patient patient = new Patient();

        patient.setId(patientDto.getId());

        patient.setLastName(patientDto.getLastName());
        patient.setFirstName(patientDto.getFirstName());

        patient.setBirthdate(DtoDateUtils.stringToDate(patientDto.getBirthdate()));

        patient.setSex(patientDto.getSex());
        patient.setAddress(patientDto.getAddress());
        patient.setPhone(patientDto.getPhone());

        return patient;
    }

    public PatientDto patientToPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setLastName(patient.getLastName());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setBirthdate(DtoDateUtils.dateToString(patient.getBirthdate()));
        patientDto.setSex(patient.getSex());
        patientDto.setAddress(patient.getAddress());
        patientDto.setPhone(patient.getPhone());
        return patientDto;
    }

}
