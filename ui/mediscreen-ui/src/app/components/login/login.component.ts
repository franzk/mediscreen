import { AuthenticationService } from './../../service/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username?: string;
  password?: string;
  error?: string;

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    console.log("login")
    this.authenticationService.login(this.username!, this.password!).pipe(
      tap(() => this.router.navigateByUrl(''))
    ).subscribe();
  }

}
