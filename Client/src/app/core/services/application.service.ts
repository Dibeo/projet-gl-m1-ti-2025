import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Advert } from './advert.service';

export interface Application {
  id?: number;
  applicant: {
    id: number;
    firstName?: string;
    lastName?: string;
    email?: string;
  };
  advert: {
    id: number;
    title?: string;
    desc?: string;
    advertType?: string;
    advertStatus?: string;
  };
  applicationStatus?: string;
}

export interface ApplicationDTO {
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

  // Nouvelle version utilisant le DTO
  create(applicationDto: ApplicationDTO): Observable<Application> {
    return this.http.post<Application>(this.baseUrl, applicationDto, this.httpOptions);
  }

  update(id: number, application: Application): Observable<Application> {
    return this.http.put<Application>(`${this.baseUrl}/${id}`, application, this.httpOptions);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.httpOptions);
  }
}
