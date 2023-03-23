package com.abernathy.mediscreen.mnotes.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "notes")
@Getter
@Setter
public class Note {

    @Id
    private String id;

    @Field("patient_id")
    private Integer patientId;

    @Field("creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Field("last_update_date")
    private LocalDateTime lastUpdateDate = LocalDateTime.now();

    @Field("content")
    private String content;
}
