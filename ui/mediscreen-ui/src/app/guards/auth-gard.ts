import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { AuthenticationService } from "../service/authentication.service";
import { tap } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthGard implements CanActivate {

  constructor(private authenticationService: AuthenticationService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let connected = true;

    this.authenticationService.checkConnection().pipe(
      tap((value) => {
        connected = value;
      })
    ).subscribe();


      if (connected) {
          return true;
      }
      else {
          this.router.navigateByUrl('login');
          return false;
      }
  }
}
