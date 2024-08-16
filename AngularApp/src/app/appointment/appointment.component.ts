import { Component } from '@angular/core';
import { AppointmentService } from '../service/appointment.service';
import { Appointment } from '../model/appointment.model';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent {
  appointment=new Appointment();
  categories: string[] = [];
  doctors: any[] = [];

  bloodGroups: string[] = ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'];

  constructor(private doctorService: AppointmentService) {}

  ngOnInit(): void {
    // Fetch all categories on component initialization
    this.doctorService.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  // Fetch doctors when a category is selected
  onCategoryChange(category: string): void {
    this.doctorService.getDoctorsByCategory(category).subscribe(data => {
      this.doctors = data;
    });
  }
}
