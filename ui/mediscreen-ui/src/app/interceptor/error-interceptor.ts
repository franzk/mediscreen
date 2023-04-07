import { Router } from '@angular/router';
import { HttpEvent, HttpHandler, HttpRequest, HttpErrorResponse, HttpInterceptor} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EMPTY, Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ErrorService } from '../service/error.service';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private router: Router, private errorService: ErrorService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {

        console.log('error interceptor url=' +  request.url + ' / status=' + error.status + ' / message=' + error.message + ' / error.message=' + error.error);

        let errorMessage = this.errorService.getMessageError(error);
        console.log("Error status = " + error.status + " / message = " + errorMessage);

        if (errorMessage == "Token Expired") {
          console.log("Token Expired");
          this.router.navigateByUrl('/login',  { state: { error:"Your session has expired. Please log in again" } });
          return EMPTY;
        }

        if ((error.status == 401) && (errorMessage == "Bad Credentials")) {
          return throwError(() => error); // Bad credentials error can't be handle here. See login.component.ts
        }

        if (request.url.includes('/auth/register')) {
          return throwError(() => error); // Registration errors See registration.component.ts
        }

        this.router.navigateByUrl('/error', { state: { statusCode: error.status, statusText : errorMessage }});

        /*if ([0, 401, 500].includes(error.status)) {
          this.router.navigateByUrl('/error/' + error.status);
          return EMPTY;
        }*/

        return throwError(() => error);

      })
    );
  }
}
