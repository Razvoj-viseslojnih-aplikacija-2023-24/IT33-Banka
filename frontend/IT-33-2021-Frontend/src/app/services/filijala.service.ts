import { Injectable } from '@angular/core';
import { FILIJALA_URL } from '../constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FilijalaService {
  constructor(private httpClient: HttpClient) {}

  public getAllFilijale(): Observable<any> {
    return this.httpClient.get(`${FILIJALA_URL}`);
  }
}
