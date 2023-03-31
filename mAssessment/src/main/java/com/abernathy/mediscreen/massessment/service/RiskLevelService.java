package com.abernathy.mediscreen.massessment.service;

import com.abernathy.mediscreen.massessment.proxy.PatientProxy;
import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mdto.model.RiskLevelDto;
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

    public String assessment(int patientId) throws JsonProcessingException {

        PatientDto patient = patientProxy.getPatientById(patientId);
        int patientAge = this.calculateAge(patient.getBirthdate());

        String riskLevelMessage = this.calculateRiskLevel(patientId, patientAge, patient.getSex()).getMessage();

        return String.format("%s %s (age %d) diabetes assessment is: %s",
                patient.getLastName(), patient.getFirstName(), patientAge, riskLevelMessage);

    }

    public RiskLevelDto calculateRiskLevel(int patientId) throws JsonProcessingException {
        PatientDto patient = patientProxy.getPatientById(patientId);
        int patientAge = this.calculateAge(patient.getBirthdate());

        RiskLevel patientRiskLevel = this.calculateRiskLevel(patientId, patientAge, patient.getSex());

        RiskLevelDto riskLevelDto = new RiskLevelDto();
        riskLevelDto.setValue(patientRiskLevel.getValue());
        riskLevelDto.setMessage(patientRiskLevel.getMessage());

        return riskLevelDto;

    }

    private RiskLevel calculateRiskLevel(int patientId, int patientAge, String patientSex) throws JsonProcessingException {

        int triggersCount = triggersCountService.getTriggersCount(patientId);

        if (patientAge >= 30) {
            if (triggersCount >= 8) {
                return RiskLevel.LEVEL_3; // plus de 30 ans et au moins 8 triggers
            }
            else if (triggersCount >= 6) {
                return RiskLevel.LEVEL_2; // plus de 30 ans et au moins 6 triggers
            }
            if (triggersCount >= 2) {
               return RiskLevel.LEVEL_1; // plus de 30 ans et au moins 2 triggers
           }
        }
        else if (patientSex.equals("M")) {
            if (triggersCount >= 5) {
                return RiskLevel.LEVEL_3; // homme de moins de 30 ans et au moins 5 triggers
            }
            else if (triggersCount >= 3) {
                return RiskLevel.LEVEL_2; // homme de moins de 30 ans et au moins 3 triggers
            }
        }
        else if (patientSex.equals("F")) {
            if (triggersCount >= 7) {
                return RiskLevel.LEVEL_3; // femme de moins de 30 ans et au moins 7 triggers
            }
            else if (triggersCount >= 4) {
                return RiskLevel.LEVEL_2; // femme de moins de 30 ans et au moins 4 triggers
            }
        }

        return RiskLevel.LEVEL_0;

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
