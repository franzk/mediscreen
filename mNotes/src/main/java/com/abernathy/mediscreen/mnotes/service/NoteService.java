package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.exception.NoteNotFoundException;
import com.abernathy.mediscreen.mnotes.model.Note;
import com.abernathy.mediscreen.mnotes.repository.NoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note add(Note note) {
        return noteRepository.insert(note);
    }

    public List<Note> getByPatientId(Integer patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    public Note update(Note note) throws NoteNotFoundException {

        String id = note.getId();
        Optional<Note> noteToUpdate = noteRepository.findById(id);

        if (noteToUpdate.isPresent()) {
            note.setLastUpdateDate(LocalDateTime.now());
            return noteRepository.save(note);
        }
        else {
            throw new NoteNotFoundException();
        }

    }

    public void deleteById(String id) {
        noteRepository.deleteById(id);
    }

}
