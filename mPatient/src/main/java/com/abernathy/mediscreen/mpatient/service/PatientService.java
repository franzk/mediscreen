package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mpatient.exception.DateFormatException;
import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

/**
 * Interface for services that contains CRUD Operations for {@link Patient}
 */
public interface PatientService {

    /**
     * @param patient {@link Patient} to add
     * @return the added {@link Patient}
     */
    Patient add(Patient patient);

    /**
     * @param id the id the requested {@link Patient}
     * @return the requested {@link Patient}
     * @throws PatientNotFoundException if no {@link Patient} exists with this id
     */
    Patient getById(Integer id) throws PatientNotFoundException;

    /**
     * @return a list of all {@link Patient}s
     */
    List<Patient> getAll();

    /**
     * @param patient the {@link Patient} updated
     * @return the updated {@link Patient}
     * @throws PatientNotFoundException if no patient exists with the id of the updated {@link Patient}
     */
    Patient update(Patient patient) throws PatientNotFoundException;

    /**
     * @param id the id the {@link Patient} to delete
     * @throws PatientNotFoundException if no patient exists with this id
     */
    void deleteById(Integer id) throws PatientNotFoundException;

}
