package com.abernathy.mediscreen.mpatient.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String lastName;

    String firstName;

    LocalDate birthdate;

    String address;

    String phone;

}