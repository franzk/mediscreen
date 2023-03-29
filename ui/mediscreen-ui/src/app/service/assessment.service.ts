import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  constructor(private http: HttpClient) {}

  serverUrl : string = "http://localhost:8083/assess/";

  public getRiskLevel(patientId: number) {
    return this.http.get<string>(this.serverUrl + patientId);
  }

}
