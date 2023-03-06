package com.abernathy.mediscreen.mpatient.repository;

import com.abernathy.mediscreen.mpatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
