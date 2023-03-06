package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient add(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getById(Integer id) throws PatientNotFoundException {
        return patientRepository.findById(id).orElseThrow(PatientNotFoundException::new);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient update(Patient patient) throws PatientNotFoundException {
        if (patientRepository.existsById(patient.getId())) {
            return patientRepository.save(patient);
        }
        else {
            throw new PatientNotFoundException();
        }
    }

    public void deleteById(Integer id) throws PatientNotFoundException {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new PatientNotFoundException();
        }
    }

}
