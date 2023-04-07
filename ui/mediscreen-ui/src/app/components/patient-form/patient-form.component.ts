import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of, tap, throwError } from 'rxjs';
import { Patient } from 'src/app/model/patient';
import { PatientService } from 'src/app/service/patient.service';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit {

  patient$!: Observable<Patient>;
  patientForm!: FormGroup;
  id!: number;
  notFoundError?: boolean;
  validationError?: string;
  isAddMode?: boolean;

  constructor(private formBuilder: FormBuilder, private patientService: PatientService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.patientForm = this.formBuilder.group({
      lastName: [null, Validators.required],
      firstName: [null, Validators.required],
      birthdate: [null, Validators.required],
      sex:  [null, Validators.required],
      address: [null],
      phone: [null]
    });

    this.id = this.route.snapshot.params['id'];

    this.isAddMode = !this.id;


    if (!this.isAddMode) {   // in Edit Mode, let's get the user to delete
      this.patient$ = this.patientService.getPatient(this.id);
      this.patient$.pipe(
        tap(p => this.patientForm.patchValue(p)),
        catchError(err => {
          console.log(err);
          if (err.status === 400) {
            this.notFoundError = true;
            return of({});
          }
          else {
            return throwError(() => err)
          }
        })
        ).subscribe();
      }
  }

  onSubmit() {
    if (this.isAddMode) {
      this.patientService.addPatient(this.patientForm.value).pipe(
        tap(() => {
          this.router.navigate(['']);
        }),
        catchError(err => {
          if (err.status === 400) {
            this.validationError = err.error;
            return of({});
          }
          else {
            return throwError(() => err)
          }
        })
      ).subscribe();
    }
    else {
      this.patientService.updatePatient(this.id, this.patientForm.value).pipe(
        tap(() => {
          this.router.navigate(['patient', this.id]);
        }),
        catchError(err => {
          if (err.status === 400) {
            this.validationError = err.error;
            return of({});
          }
          else {
            return throwError(() => err)
          }
        })
      ).subscribe();
    }
  }

}
