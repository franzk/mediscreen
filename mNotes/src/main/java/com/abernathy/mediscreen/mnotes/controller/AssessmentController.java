package com.abernathy.mediscreen.mnotes.controller;

import com.abernathy.mediscreen.mnotes.service.AssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes/assessment")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/")
    public int getTriggersCount(@RequestParam Integer patientId, @RequestParam String triggersRegex) {
        return assessmentService.getTriggersCount(patientId, triggersRegex);
    }

}
