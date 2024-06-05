import { Banka } from './../models/banka';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BANKA_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class BankaService {

  constructor(private httpClient: HttpClient) {}

  public getAllBanks(): Observable<any> {
    return this.httpClient.get(`${BANKA_URL}`);
  }

  public createBanka(banka:Banka):Observable<any> {
    return this.httpClient.post(`${BANKA_URL}`, banka);
  }

  public updateBanka(banka:Banka):Observable<any>{
    return this.httpClient.put(`${BANKA_URL}/id/${banka.id}`, banka);
  }

  public deleteBanka(bankaID:number):Observable<any> {
    return this.httpClient.delete(`${BANKA_URL}/id/${bankaID}`, {responseType:"text"});
  }
}

