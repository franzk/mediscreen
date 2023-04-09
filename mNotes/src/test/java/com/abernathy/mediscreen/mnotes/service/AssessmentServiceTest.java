package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.repository.AssessmentRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {

    @InjectMocks
    private AssessmentService serviceUnderTest;

    @Mock
    private AssessmentRepository assessmentRepository;

    @Test
    void getTriggersCountTest() {
        // Arrange
        Random random = new Random();
        int testCount = random.nextInt();
        int testPatientId = random.nextInt();
        String testRegex = RandomString.make(64);
        when(assessmentRepository.getTriggersCount(testPatientId, testRegex)).thenReturn(testCount);

        // Act
        int result = serviceUnderTest.getTriggersCount(testPatientId, testRegex);

        // Assert
        assertThat(result).isEqualTo(testCount);

    }

}
