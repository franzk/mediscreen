package com.abernathy.mediscreen.massessment.proxy;

import com.abernathy.mediscreen.mdto.model.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MEDISCREEN-PATIENT")
public interface PatientProxy {

    @GetMapping("/patient/{id}")
    PatientDto getPatientById(@PathVariable Integer id);

}
