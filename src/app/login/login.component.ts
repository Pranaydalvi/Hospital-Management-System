import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Login } from '../model/login.model';
import { Router } from '@angular/router';
import { UserResponse } from '../model/userresponce.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private loginService : LoginService,private router :Router){
  
  }
   login = new Login();
   userResponse = new UserResponse();
   admin : string ='Admin';
   patient : string ='Patient';
   doctor : string ='Doctor';
   nurse : string ='Nurse';

  generatedCode: string = '';
  userEnteredCode: string = '';

  ngOnInit() {
    this.generateCaptchaCode();
  }

  generateCaptchaCode() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    this.generatedCode = '';
    for (let i = 0; i < 6; i++) {
      this.generatedCode += characters.charAt(Math.floor(Math.random() * characters.length));
    }
  }

  userLogin() {
    if (this.userEnteredCode === this.generatedCode) {
       this.loginService.login(this.login).subscribe((rs:any)=>{
          console.log(rs);
          this.userResponse = rs;
          if(this.userResponse.errorMsg == null){
            sessionStorage.setItem('usernumber', this.userResponse.usernumber);
            sessionStorage.setItem('token',this.userResponse.token);
            if(this.userResponse.rolename === this.admin){
                this.router.navigate(['/admin']);
            } else if(this.userResponse.rolename === this.patient){
              this.router.navigate(['/patient']);
            }else if(this.userResponse.rolename === this.doctor){
              this.router.navigate(['/doctor']);
          }else if(this.userResponse.rolename === this.nurse){
            this.router.navigate(['/nurse']);
          }else{
            this.router.navigate(['/log']);
          }
        }
       });
      } else {
        alert('CAPTCHA code does not match. Please try again.');
        this.generateCaptchaCode();
      }
    }
  }