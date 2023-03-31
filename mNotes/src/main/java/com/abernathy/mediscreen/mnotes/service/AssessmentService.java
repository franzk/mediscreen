package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.repository.AssessmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;

    public AssessmentService(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    public int getTriggersCount(Integer patientId, String triggersRegex) {
        return assessmentRepository.getTriggersCount(patientId, triggersRegex);
    }

}
