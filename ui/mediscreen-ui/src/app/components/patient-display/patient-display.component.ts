import { PatientService } from '../../service/patient.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Patient } from 'src/app/model/patient';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-display-patient',
  templateUrl: './patient-display.component.html',
  styleUrls: ['./patient-display.component.css']
})
export class PatientDisplayComponent implements OnInit {

  patient$!: Observable<Patient>;

  constructor(private route: ActivatedRoute, private patientService: PatientService) { }

  ngOnInit(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')?.toString()!);
    this.patient$ = this.patientService.getPatient(id);
  }

}
