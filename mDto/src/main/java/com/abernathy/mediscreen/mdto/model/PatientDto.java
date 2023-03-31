package com.abernathy.mediscreen.mdto.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {

    Integer id;

    @NotEmpty(message = "{patientdto.lastname.notempty}")
    private String lastName;

    @NotEmpty(message = "{patientdto.firstname.notempty}")
    private String firstName;

    @NotEmpty(message = "{patientdto.birthdate.notempty}")
    private String birthdate;

    private String sex;

    private String address;

    private String phone;

}