package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.exception.InvalidNoteDataException;
import com.abernathy.mediscreen.mnotes.model.Note;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImportNoteServiceTest {

    ImportNoteService serviceUnderTest = new ImportNoteService();

    @Test
    void parseUrlDataTest() throws InvalidNoteDataException {
        // Arrange
        Integer testId = new Random().nextInt();
        String testContent = RandomString.make(64);
        String data = String.format("%d¬e=%s", testId, testContent);

        // Act
        Note result = serviceUnderTest.parseUrlData(data);

        // Assert
        assertThat(result.getPatientId()).isEqualTo(testId);
        assertThat(result.getContent()).isEqualTo(testContent);
    }

    @Test
    void parseUrlDataWithInvalidNoteDataExceptionTest() {
        // Arrange
        String data = "invalid data";

        // Act + Assert
        assertThrows(InvalidNoteDataException.class, () -> serviceUnderTest.parseUrlData(data));
    }

}
