import { PatientService } from './../../service/patient.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Patient } from 'src/app/model/patient';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  patient$!: Observable<Patient>;

  constructor(private route: ActivatedRoute, private patientService: PatientService) { }

  ngOnInit(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')?.toString()!);
    this.patient$ = this.patientService.getPatient(id);
    this.patient$.subscribe;
  }

}
