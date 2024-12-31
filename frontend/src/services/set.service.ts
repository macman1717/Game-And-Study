import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Set } from '../models/set.model';

@Injectable({
  providedIn: 'root'
})
export class SetService {
  private baseUrl = 'http://localhost:8080/sets';

  constructor(private http: HttpClient) {}

  getSets(): Observable<Set[]> {
    return this.http.get<Set[]>(`${this.baseUrl}/sets`);
  }
}
