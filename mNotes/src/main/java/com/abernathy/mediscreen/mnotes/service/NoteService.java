package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.exception.NoteNotFoundException;
import com.abernathy.mediscreen.mnotes.model.Note;
import com.abernathy.mediscreen.mnotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;


    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note add(Note note) {
        return noteRepository.insert(note);
    }

    public Note getByPatientId(String patientId) throws NoteNotFoundException {
        return noteRepository.findByPatientId(patientId).orElseThrow(NoteNotFoundException::new);
    }

    public Note update(Note note) throws NoteNotFoundException {
        if (noteRepository.findById(note.getId()).isEmpty()) {
            throw new NoteNotFoundException();
        }
        else {
            return noteRepository.save(note);
        }
    }

    public void deleteById(String id) {
        noteRepository.deleteById(id);
    }

}
