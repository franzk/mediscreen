package com.abernathy.mediscreen.mpatient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientImportDto {

    private String family;

    private String given;

    private String dob;

    private String sex;

    private String address;

    private String phone;

}
