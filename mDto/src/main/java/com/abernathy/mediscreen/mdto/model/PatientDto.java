package com.abernathy.mediscreen.mdto.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@PropertySource("classpath:dto_errors.properties")
public class PatientDto {

    Integer id;

    @NotEmpty(message = "${patientdto.lastname.notempty}")
    private String lastName;

    @NotEmpty(message = "${patientdto.firstname.notempty}}")
    private String firstName;

    @NotEmpty(message = "${patientdto.birthdate.notempty}}")
    private String birthdate;

    private String sex;

    private String address;

    private String phone;

}