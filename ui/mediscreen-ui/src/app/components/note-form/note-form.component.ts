import { NoteService } from './../../service/note.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { tap } from 'rxjs';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Note } from 'src/app/model/note';

@Component({
  selector: 'app-note-form',
  templateUrl: './note-form.component.html',
  styleUrls: ['./note-form.component.css']
})
export class NoteFormComponent implements OnInit {

  noteForm!: FormGroup;
  isAddMode?: boolean;
  formVisible: boolean = false;


  @Input("note") note?: Note;
  @Input("patientId") patientId?: number;

  @Output() actionOK = new EventEmitter();


  constructor(private formBuilder: FormBuilder, private noteService: NoteService) { }

  ngOnInit(): void {
    this.isAddMode = !this.note;
    this.formVisible = this.isAddMode;
    this.noteForm = this.formBuilder.group({
      noteContent : [null]
    });
    if (!this.isAddMode) {
      this.noteForm.setValue({noteContent : this.note?.content});
    }
  }

  onSubmit() {
    if (this.isAddMode) {
      const newNote: Note = new Note();
      newNote.patientId = this.patientId;
      newNote.content = this.noteForm.get("noteContent")?.value;
      console.log("add " + newNote.content);

      this.noteService.addNote(newNote).pipe(
        tap(() => {
          this.noteForm.setValue({noteContent : ''});
          this.actionOK.emit();
        })
      ).subscribe();
    }
    else {
      this.note!.content = this.noteForm.get("noteContent")?.value;
      console.log(this.note);
      this.noteService.updateNote(this.note!).pipe(
        tap(() => {
          this.actionOK.emit();
        })
      ).subscribe();
    }
  }

  editMode(activated: boolean) {
    this.formVisible = activated;
  }

  deleteNote() {
    console.log("dd");
    console.log('delete note :' + this.note!.id);
    this.noteService.deleteNote(this.note!).pipe(
      tap(() => {
        this.actionOK.emit();
      })
    ).subscribe();

  }


}
