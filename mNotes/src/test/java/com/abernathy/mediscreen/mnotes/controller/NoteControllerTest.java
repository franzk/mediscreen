package com.abernathy.mediscreen.mnotes.controller;

import com.abernathy.mediscreen.mnotes.exception.NoteNotFoundException;
import com.abernathy.mediscreen.mnotes.model.Note;
import com.abernathy.mediscreen.mnotes.service.NoteService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NoteControllerTest {

    @InjectMocks
    private NoteController controllerUnderTest;

    @Mock
    private NoteService noteService;

    @Test
    void addTest() {
        // Arrange
        Note note = new Note();
        note.setContent(RandomString.make(64));
        // Act
        ResponseEntity<Note> result = controllerUnderTest.add(note);
        // Assert
        verify(noteService, times(1)).add(note);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void getByPatientIdTest() {
        // Arrange
        int patientId = new Random().nextInt();
        // Act
        ResponseEntity<List<Note>> result = controllerUnderTest.getByPatientId(patientId);
        // Assert
        verify(noteService, times(1)).getByPatientId(patientId);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateTest() throws NoteNotFoundException {
        // Arrange
        Note note = new Note();
        note.setContent(RandomString.make(64));
        // Act
        ResponseEntity<Note> result = controllerUnderTest.update(note);
        // Assert
        verify(noteService, times(1)).update(note);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteByIdTest() {
        // Arrange
        String patientId = RandomString.make(64);
        // Act
        ResponseEntity<String> result = controllerUnderTest.deleteById(patientId);
        // Assert
        verify(noteService, times(1)).deleteById(patientId);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


}
