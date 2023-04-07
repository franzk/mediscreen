import { AuthenticationService } from './../../service/authentication.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EMPTY, catchError, tap, throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //username?: string;
  //password?: string;
  error?: string;
  loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    console.log(history.state);
    //if (history.state.error) {
      this.error = history.state.error;
    //}
    //else {
    //  this.error = "";
    //}

    let emailRegExp = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    this.loginForm = this.formBuilder.group({
      username: [null, [Validators.required, Validators.pattern(emailRegExp) ]],
      password: [null, Validators.required]
    }, {
      updateOn: 'change'
    });

  }

  login() {
    console.log("login")
    this.authenticationService.login(this.loginForm.value.username, this.loginForm.value.password).pipe(
      tap(() => this.router.navigateByUrl('')),
      catchError((error) => {
        console.log("Login error = " + error.status);
        if ((error.status == 401) && (error.error == "Bad Credentials")) {
          console.log("Bad crr");
          this.error = "Bad credentials ! Please try again.";
          return EMPTY;
        } else {
          return throwError(() => error);
        }
      })
    ).subscribe();
  }

}
