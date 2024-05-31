import { Injectable } from '@angular/core';
import { Korisnik } from '../models/korisnik';
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
}
