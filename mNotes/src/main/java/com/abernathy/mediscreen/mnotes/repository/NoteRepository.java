package com.abernathy.mediscreen.mnotes.repository;

import com.abernathy.mediscreen.mnotes.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    @Query(value="{ patient_id : ?0 }", sort = "{ creation_date :  -1}")
    List<Note> findByPatientId(Integer patientId);

}
