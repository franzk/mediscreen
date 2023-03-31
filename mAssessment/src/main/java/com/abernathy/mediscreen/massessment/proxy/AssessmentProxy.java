package com.abernathy.mediscreen.massessment.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MEDISCREEN-NOTES")
public interface AssessmentProxy {

    @GetMapping("/notes/assessment/")
    int getTriggersCount(@RequestParam Integer patientId, @RequestParam String triggersRegex);

}
