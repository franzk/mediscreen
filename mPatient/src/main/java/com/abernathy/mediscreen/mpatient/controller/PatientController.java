package com.abernathy.mediscreen.mpatient.controller;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mpatient.model.PatientUrlDto;
import com.abernathy.mediscreen.mpatient.service.PatientMapper;
import com.abernathy.mediscreen.mpatient.service.PatientService;
import com.abernathy.mediscreen.mpatient.service.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API Requests for "/patient" urls <br>
 * Contains CRUD operations for {@link Patient}  class
 */

@RestController
@RequestMapping("/patient")
@Log4j2
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @Value("${patient.api.deletecompleted}")
    private String deleteCompletedMessage;

    public PatientController(PatientServiceImpl patientServiceImpl, PatientMapper patientMapper) {
        this.patientService = patientServiceImpl;
        this.patientMapper = patientMapper;
    }

    /**
     * POST operation : create a patient from url encoded parameter
     * @param patientUrlDto dto for url encoded data
     * @return the created {@link Patient} and status code 200 (OK)
     * @throws DateFormatException if format YYYY-MM-DD is not respected
     * @apiNote : curl -d "family=XXX&given=XXXX&dob=YYYY-MM-DD&sex=F&address=XXX&phone=XXX" -X POST http://localhost:8081/patient/add
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Patient> createFromUrl(@Valid PatientUrlDto patientUrlDto) throws DateFormatException {
        return new ResponseEntity<>(patientService.add(patientMapper.patientUrlDtoToPatient(patientUrlDto)), HttpStatus.OK);
    }


    /**
     * POST operation : create a patient from a POST request with patient dto in body
     * @param patientDto {@link PatientDto} in the body of the request
     * @return the created {@link Patient} and status code 200 (OK)
     * @throws DateFormatException if format YYYY-MM-DD is not respected
     * @apiNote curl -X POST http://localhost:8081/patient/insert
     * -H "Content-Type: application/json"
     * -d "{\"lastName\": \"XX\",\"firstName\": \"XX\",\"birthdate\": \"YYYY-MM-DD\",\"sex\": \"F\", \"address\": \"XX\",\"phone\": \"XX\"}"
     */
    @PostMapping(path="/insert")
    public ResponseEntity<Patient> createFromBody(@RequestBody @Valid PatientDto patientDto) throws DateFormatException {
        log.info(patientDto);
        return new ResponseEntity<>(patientService.add(patientMapper.patientDtoToPatient(patientDto)), HttpStatus.OK);
    }

    /**
     * GET (all) operation <br>
     * @return a list of all {@link Patient}s and status code 200 (OK)
     * @apiNote curl -X GET http://localhost:8081/patient/
     */
    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAll() {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    /**
     * GET (by id) operation
     * @param id : the id of the requested {@link Patient}
     * @return the requested {@link PatientDto} and status code 200 (OK)
     * @throws PatientNotFoundException if no patient exists with this id
     * @apiNote curl -X GET http://localhost:8081/patient/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getById(@PathVariable Integer id) throws PatientNotFoundException {
        return new ResponseEntity<>(patientMapper.patientToPatientDto(patientService.getById(id)), HttpStatus.OK);
    }

    /**
     * PUT operation
     * @param patientDto the {@link PatientDto} with updated patient data in the body of the request
     * @return the {@link Patient} updated and status code 200 (OK)
     * @throws PatientNotFoundException if no patient exists with the id of the updated {@link Patient}
     * @throws DateFormatException if format YYYY-MM-DD is not respected
     * @apiNote curl -X PUT http://localhost:8081/patient/update
     * -H "Content-Type: application/json"
     * -d "{\"lastName\": \"XX\",\"firstName\": \"XX\",\"birthdate\": \"YYYY-MM-DD\",\"sex\": \"F\", \"address\": \"XX\",\"phone\": \"XX\"}"
     */
    @PutMapping("/update")
    public ResponseEntity<Patient> update(@RequestBody @Valid PatientDto patientDto) throws PatientNotFoundException, DateFormatException {
        return new ResponseEntity<>(patientService.update(patientMapper.patientDtoToPatient(patientDto)), HttpStatus.OK);
    }

    /**
     * DELETE (by id) operation
     * @param id the id of the {@link Patient} to delete
     * @return delete completed message (see message.properties) and status code 200 (OK)
     * @throws PatientNotFoundException if no patient exists with this id
     * @apiNote curl -X DELETE http://localhost:8081/patient/delete/2
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws PatientNotFoundException {
        patientService.deleteById(id);
        return new ResponseEntity<>(deleteCompletedMessage, HttpStatus.OK);
    }

}
