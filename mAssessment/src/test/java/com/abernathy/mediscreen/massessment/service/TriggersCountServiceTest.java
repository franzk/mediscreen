package com.abernathy.mediscreen.massessment.service;

import com.abernathy.mediscreen.massessment.proxy.AssessmentProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TriggersCountServiceTest {

    @InjectMocks
    private TriggersCountService serviceUnderTest;

    @Mock
    private AssessmentProxy assessmentProxy;

    Random random = new Random();

    @Test
    void getTriggersCountTest() {
        // Arrange
        int testCount = random.nextInt();
        ReflectionTestUtils.setField(serviceUnderTest, "triggersList", new ArrayList<>());
        when(assessmentProxy.getTriggersCount(anyInt(), anyString())).thenReturn(testCount);
        // Act
        int result = serviceUnderTest.getTriggersCount(random.nextInt());
        // Assert
        assertThat(result).isEqualTo(testCount);
    }

}
