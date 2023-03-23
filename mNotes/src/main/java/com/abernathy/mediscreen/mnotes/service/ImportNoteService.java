package com.abernathy.mediscreen.mnotes.service;

import com.abernathy.mediscreen.mnotes.exception.BadNoteImportRequestException;
import com.abernathy.mediscreen.mnotes.model.Note;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImportNoteService {

    public Note parseUrlData(String data) throws BadNoteImportRequestException {

        Pattern pattern = Pattern.compile("(.*?)¬e=Patient: (.*?) Practitioner's notes\\/recommendations: (.*?)$");
        Matcher matcher = pattern.matcher(data);

        if ((matcher.find()) && (matcher.groupCount() == 3)) {
            int patientId = Integer.parseInt(matcher.group(1));
            String patientLastname = matcher.group(2);
            String noteContent = matcher.group(3);
            Note newNote = new Note();
            newNote.setPatientId(patientId);
            newNote.setContent(noteContent);
            return newNote;
        }
        else {
            throw new BadNoteImportRequestException();
        }
    }
}
