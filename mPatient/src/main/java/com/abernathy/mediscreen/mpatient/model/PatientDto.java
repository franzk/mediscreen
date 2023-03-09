package com.abernathy.mediscreen.mpatient.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
