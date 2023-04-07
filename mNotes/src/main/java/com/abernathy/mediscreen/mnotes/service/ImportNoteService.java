package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.exception.InvalidNoteDataException;
import com.abernathy.mediscreen.mnotes.model.Note;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j2
public class ImportNoteService {

    public Note parseUrlData(String data) throws InvalidNoteDataException {

        log.info(data);
        Pattern pattern = Pattern.compile("(.*?)¬e=(.*?)$", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(data);

        if ((matcher.find()) && (matcher.groupCount() == 2)) {
            int patientId = Integer.parseInt(matcher.group(1));
            log.info("id = " + patientId);
            String noteContent = matcher.group(2);
            log.info("noteContent = " + noteContent);
            Note newNote = new Note();
            newNote.setPatientId(patientId);
            newNote.setContent(noteContent);
            return newNote;
        }
        else {
            throw new InvalidNoteDataException();
        }
    }
}
