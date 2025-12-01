import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Advert {
  id?: number;
  title: string;
  description: string;
  advertType?: string;
  advertStatus?: string;
  userId?: number;
}

@Injectable({
  providedIn: 'root',
})
export class AdvertService {
  private baseUrl = 'http://localhost:4040/adverts';
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) {}

  getAll(): Observable<Advert[]> {
    return this.http.get<Advert[]>(this.baseUrl);
  }
  getById(id: number): Observable<Advert> {
    return this.http.get<Advert>(`${this.baseUrl}/${id}`);
  }
  create(advert: Advert): Observable<Advert> {
    return this.http.post<Advert>(this.baseUrl, advert, this.httpOptions);
  }
  update(id: number, advert: Advert): Observable<Advert> {
    return this.http.put<Advert>(`${this.baseUrl}/${id}`, advert, this.httpOptions);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.httpOptions);
  }
}
