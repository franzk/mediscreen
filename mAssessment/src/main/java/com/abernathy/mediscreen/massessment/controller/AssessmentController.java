package com.abernathy.mediscreen.massessment.controller;

import com.abernathy.mediscreen.massessment.service.RiskLevelService;
import com.abernathy.mediscreen.mdto.model.RiskLevelDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assess")
public class AssessmentController {

    private final RiskLevelService riskLevelService;

    public AssessmentController(RiskLevelService riskLevelService) {
        this.riskLevelService = riskLevelService;
    }

    @PostMapping(path =  "/id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> assessment(@RequestParam Integer patId) {
        return new ResponseEntity<>(riskLevelService.assessment(patId), HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<RiskLevelDto> riskLevel(@PathVariable Integer patientId)  {
        return new ResponseEntity<>(riskLevelService.calculateRiskLevel(patientId), HttpStatus.OK);
    }

}
