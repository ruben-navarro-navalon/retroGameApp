import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SellerOrBuyer } from '../models/interfaces';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  readonly baseUrl: string = "http://localhost:8080/";

  constructor(
    private http: HttpClient
    ) { }

  sendRegisterEmail(address: string) {
    const url: string = this.baseUrl + 'email/send';

    const textMessage: string = "Hi! Welcome to RetroGameApp. Login and start using this awesome app just now.";
    const subject: string = "Welcome to RetroGameApp";

    return this.http.post(url, {textMessage: textMessage, address: address, subject: subject});
  }

  sendContactEmail(textMessage: string, address: string, subject: string) {
    const url: string = this.baseUrl + 'email/send';

    return this.http.post(url, {textMessage: textMessage, address: address, subject: subject});
  }
  
}
