package com.abernathy.mediscreen.mpatient.service;

import com.abernathy.mediscreen.mpatient.GenerateTestData;
import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.repository.PatientRepository;
import com.abernathy.mediscreen.mpatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl serviceUnderTest;

    @Mock
    private PatientRepository patientRepository;

    @Test
    void addTest() {
        // Arrange
        Patient patient = GenerateTestData.patient();
        // Act
        serviceUnderTest.add(patient);
        // Assert
        verify(patientRepository, times(1)).save(patient);
    }

//    MockedStatic<LocalDate> localDateMockedStatic = mockStatic(LocalDate.class) {
//        when(LocalDate.parse(any())).thenReturn()
//    };

    @Test
    void getByIdTest() throws PatientNotFoundException {
        // Arrange
        int id = new Random().nextInt();
        Patient testPatient = GenerateTestData.patient();
        when(patientRepository.findById(any())).thenReturn(Optional.of(testPatient));
        // Act
        Patient result = serviceUnderTest.getById(id);
        // Assert
        assertThat(result).isEqualTo(testPatient);
    }

    @Test
    void getByIdWithExceptionTest() {
        assertThrows(PatientNotFoundException.class, () -> serviceUnderTest.getById(0));
    }

    @Test
    void getAllTest() {
        // Act
        serviceUnderTest.getAll();
        // Assert
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void updateTest() throws PatientNotFoundException {
        // Arrange
        Patient testPatient = GenerateTestData.patient();
        when(patientRepository.existsById(any())).thenReturn(true);
        // Act
        serviceUnderTest.update(testPatient);
        // Assert
        verify(patientRepository, times(1)).save(testPatient);
    }

    @Test
    void updateWithPatientNotFoundExceptionTest() {
        // Arrange
        when(patientRepository.existsById(any())).thenReturn(false);
        // Act + Assert
        assertThrows(PatientNotFoundException.class, () -> serviceUnderTest.update(GenerateTestData.patient()));
    }

    @Test
    void deleteById() throws PatientNotFoundException {
        // Arrange
        int id = new Random().nextInt();
        when(patientRepository.existsById(any())).thenReturn(true);
        // Act
        serviceUnderTest.deleteById(id);
        // Assert
        verify(patientRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteByIdWithPatientNotFoundExceptionTest() {
        // Arrange
        when(patientRepository.existsById(any())).thenReturn(false);
        // Act + Assert
        assertThrows(PatientNotFoundException.class, () -> serviceUnderTest.deleteById(0));
    }

}
