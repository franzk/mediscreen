package com.abernathy.mediscreen.massessment.controller;

import com.abernathy.mediscreen.massessment.service.KeywordsCountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assessment")
public class KeywordsCountController {

    private final KeywordsCountService keywordsCountService;

    public KeywordsCountController(KeywordsCountService keywordsCountService) {
        this.keywordsCountService = keywordsCountService;
    }

    @GetMapping("/keywordscount/{patientId}")
    public ResponseEntity<Integer> tt(@PathVariable Integer patientId) throws JsonProcessingException {
        return new ResponseEntity<>(keywordsCountService.countKeywords(patientId), HttpStatus.OK);
    }

}
