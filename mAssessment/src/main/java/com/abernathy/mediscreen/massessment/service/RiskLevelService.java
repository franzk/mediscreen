package com.abernathy.mediscreen.massessment.service;

import com.abernathy.mediscreen.massessment.proxy.PatientProxy;
import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mdto.service.DtoDateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class RiskLevelService {

    private final TriggersCountService triggersCountService;

    private final PatientProxy patientProxy;

    public RiskLevelService(TriggersCountService triggersCountService, PatientProxy patientProxy) {
        this.triggersCountService = triggersCountService;
        this.patientProxy = patientProxy;
    }

    private static final String LEVEL_0 = "None";
    private static final String LEVEL_1 = "Borderline";
    private static final String LEVEL_2 = "In Danger";
    private static final String LEVEL_3 = "Early onset";

    public String assessment(int patientId) throws JsonProcessingException {

        PatientDto patient = patientProxy.getPatientById(patientId);
        int patientAge = this.calculateAge(patient.getBirthdate());

        return String.format("%s %s (age %d) diabetes assessment is: %s",
                patient.getLastName(), patient.getFirstName(), patientAge,
                this.riskLevel(patientId, patientAge, patient.getSex()));

    }

    public String riskLevel(int patientId) throws JsonProcessingException {
        PatientDto patient = patientProxy.getPatientById(patientId);
        int patientAge = this.calculateAge(patient.getBirthdate());

        return this.riskLevel(patientId, patientAge, patient.getSex());
    }

    private String riskLevel(int patientId, int patientAge, String patientSex) throws JsonProcessingException {

        int triggersCount = triggersCountService.countTriggers(patientId);

        if (patientAge >= 30) {
            if (triggersCount >= 8) {
                return LEVEL_3; // plus de 30 ans et au moins 8 triggers
            }
            else if (triggersCount >= 6) {
                return LEVEL_2; // plus de 30 ans et au moins 6 triggers
            }
            if (triggersCount >= 2) {
               return LEVEL_1; // plus de 30 ans et au moins 2 triggers
           }
        }
        else if (patientSex.equals("M")) {
            if (triggersCount >= 5) {
                return LEVEL_3; // homme de moins de 30 ans et au moins 5 triggers
            }
            else if (triggersCount >= 3) {
                return LEVEL_2; // homme de moins de 30 ans et au moins 3 triggers
            }
        }
        else if (patientSex.equals("F")) {
            if (triggersCount >= 7) {
                return LEVEL_3; // femme de moins de 30 ans et au moins 7 triggers
            }
            else if (triggersCount >= 4) {
                return LEVEL_2; // femme de moins de 30 ans et au moins 4 triggers
            }
        }

        return LEVEL_0;

    }

    private int calculateAge(String dob) {

        try {
            LocalDate birthdate = DtoDateUtils.stringToDate(dob);
            return Period.between(birthdate, LocalDate.now()).getYears();
        } catch (DateFormatException e) {
            return 0;
        }
    }

}
