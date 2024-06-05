import { Korisnik } from './../models/korisnik';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { KORISNIK_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class KorisnikService {
  constructor(private httpClient: HttpClient) {}

  public getAllKorisnik(): Observable<any> {
    return this.httpClient.get(`${KORISNIK_URL}`);
  }

  public createKorisnik(korisnik:Korisnik):Observable<any> {
    return this.httpClient.post(`${KORISNIK_URL}`, korisnik);
  }

  public updateKorisnik(korisnik:Korisnik):Observable<any>{
    return this.httpClient.put(`${KORISNIK_URL}/id/${korisnik.id}`, korisnik);
  }

  public deleteKorisnik(korisnikID:number):Observable<any> {
    return this.httpClient.delete(`${KORISNIK_URL}/id/${korisnikID}`, {responseType:"text"});
  }
}

