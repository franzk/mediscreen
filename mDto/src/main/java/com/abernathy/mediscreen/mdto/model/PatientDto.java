package com.abernathy.mediscreen.mdto.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {

    Integer id;

    @NotEmpty(message = "aa")
    private String lastName;

    @NotEmpty(message = "aa")
    private String firstName;

    @NotEmpty(message = "aa")
    private String birthdate;

    @NotEmpty(message = "aa")
    private String sex;

    private String address;

    private String phone;

}