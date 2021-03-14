import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StatesFromApi, TownsFromApi } from '../models/interfaces';

@Injectable({
  providedIn: 'root'
})
export class GeoApiService {

  readonly baseUrl: string = "https://apiv1.geoapi.es/";
  type: string = 'JSON';

  // GEOAPI KEY HERE. TAKE A LOOK AT THE README
  key: string = '';


  sandbox: string = '0';

  constructor(
    private http: HttpClient,
  ) { }

  getStates(): Observable<StatesFromApi> {
    const url: string = this.baseUrl + 'provincias?type=' + this.type + '&key=' + this.key + '&sandbox=' + this.sandbox;
    return this.http.get<StatesFromApi>(url);
  }
  getTowns(CPRO: string): Observable<TownsFromApi> {
    const url: string = this.baseUrl + 'municipios?type' + this.type + '&key=' + this.key + '&sandbox=' + this.sandbox + '&CPRO=' + CPRO;
    return this.http.get<TownsFromApi>(url);
  }
}

