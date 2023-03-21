package com.abernathy.mediscreen.mnotes.controller;

import com.abernathy.mediscreen.mnotes.exception.NoteNotFoundException;
import com.abernathy.mediscreen.mnotes.model.Note;
import com.abernathy.mediscreen.mnotes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/")
    public ResponseEntity<Note> add(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.add(note), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Note> getByPatientId(@RequestParam String patientId) throws NoteNotFoundException {
        return new ResponseEntity<>(noteService.getByPatientId(patientId), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Note> update(@RequestBody Note note) throws NoteNotFoundException {
        return new ResponseEntity<>(noteService.update(note), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteById(@RequestParam String id) {
        noteService.deleteById(id);
        return new ResponseEntity<>("Note deleted", HttpStatus.OK);
    }

}
