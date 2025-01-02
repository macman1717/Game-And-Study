import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Set } from '../models/set.model';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class SetService {
  private baseUrl = 'http://localhost:8080/sets';
  public platformId!: number;

  constructor(private http: HttpClient, @Inject(PLATFORM_ID) platformId: number) {
    this.platformId = platformId;
  }

  getSets(): Observable<Set[]> {
    if (isPlatformBrowser(this.platformId)) {
      return this.http.get<Set[]>(`${this.baseUrl}/sets`);
    } else {
      return of([]);
    }
  }

  getSetDetails(id: string): Observable<Set> {
    if (isPlatformBrowser(this.platformId)) {
      return this.http.get<Set>(`${this.baseUrl}/sets/${id}`);
    } else {
      return of();
    }
  }

  deleteSet(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete-set/${id}`);
  }
}
