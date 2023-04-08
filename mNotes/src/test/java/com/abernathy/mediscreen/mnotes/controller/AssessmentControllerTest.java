package com.abernathy.mediscreen.mnotes.controller;

import com.abernathy.mediscreen.mnotes.service.AssessmentService;
import com.ctc.wstx.shaded.msv_core.verifier.IVerifier;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AssessmentControllerTest {

    @InjectMocks
    private AssessmentController controllerUnderTest;

    @Mock
    private AssessmentService assessmentService;


    @Test
    void getTriggersCountTest() {
        // Arrange
        int patientId = new Random().nextInt();
        String regex = RandomString.make(64);
        // Act
        controllerUnderTest.getTriggersCount(patientId, regex);
        // Assert
        verify(assessmentService, times(1)).getTriggersCount(patientId, regex);
    }

}
