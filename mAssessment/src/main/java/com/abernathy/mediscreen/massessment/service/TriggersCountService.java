package com.abernathy.mediscreen.massessment.service;

import com.abernathy.mediscreen.massessment.model.TriggersCountResult;
import com.abernathy.mediscreen.massessment.repository.AssessmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class TriggersCountService {

    private final AssessmentRepository assessmentRepository;

    private final TriggersService triggersService;

    public TriggersCountService(AssessmentRepository assessmentRepository, TriggersService triggersService) {
        this.assessmentRepository = assessmentRepository;
        this.triggersService = triggersService;
    }


    /**
     *
     * @param patientId
     * @return
     * @throws JsonProcessingException
     */
    public Integer countTriggers(Integer patientId) throws JsonProcessingException {

        String triggersRegex = triggersService.buildTriggersRegex();

        AggregateIterable<Document> result = assessmentRepository.getTriggersCountAggregationQueryResult(patientId, triggersRegex);

        if (result.into(new ArrayList<>()).isEmpty()) {
            return 0;
        }
        else {
            ObjectMapper mapper = new ObjectMapper();
            String rr = Objects.requireNonNull(result.first()).toJson();
            TriggersCountResult r = mapper.readValue(rr, TriggersCountResult.class);

            return r.getTotal();
        }
    }
}