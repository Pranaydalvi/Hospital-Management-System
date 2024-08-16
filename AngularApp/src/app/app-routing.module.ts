import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { LoginComponent } from './login/login.component';
import { PatientComponent } from './patient/patient.component';
import { AdminComponent } from './admin/admin.component';
import { DoctorComponent } from './doctor/doctor.component';
import { NurseComponent } from './nurse/nurse.component';
import { AppointmentComponent } from './appointment/appointment.component';

const routes: Routes = [
  {path:"",component:HomeComponent},
  {path:"reg",component:RegisterComponent},
  {path:"log",component:LoginComponent},
  {path:"admin",component:AdminComponent},
  {path:"doctor",component:DoctorComponent},
  {path:"patient",component:PatientComponent},
  {path:"bookappointment",component:AppointmentComponent},
  {path:"nurse",component:NurseComponent},
  {path:"**",component:PagenotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {  }
