import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Patient } from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  public getPatients() {
    return this.http.get<Patient[]>("http://localhost:8081/patient/");
  }

}
