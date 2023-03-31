package com.abernathy.mediscreen.massessment.service;

import com.abernathy.mediscreen.massessment.proxy.AssessmentProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:triggers.properties")
public class TriggersCountService {
    private final AssessmentProxy assessmentProxy;

    public TriggersCountService(AssessmentProxy assessmentProxy) {
        this.assessmentProxy = assessmentProxy;
    }

    @Value("#{${triggers}}")
    private List<String> triggersList;

    public int getTriggersCount(int patientId) {

        String regex = triggersList.stream().collect(Collectors.joining("|", "(", ")")); // "(s1|s2|s3|...)"
        regex += "(?i)"; // case insensitive

        return assessmentProxy.getTriggersCount(patientId, regex);
    }
}