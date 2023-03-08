package com.abernathy.mediscreen.mpatient.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents Patient info details <br>
 * (id, lastName, firstName, birthdate, sex, address, phone)
 */

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String lastName;

    private String firstName;

    private LocalDate birthdate;

    private String sex;

    private String address;

    private String phone;

}