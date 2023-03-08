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

    /**
     * Import patient from encoded value <br>
     * Add the decoded new {@link Patient}
     * @param params a map of parameters. Should contain these keys :  <br>
     *               family                                            <br>
     *               given                                             <br>
     *               dob (yyyy-MM-dd format)                           <br>
     *               address                                           <br>
     *               phone                                             <br>
     * @return the decoded new {@link Patient} added
     * @throws DateFormatException if dob parameter don't respect the yyyy-MM-dd format
     * @see com.abernathy.mediscreen.mpatient.controller.PatientController#add(MultiValueMap)
     */
    default Patient importFromUrl(Map<String, String> params) throws DateFormatException {
        Patient patient = new Patient();
        patient.setLastName(params.get("family"));
        patient.setFirstName(params.get("given"));

        // Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate birthDate = LocalDate.parse(params.get("dob"), formatter);
            patient.setBirthdate(birthDate);
        }
        catch(DateTimeParseException exception) {
            throw new DateFormatException();
        }


        patient.setAddress(params.get("address"));
        patient.setPhone(params.get("phone"));
        return this.add(patient);
    }

}
