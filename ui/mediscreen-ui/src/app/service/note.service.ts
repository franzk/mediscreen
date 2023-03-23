import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Note } from '../model/note';


@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor(private http:HttpClient) { }

  serverUrl: string = "http://localhost:8082/";

  public getNotes(patientId: number) {
    let params = new HttpParams().set('patientId', patientId);
    console.log(params);
    return this.http.get<Note[]>(this.serverUrl, { params : params });
  }

  public addNote(note: Note) {
    return this.http.post(this.serverUrl, note);
  }

  public updateNote(note: Note) {
    return this.http.put(this.serverUrl, note);
  }



}
