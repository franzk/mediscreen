package com.abernathy.mediscreen.mpatient.controller;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import com.abernathy.mediscreen.mdto.model.PatientDto;
import com.abernathy.mediscreen.mpatient.GenerateTestData;
import com.abernathy.mediscreen.mpatient.exception.PatientNotFoundException;
import com.abernathy.mediscreen.mpatient.service.PatientMapper;
import com.abernathy.mediscreen.mpatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @InjectMocks
    PatientController controllerUnderTest;

    @Mock
    PatientServiceImpl patientService;

    @Mock
    PatientMapper patientMapper;

//    @Test
//    void importFromUrlTest() throws DateFormatException {
//        // Arrange
//        PatientUrlDto dto = new PatientUrlDto();
//        // Act
//        controllerUnderTest.createFromUrl(dto);
//        // Assert
//        verify(patientMapper, times(1)).patientUrlDtoToPatient(dto);
//        verify(patientService, times(1)).add(any());
//    }

//    @Test
//    void importFromUrlWithDateFormatExceptionTest() throws DateFormatException {
//        // Arrange
//        PatientUrlDto dto = new PatientUrlDto();
//        when(patientMapper.patientUrlDtoToPatient(any())).thenThrow(DateFormatException.class);
//        // Act + Assert
//        assertThrows(DateFormatException.class, () -> controllerUnderTest.createFromUrl(dto));
//    }
//
//    @Test
//    void insertTest() throws DateFormatException {
//        // Arrange
//        PatientDto dto = new PatientDto();
//        // Act
//        controllerUnderTest.createFromBody(dto);
//        // Assert
//        verify(patientMapper, times(1)).patientDtoToPatient(dto);
//        verify(patientService, times(1)).add(any());
//    }
//
//    @Test
//    void insertWithDateFormatExceptionTest() throws DateFormatException {
//        // Arrange
//        PatientDto dto = new PatientDto();
//        when(patientMapper.patientDtoToPatient(any())).thenThrow(DateFormatException.class);
//        // Act + Assert
//        assertThrows(DateFormatException.class, () -> controllerUnderTest.createFromBody(dto));
//    }


    @Test
    void getAllTest() {
        // Act
        controllerUnderTest.getAll();
        // Assert
        verify(patientService, times(1)).getAll();
    }

//    @Test
//    void getByIdTest() throws PatientNotFoundException {
//        // Arrange
//        int testId = new Random().nextInt();
//        Patient testPatient = GenerateTestData.patient();
//        when(patientService.getById(testId)).thenReturn(testPatient);
//        // Act
//        controllerUnderTest.getById(testId);
//        // Assert
//        verify(patientService, times(1)).getById(testId);
//    }

    @Test
    void getByIdWithPatientNotFoundExceptionTest() throws PatientNotFoundException {
        // Assert
        when(patientService.getById(any())).thenThrow(PatientNotFoundException.class);
        // Act + Assert
        assertThrows(PatientNotFoundException.class, () -> controllerUnderTest.getById(0));
    }

    @Test
    void updateTest() throws PatientNotFoundException, DateFormatException {
        // Arrange
        PatientDto testDto = GenerateTestData.patientDto();
        // Act
        controllerUnderTest.update(testDto);
        // Assert
        verify(patientService, times(1)).update(any());
    }

    @Test
    void updateWithPatientNotFoundExceptionTest() throws PatientNotFoundException {
        // Arrange
        PatientDto testDto = GenerateTestData.patientDto();
        when(patientService.update(any())).thenThrow(PatientNotFoundException.class);
        // Act + Assert
        assertThrows(PatientNotFoundException.class, () -> controllerUnderTest.update(testDto));
    }

    @Test
    void updateWithDateFormatExceptionTest() throws DateFormatException {
        // Arrange
        PatientDto testDto = GenerateTestData.patientDto();
        when(patientMapper.patientDtoToPatient(any())).thenThrow(DateFormatException.class);
        // Act + Assert
        assertThrows(DateFormatException.class, () -> controllerUnderTest.update(testDto));
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