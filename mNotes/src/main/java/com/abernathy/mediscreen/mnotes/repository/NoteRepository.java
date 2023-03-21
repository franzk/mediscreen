package com.abernathy.mediscreen.mnotes.repository;

import com.abernathy.mediscreen.mnotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    Optional<Note> findByPatientId(String patientId);

}
