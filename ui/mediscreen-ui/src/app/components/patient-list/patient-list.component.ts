import { PatientService } from './../../service/patient.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from 'src/app/model/patient';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  patients$!: Observable<Patient[]>

  constructor(private patientService:PatientService) { }

  ngOnInit(): void {
    this.patients$ = this.patientService.getPatients();
    this.patients$.subscribe(console.log);
  }

}
