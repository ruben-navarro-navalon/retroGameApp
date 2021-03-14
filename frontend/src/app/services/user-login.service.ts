import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  readonly baseUrl: string = "http://localhost:8080/";

  constructor(
    private http: HttpClient,
    private cookies: CookieService
  ) { }

  register(user: {username: string, password: string}): Observable<any> {
    const url: string = this.baseUrl + 'signup';
    return this.http.post(url, user, {responseType: 'text'});
  }

  login(user: {username: string, password: string}): Observable<any> {
    const url: string = this.baseUrl + 'login';
    return this.http.post(url, user);
  }

  setToken(token: string, username: string) {
    this.cookies.set("token", token);
    this.cookies.set("username", username);
    this.cookies.set("isLogged", 'true');
  }

  getToken(): string {
    return this.cookies.get("token");
  }
  getUsername(): string {
    return this.cookies.get("username");
  }
  isLogged(): boolean {
    return this.cookies.get("isLogged") === 'true';
  }

  deleteCookies(): void{
    this.cookies.deleteAll();
  }

  getUser(username: string): Observable<User> {
    const url: string = this.baseUrl + 'user/' + username;
    return this.http.get<User>(url);
  }
}
