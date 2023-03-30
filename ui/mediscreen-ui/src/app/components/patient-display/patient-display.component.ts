import { RiskLevel } from './../../model/riskLevel';
import { AssessmentService } from './../../service/assessment.service';
import { NoteService } from './../../service/note.service';
import { PatientService } from '../../service/patient.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Patient } from 'src/app/model/patient';
import { map, Observable } from 'rxjs';
import { Note } from 'src/app/model/note';

@Component({
  selector: 'app-display-patient',
  templateUrl: './patient-display.component.html',
  styleUrls: ['./patient-display.component.css']
})
export class PatientDisplayComponent implements OnInit {

  patient$!: Observable<Patient>;
  notes$!: Observable<Note[]>;
  riskLevel$!: Observable<RiskLevel>;
  id?: number;

  constructor(private route: ActivatedRoute,
              private patientService: PatientService,
              private noteService: NoteService,
              private assessmentService: AssessmentService) { }

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('id')?.toString()!);
    this.patient$ = this.patientService.getPatient(this.id);
    this.notes$ = this.noteService.getNotes(this.id);
    this.riskLevel$ = this.assessmentService.getRiskLevel(this.id);
  }

  refreshNotes() {
    this.notes$ = this.noteService.getNotes(this.id!);
    this.riskLevel$ = this.assessmentService.getRiskLevel(this.id!);
  }

}
