package com.abernathy.mediscreen.mnotes.controller;

import com.abernathy.mediscreen.mnotes.exception.InvalidNoteDataException;
import com.abernathy.mediscreen.mnotes.model.Note;
import com.abernathy.mediscreen.mnotes.service.ImportNoteService;
import com.abernathy.mediscreen.mnotes.service.NoteService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportControllerTest {

    @InjectMocks
    private ImportController controllerUnderTest;

    @Mock
    private NoteService noteService;

    @Mock
    private ImportNoteService importNoteService;

    @Mock
    private MultiValueMap<String,String> paramMap;

    @Test
    void importNoteTest() throws InvalidNoteDataException {
        // Arrange
        String tmpData = RandomString.make(64);
        when(paramMap.get(any())).thenReturn(List.of(tmpData));
        // Act
        ResponseEntity<Note> result = controllerUnderTest.importNote(paramMap);
        // Assert
        verify(importNoteService, times(1)).parseUrlData(tmpData);
        verify(noteService, times(1)).add(any());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
