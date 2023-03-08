package com.abernathy.mediscreen.mpatient.controller;

import com.abernathy.mediscreen.mpatient.exception.DateFormatException;
import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.service.PatientService;
import com.abernathy.mediscreen.mpatient.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API Requests for "/patient" urls <br>
 * Contains CRUD operations for {@link Patient}  class
 */

@RestController
@CrossOrigin
@RequestMapping("/patient")
@Log4j2
public class PatientController {

    private final PatientService patientService;

    @Value("${patient.api.deletecompleted}")
    private String deleteCompletedMessage;

    public PatientController(PatientServiceImpl patientServiceImpl) {
        this.patientService = patientServiceImpl;
    }

    /**
     * POST operation : create a patient from a url encoded parameter
     * @param paramMap urlencoded value with fields (family, given, dob, sex, address, phone)
     * @return the created {@link Patient} and status code 200 (OK)
     * @throws DateFormatException if format YYYY-MM-DD is not respected
     * @apiNote : curl -d "family=XXX&given=XXXX&dob=YYYY-MM-DD&sex=F&address=XXX&phone=XXX" -X POST http://domain:port/patient/add
     */
    @PostMapping(path="/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Patient> add(@RequestParam MultiValueMap<String,String> paramMap) throws DateFormatException {
        log.info(paramMap);
        return new ResponseEntity<>(patientService.importFromUrl(paramMap.toSingleValueMap()), HttpStatus.OK);
    }

    @PostMapping(path="/insert")
    public ResponseEntity<Patient> add(@RequestBody Patient patient) throws DateFormatException {
        return new ResponseEntity<>(patientService.add(patient), HttpStatus.OK);
    }

    /**
     * GET (all) operation <br>
     * @return a list of all {@link Patient}s and status code 200 (OK)
     * @apiNote curl -X GET http://domain:port/patient/
     */
    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAll() {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    /**
     * GET (by id) operation
     * @param id : the id of the requested {@link Patient}
     * @return the requested {@link Patient} and status code 200 (OK)
     * @throws PatientNotFoundException if no patient exists with this id
     * @apiNote curl -X GET http://domain:port/patient/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Integer id) throws PatientNotFoundException {
        return new ResponseEntity<>(patientService.getById(id), HttpStatus.OK);
    }

    /**
     * PUT operation
     * @param patient the {@link Patient} updated (JSON)
     * @return the {@link Patient} updated and status code 200 (OK)
     * @throws PatientNotFoundException if no patient exists with the id of the updated {@link Patient}
     * @apiNote curl -X PUT http://domain:port/patient/update
     * -H "Content-Type: application/json"
     * -d "{\"id\": 1,\"lastName\": \"XX\",\"firstName\": \"XX\",\"birthdate\": \"YYYY-MM-DD\",\"address\": \"XX\",\"phone\": \"XX\"}"
     */
    @PutMapping("/update")
    public ResponseEntity<Patient> update(@RequestBody Patient patient) throws PatientNotFoundException {
        return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);
    }

    /**
     * DELETE (by id) operation
     * @param id the id of the {@link Patient} to delete
     * @return the delete completed message (see message.properties) and status code 200 (OK)
     * @throws PatientNotFoundException if no patient exists with this id
     * @apiNote curl -X DELETE http://domain:port/patient/delete/2
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws PatientNotFoundException {
        patientService.deleteById(id);
        return new ResponseEntity<>(deleteCompletedMessage, HttpStatus.OK);
    }

}
