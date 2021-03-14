import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { UserLoginService } from 'src/app/services/user-login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private userLoginService: UserLoginService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const url: string = state.url;
      return this.checkLogin(url);
  }
  
  checkLogin(url: string) : true | UrlTree {
    if (!this.userLoginService.isLogged()){
      if (url.indexOf('login') === -1) {
        return this.router.parseUrl('login');
      } else {
        return true;
      }
    } else {
      if (url.indexOf('login') !== -1) {
        return this.router.parseUrl('');
      } else {
        return true;
      }
    }
  }

}
