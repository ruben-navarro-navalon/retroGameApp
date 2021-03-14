import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collection, SellerOrBuyer } from '../models/interfaces';
import { Platform } from '../models/platform';
import { HttpInterceptorService } from './http-interceptor.service';
import { UserLoginService } from './user-login.service';

@Injectable({
  providedIn: 'root'
})
export class UserCatalogService {

  readonly baseUrl: string = 'http://localhost:8080/';

  constructor(
    private http: HttpClient
  ) { }

  getPlatforms(): Observable<Platform[]> {
    const url: string = this.baseUrl + 'platforms';
    return this.http.get<Platform[]>(url);
  }

  getPlatformByShortName(shortName: string): Observable<Platform> {
    const url: string = this.baseUrl + 'platforms/' + shortName;
    return this.http.get<Platform>(url);
  }

  getCollection(id: number): Observable<Collection> {
    const url: string = this.baseUrl + 'collection/' + id;
    return this.http.get<Collection>(url);
  }

  addToCollection(apiId: number): Observable<Collection> {
    const url: string = this.baseUrl + 'collection/add';
    return this.http.put<Collection>(url, apiId);
  }

  removeFromCollection(apiId: number): Observable<Collection> {
    const url: string = this.baseUrl + 'collection/remove';
    return this.http.put<Collection>(url, apiId);
  }

  getWanted(id: number): Observable<Collection> {
    const url: string = this.baseUrl + 'wanted/' + id;
    return this.http.get<Collection>(url);
  }

  addToWanted(apiId: number): Observable<Collection> {
    const url: string = this.baseUrl + 'wanted/add';
    return this.http.put<Collection>(url, apiId);
  }

  removeFromWanted(apiId: number): Observable<Collection> {
    const url: string = this.baseUrl + 'wanted/remove';
    return this.http.put<Collection>(url, apiId);
  }

  moveFromWantedToCollection(apiId: number): Observable<Collection> {
    const url: string = this.baseUrl + 'wanted/move';
    return this.http.put<Collection>(url, apiId);
  }

  getSell(id: number): Observable<Collection> {
    const url: string = this.baseUrl + 'sell/' + id;
    return this.http.get<Collection>(url);
  }

  addToSell(apiId: number): Observable<Collection> {
    const url: string = this.baseUrl + 'sell/add';
    return this.http.put<Collection>(url, apiId);
  }

  removeFromSell(apiId: number): Observable<Collection> {
    const url: string = this.baseUrl + 'sell/remove';
    return this.http.put<Collection>(url, apiId);
  }


  getSellers(apiId: number): Observable<SellerOrBuyer[]> {
    const url: string = this.baseUrl + 'people/sells/' + apiId;
    return this.http.get<SellerOrBuyer[]>(url);
  }
  getBuyers(apiId: number): Observable<SellerOrBuyer[]> {
    const url: string = this.baseUrl + 'people/wants/' + apiId;
    return this.http.get<SellerOrBuyer[]>(url);
  }
}

