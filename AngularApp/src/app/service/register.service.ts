import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from '../model/login.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http : HttpClient) { }

  url = "http://localhost:8983/api/user";

  registerData(login:Login){
    console.log("Calling From RG component");
    console.log(login);
    return this.http.post(this.url+'/save',login)
  }
}
