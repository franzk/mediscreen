import { Credentials } from './../model/credentials';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { Injectable } from '@angular/core';
import { AuthToken } from '../model/authToken';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  isConnected$! : Observable<boolean>;

  serverUrl: string = "http://localhost:8080/auth/";

  constructor(private httpClient: HttpClient) { }


  public login(_username: string, _password: string) {
    let credentials = { username: _username, password: _password};
    return this.httpClient.post<AuthToken>(this.serverUrl + "token", credentials).pipe(
      tap((token) => {
        console.log("ok" + token.authToken);
        if (token.authToken) {
          this.setToken(token.authToken);
          this.isConnected$ = of(true);
        }
        else {
          this.isConnected$ = of(false);
          console.log("LOGIN ERROR !!");
        }
      })
    );
  }

  public logout() {
    localStorage.removeItem('authToken');
    this.isConnected$ = of(false);
  }

  public getToken() {
    let token = localStorage.getItem('authToken');
    console.log('get token : ' + token);
    if (token) {
      this.isConnected$ = of(true);
    }
    else {
      this.isConnected$ = of(false);
    }
    return token;
  }

  public setToken(authToken : string) {
    localStorage.setItem('authToken', authToken);
  }

  public register(_username: string, _password: string) {
    let credentials = { username: _username, password: _password};
    return this.httpClient.post<string>(this.serverUrl + "register", credentials);
  }

}
