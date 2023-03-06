package com.abernathy.mediscreen.mpatient.controller;

import com.abernathy.mediscreen.mpatient.GenerateTestData;
import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.model.Patient;
import com.abernathy.mediscreen.mpatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    PatientController controllerUnderTest;

    @Mock
    PatientServiceImpl patientService;

    @Test
    void addTest() {
        // Arrange
        MultiValueMap<String,String> testMap = new LinkedMultiValueMap<String, String>();
        // Act
        controllerUnderTest.add(testMap);
        // Assert
        verify(patientService, times(1)).importFromUrl(any());
    }


    @Test
    void getAllTest() {
        // Act
        controllerUnderTest.getAll();
        // Assert
        verify(patientService, times(1)).getAll();
    }

    @Test
    void getByIdTest() throws PatientNotFoundException {
        // Arrange
        int testId = new Random().nextInt();
        Patient testPatient = GenerateTestData.patient();
        when(patientService.getById(testId)).thenReturn(testPatient);
        // Act
        controllerUnderTest.getById(testId);
        // Assert
        verify(patientService, times(1)).getById(testId);
    }

    @Test
    void getByIdWithPatientNotFoundExceptionTest() throws PatientNotFoundException {
        // Assert
        when(patientService.getById(any())).thenThrow(PatientNotFoundException.class);
        // Act + Assert
        assertThrows(PatientNotFoundException.class, () -> controllerUnderTest.getById(0));
    }

    @Test
    void updateTest() throws PatientNotFoundException {
        // Arrange
        Patient testPatient = GenerateTestData.patient();
        // Act
        controllerUnderTest.update(testPatient);
        // Assert
        verify(patientService, times(1)).update(testPatient);
    }

    @Test
    void updateWithPatientNotFoundExceptionTest() throws PatientNotFoundException {
        // Arrange
        Patient testPatient = GenerateTestData.patient();
        when(patientService.update(testPatient)).thenThrow(PatientNotFoundException.class);
        // Act + Assert
        assertThrows(PatientNotFoundException.class, () -> controllerUnderTest.update(testPatient));

    }

    @Test
    void deleteByIdTest() throws PatientNotFoundException {
        // Arrange
        int testId = new Random().nextInt();
        // Act
        controllerUnderTest.deleteById(testId);
        // Assert
        verify(patientService, times(1)).deleteById(testId);
    }

    @Test
    void deleteByIdWithPatientNotFoundExceptionTest() throws PatientNotFoundException {
        // Assert
        doThrow(PatientNotFoundException.class).when(patientService).deleteById(any());
        // Act + Assert
        assertThrows(PatientNotFoundException.class, () -> controllerUnderTest.deleteById(0));
    }


}