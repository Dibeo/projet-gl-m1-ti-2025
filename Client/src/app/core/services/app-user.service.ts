import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AppUser {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  password?: string;
  bio?: string;
  location?: string;
  userType?: string;
}

@Injectable({
  providedIn: 'root',
})
export class AppUserService {
  private baseUrl = 'http://localhost:4040/users';
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) {}

  getAll(): Observable<AppUser[]> {
    return this.http.get<AppUser[]>(this.baseUrl);
  }
  getById(id: number): Observable<AppUser> {
    return this.http.get<AppUser>(`${this.baseUrl}/${id}`);
  }
  create(user: AppUser): Observable<AppUser> {
    return this.http.post<AppUser>(this.baseUrl, user, this.httpOptions);
  }
  update(id: number, user: AppUser): Observable<AppUser> {
    return this.http.put<AppUser>(`${this.baseUrl}/${id}`, user, this.httpOptions);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.httpOptions);
  }
}
