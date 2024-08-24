import { Component } from '@angular/core';
import { Regsiter } from '../model/register.model';
import { RegisterService } from '../service/register.service';
import { Login } from '../model/login.model';
import { ResponseDto } from '../model/responsedto.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
constructor(private rgService: RegisterService){

}

  name = "Application";
   login = new Login();
   responseDto = new ResponseDto();
   //regsiter = new Regsiter();

   addingData(){
     console.log(this.login);
     //this.login.userRequest= this.regsiter;
    // console.log(this.regsiter);
    this.rgService.registerData(this.login).subscribe((rs)=>{
       if(rs!=null){
           console.log(rs);
           this.responseDto.msg = rs;
           alert(JSON.stringify(this.responseDto.msg));
       }
    });
   }
}
