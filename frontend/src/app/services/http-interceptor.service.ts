import { HttpEvent, HttpHandler, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLoginService } from './user-login.service';

@Injectable()
export class HttpInterceptorService {

  constructor(
    private userLoginService: UserLoginService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.userLoginService.isLogged() && req.url.indexOf('login') === -1 && req.url.indexOf('signup') === -1 && req.url.indexOf('geoapi') === -1) {
      const authReq = req.clone({
        headers: new HttpHeaders({
          'content-type': 'application/json',
          'Authorization': 'Bearer ' + this.userLoginService.getToken()
        })
      });
      return next.handle(authReq);
    } else {
      return next.handle(req);
    }
  }
}
