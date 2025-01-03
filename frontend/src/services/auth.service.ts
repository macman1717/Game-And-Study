import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/auth';
  constructor(private http: HttpClient) {}

  generateToken(authRequest: any): Observable<string> {
    return this.http.post<string>(`${this.url}/generateToken`, authRequest, { responseType: 'text' as 'json' });
  }
}