import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private baseUrl = 'http://localhost:8983/api/doctor';
  private baseUrlall = 'http://localhost:8983/api/all';

  constructor(private http: HttpClient) {}

  private getAuthHeaders(): HttpHeaders {
    const token = sessionStorage.getItem('token');
    console.log(token)
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
  
  getCategories(): Observable<string[]> {
    console.log(this.getAuthHeaders)
    return this.http.get<string[]>(`${this.baseUrl}/categories`, { headers: this.getAuthHeaders() });
  }
  
  getDoctorsByCategory(category: string): Observable<any[]> {
    console.log(this.getAuthHeaders)
    return this.http.get<any[]>(`${this.baseUrl}/${category}`, { headers: this.getAuthHeaders() });
  }
  
  saveAppointment(appointment: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrlall}/BookAppointment/${sessionStorage.getItem('usernumber')}`, appointment, { headers: this.getAuthHeaders() });
  }
}
