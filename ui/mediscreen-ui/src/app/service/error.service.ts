import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor() { }

  public getMessageError(error: any) : string {


    if (error.status == 0) {
      return "Connection with server failed";
    }

    if (error.error.message) {
          return error.error.message;
    }
    else if (error.error) {
      return error.error;
    }
    else if (error.message) {
      return error.message;
    }
    else {
      return "Sorry, an error occured !";
    }
  }

}
