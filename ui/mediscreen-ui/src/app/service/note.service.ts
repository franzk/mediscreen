import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Note } from '../model/note';


@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private http:HttpClient) { }

  serverUrl: string = "http://localhost:8080/notes/";

  public getNotes(patientId: number) {
    return this.http.get<Note[]>(this.serverUrl + patientId);
  }

  public addNote(note: Note) {
    return this.http.post(this.serverUrl, note);
  }

  public updateNote(note: Note) {
    return this.http.put(this.serverUrl, note);
  }

  public deleteNote(note: Note) {
    return this.http.delete(this.serverUrl + note.id, {responseType: 'text'});
  }



}
