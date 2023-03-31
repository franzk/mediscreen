import { AuthenticationService } from './../../service/authentication.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  username?: string;
  password?: string;
  error?: string;

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }

  submit() {
    this.authenticationService.register(this.username!, this.password!).pipe(
      tap(() => this.router.navigateByUrl('login'))
    ).subscribe();
  }

}
