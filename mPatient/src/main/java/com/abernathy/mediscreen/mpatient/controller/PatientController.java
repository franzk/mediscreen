package com.abernathy.mediscreen.mpatient.controller;

import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.service.PatientService;
import com.abernathy.mediscreen.mpatient.service.PatientServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patient")
@Log4j2
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientServiceImpl patientServiceImpl) {
        this.patientService = patientServiceImpl;
    }

    @PostMapping(path="/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Patient> add(@RequestParam MultiValueMap<String,String> paramMap) {
        log.info(paramMap);
        return new ResponseEntity<>(patientService.importFromUrl(paramMap.toSingleValueMap()), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> getAll() {
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Integer id) throws PatientNotFoundException {
        return new ResponseEntity<>(patientService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> update(@RequestBody Patient patient) throws PatientNotFoundException {
        return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws PatientNotFoundException {
        patientService.deleteById(id);
        return new ResponseEntity<>("Patient deleted", HttpStatus.OK);
    }

}
