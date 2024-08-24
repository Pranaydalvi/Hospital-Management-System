import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../model/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = "http://localhost:8983/api/user";
  constructor(private http : HttpClient) { }

  login(login :Login){
    return this.http.post(this.url+"/login",login);
  }
}