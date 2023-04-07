package com.abernathy.mediscreen.mpatient.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@PropertySource("classpath:messages.properties")
public class PatientUrlDto {

    @NotEmpty(message = "${patient.error.familyrequired}")
    private String family;

    @NotEmpty(message = "${patient.error.givenrequired}")
    private String given;

    @NotEmpty(message = "${patient.error.dobrequired}")
    private String dob;

    @NotEmpty(message = "${patient.error.sexrequired}")
    private String sex;

    private String address;

    private String phone;

}
