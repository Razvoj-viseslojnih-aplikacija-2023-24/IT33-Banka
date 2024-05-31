import { Injectable } from '@angular/core';
import { USLUGA_URL } from '../constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UslugaService {
  constructor(private httpClient: HttpClient) {}

  public getAllUsluge(): Observable<any> {
    return this.httpClient.get(`${USLUGA_URL}`);
  }
}
