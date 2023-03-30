import { RiskLevel } from './../model/riskLevel';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssessmentService {

  constructor(private http: HttpClient) {}

  serverUrl : string = "http://localhost:8080/assess/";

  public getRiskLevel(patientId: number) {
    return this.http.get<RiskLevel>(this.serverUrl + patientId).pipe(
      map((r) => {
        switch(r.value) {
          case 1 : return  { value : r.value, message : r.message, color : "secondary"}
          case 2 : return  { value : r.value, message : r.message, color : "warning"}
          case 3 : return  { value : r.value, message : r.message, color : "danger"}
        }
        return  { message : ""};
      })
    );
  }

}
