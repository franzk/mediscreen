package com.abernathy.mediscreen.mnotes.controller;

import com.abernathy.mediscreen.mnotes.exception.InvalidNoteDataException;
import com.abernathy.mediscreen.mnotes.model.Note;
import com.abernathy.mediscreen.mnotes.service.ImportNoteService;
import com.abernathy.mediscreen.mnotes.service.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patHistory")
@Log4j2
public class ImportController {

    private final NoteService noteService;
    private final ImportNoteService importNoteService;

    public ImportController(NoteService noteService, ImportNoteService importNoteService) {
        this.noteService = noteService;
        this.importNoteService = importNoteService;
    }

    /**
     * curl -d "patId=1¬e=Patient: TestNone Practitioner's notes/recommendations: Patient states that they are 'feeling terrific' Weight at or below recommended level" -X POST http://localhost:8082/patHistory/add
     * @param paramMap
     * @return
     */
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Note> importNote(@RequestParam MultiValueMap<String,String> paramMap) throws InvalidNoteDataException {

        if (paramMap.get("patId") == null) {
            throw new InvalidNoteDataException();
        }

        String data = paramMap.get("patId").get(0);
        log.info(data);

        Note newNote = importNoteService.parseUrlData(data);
        return new ResponseEntity<>(noteService.add(newNote), HttpStatus.OK);
    }


}
