import { AuthenticationService } from './../service/authentication.service';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log('http interceptor ' + req.url + ' - ' + req.method);

    let authHeaderValue = 'Bearer ' + this.authenticationService.getToken();
    console.log(authHeaderValue);

      const modifiedReq = req.clone({
        headers: req.headers.set('Authorization', authHeaderValue)
      });
      return next.handle(modifiedReq);

 }

}
