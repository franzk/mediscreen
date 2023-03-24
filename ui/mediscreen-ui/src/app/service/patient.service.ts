import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  serverUrl : string = "http://localhost:8080/patient/";

  public getPatients() {
    return this.http.get<Patient[]>(this.serverUrl);
  }

  public getPatient(id: number) {
    return this.http.get<Patient>(this.serverUrl + id);
  }

  public updatePatient(id: number, patient: Patient) {
    patient.id = id;
    return this.http.put<Patient>(this.serverUrl + "update", patient);
  }

  public addPatient(patient: Patient) {
    return this.http.post<Patient>(this.serverUrl + "insert", patient);
  }


}
