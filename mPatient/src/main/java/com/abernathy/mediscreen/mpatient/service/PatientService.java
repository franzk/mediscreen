package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public interface PatientService {

    Patient add(Patient patient);

    Patient getById(Integer id) throws PatientNotFoundException;

    List<Patient> getAll();

    Patient update(Patient patient) throws PatientNotFoundException;

    void deleteById(Integer id) throws PatientNotFoundException;

    default Patient importFromUrl(Map<String, String> params) {
        Patient patient = new Patient();
        patient.setLastName(params.get("family"));
        patient.setFirstName(params.get("given"));

        // Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(params.get("dob"), formatter);
        patient.setBirthdate(birthDate);

        patient.setAddress(params.get("address"));
        patient.setPhone(params.get("phone"));
        return this.add(patient);
    }

}
