import { USLUGA_BY_FILIJALA_URL, USLUGA_URL } from './../constants';
import { Usluga } from './../models/usluga';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
//mport { USLUGA_URL, USLUGA_BY_FILIJALA_URL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class UslugaService {

  constructor(private httpClient: HttpClient) { }

  public getAllUsluge():Observable<any> {
    return this.httpClient.get(`${USLUGA_URL}`);
  }

  public getUslugaByFilijala(filijalaID:number):Observable<any> {
    return this.httpClient.get(`${USLUGA_BY_FILIJALA_URL}/${filijalaID}`);
  }

  public addUsluga(usluga:Usluga):Observable<any>{
    return this.httpClient.post(`${USLUGA_URL}`, usluga);
  }

  public updateUsluga(usluga:Usluga):Observable<any>{
    return this.httpClient.put(`${USLUGA_URL}/id/${usluga.id}`, usluga);
  }

  public deleteUsluga(uslugaID:number):Observable<any>{
    return this.httpClient.delete(`${USLUGA_URL}/id/${uslugaID}`, {responseType:"text"});
  }
}