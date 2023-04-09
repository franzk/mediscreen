package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.exception.NoteNotFoundException;
import com.abernathy.mediscreen.mnotes.model.Note;
import com.abernathy.mediscreen.mnotes.repository.NoteRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @InjectMocks
    private NoteService serviceUnderTest;

    @Mock
    private NoteRepository noteRepository;

    Random random = new Random();

    @Test
    void addTest() {
        // Arrange
        Note testNote = new Note();
        // Act
        serviceUnderTest.add(testNote);
        // Assert
        verify(noteRepository, times(1)).insert(testNote);
    }

    @Test
    void getByPatientIdTest() {
        // Arrange
        int testPatientId = random.nextInt();
        // Act
        serviceUnderTest.getByPatientId(testPatientId);
        // Assert
        verify(noteRepository, times(1)).findByPatientId(testPatientId);
    }

    @Test
    void updateTest() throws NoteNotFoundException {
        // Arrange
        String noteId = RandomString.make(64);
        Note testNote = new Note();
        testNote.setId(noteId);
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(testNote));

        // Act
        serviceUnderTest.update(testNote);

        // Assert
        verify(noteRepository, times(1)).save(any());

    }

    @Test
    void updateWithNoteNotFoundExceptionTest() {
        // Arrange
        String noteId = RandomString.make(64);
        Note testNote = new Note();
        testNote.setId(noteId);
        when(noteRepository.findById(noteId)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(NoteNotFoundException.class, () -> serviceUnderTest.update(testNote));
    }


    @Test
    void deleteByIdTest() {
        // Arrange
        String noteId = RandomString.make(64);

        // Act
        serviceUnderTest.deleteById(noteId);

        // Assert
        verify(noteRepository, times(1)).deleteById(noteId);



    }

}
