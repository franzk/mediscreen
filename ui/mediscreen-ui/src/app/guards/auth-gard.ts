import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { AuthenticationService } from "../service/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGard implements CanActivate {

  constructor(private authenticationService: AuthenticationService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

      console.log('canactivate');

      if (this.authenticationService.isConnected$) {
          return true;
      }
      else {
          this.router.navigateByUrl('login');
          return false;
      }
  }
}
