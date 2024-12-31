import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/auth';
  public platformId!: number;

  constructor(private http: HttpClient, @Inject(PLATFORM_ID) platformId: number) {
    console.log(this.platformId);
    this.platformId = platformId;
    console.log(this.platformId);
  }


  generateToken(authRequest: any): Observable<string> {
    return this.http.post<string>(`${this.url}/generateToken`, authRequest, { responseType: 'text' as 'json' });
  }

  getAuthToken(): string {
    console.log("Hi");
    console.log(this.platformId);
    console.log(isPlatformBrowser(this.platformId));
    if (isPlatformBrowser(this.platformId)) {
      const authToken = localStorage.getItem('authToken');
      if (!authToken) {
        throw new Error('Authentication token is missing.');
      }
      return authToken;
    } else {
      return '';
    }
  }
}