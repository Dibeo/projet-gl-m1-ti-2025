import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Message {
  id?: number;
  content: string;
  senderId: number;
  receiverId: number;
}

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  private baseUrl = 'http://localhost:4040/messages';
  private httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) {}

  getAll(): Observable<Message[]> {
    return this.http.get<Message[]>(this.baseUrl);
  }
  getById(id: number): Observable<Message> {
    return this.http.get<Message>(`${this.baseUrl}/${id}`);
  }
  create(message: Message): Observable<Message> {
    return this.http.post<Message>(this.baseUrl, message, this.httpOptions);
  }
  update(id: number, message: Message): Observable<Message> {
    return this.http.put<Message>(`${this.baseUrl}/${id}`, message, this.httpOptions);
  }
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.httpOptions);
  }
}
