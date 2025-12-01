import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Application {
  id?: number;
  userId: number;
  advertId: number;
}

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  private baseUrl = 'http://localhost:4040/applications';
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) {}

  getAll(): Observable<Application[]> {
    return this.http.get<Application[]>(this.baseUrl);
  }
  getById(id: number): Observable<Application> {
    return this.http.get<Application>(`${this.baseUrl}/${id}`);
  }
  create(application: Application): Observable<Application> {
    return this.http.post<Application>(this.baseUrl, application, this.httpOptions);
  }
  update(id: number, application: Application): Observable<Application> {
    return this.http.put<Application>(`${this.baseUrl}/${id}`, application, this.httpOptions);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.httpOptions);
  }
}
