import { AuthenticationService } from './../../service/authentication.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { EMPTY, catchError, tap } from 'rxjs';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ErrorService } from 'src/app/service/error.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  error?: string;
  registerForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,
                private router: Router,
                private authenticationService: AuthenticationService,
                private errorService: ErrorService) { }

  ngOnInit(): void {
    this.error = '';
    let emailRegExp = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    this.registerForm = this.formBuilder.group({
      username: [null, [Validators.required, Validators.pattern(emailRegExp) ]],
      password: [null, Validators.required]
    }, {
      updateOn: 'change'
    });
  }

  submit() {
    this.authenticationService.register(this.registerForm.value.username, this.registerForm.value.password!).pipe(
      tap(() => this.router.navigateByUrl('login')),
      catchError((error) => {
          this.error = this.errorService.getMessageError(error);
          return EMPTY;
      })
    ).subscribe();
  }

}
