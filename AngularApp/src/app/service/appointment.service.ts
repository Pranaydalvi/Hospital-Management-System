import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private baseUrl = 'http://localhost:8983/api/doctor';
  private baseUrlall = 'http://localhost:8983/api/all';

  constructor(private http: HttpClient) {}

  // Get all categories
  getCategories(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/categories`);
  }

  // Get doctors by category
  getDoctorsByCategory(category: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${category}`);
  }
  saveAppointment(appointment: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrlall}/BookAppointment/`+appointment.usernumber, appointment);
  }
}
