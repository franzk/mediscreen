import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  public getPatients() {
    return this.http.get<Patient[]>("http://localhost:8081/patient/");
  }

  public getPatient(id: number) {
    return this.http.get<Patient>("http://localhost:8081/patient/" + id);
  }

  public updatePatient(id: number, patient: Patient) {
    patient.id = id;
    return this.http.put<Patient>("http://localhost:8081/patient/update", patient);
  }

  public addPatient(patient: Patient) {
    return this.http.post<Patient>("http://localhost:8081/patient/insert", patient);
  }


}
