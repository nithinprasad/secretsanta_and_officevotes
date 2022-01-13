import { Component, OnInit } from '@angular/core';
import { User } from '../datamodel/User';
import { UserGiftPreference } from '../datamodel/UserGiftPreference';
import { LoginService } from '../service/login.service';
import { PollsService } from '../service/polls.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private _username:string="";
  private _password:string="";

  private _isLoggedin:boolean=false;

  private _isSubmitted:boolean=false;

  private _userInfo:any;

  private _userGiftService:any;

  private _isChild:boolean=false;

  constructor(private service:LoginService,private pollsService:PollsService) { 
  }

  ngOnInit(): void {
    this.checkLoginStatus();
  }

  onSubmit() {
    var formData: any = new FormData();
    formData.append("username", this.username);
    formData.append("password", this.password);
   
    this.service.doLogin(formData).subscribe({
      next: data =>{  
        this.checkLoginStatus();
      },
      error: error => {
        this.checkLoginStatus();
      }
    });
    this._isSubmitted=true;
  }

  checkLoginStatus(){
    this.service.checkStatus().subscribe({
      next: data =>{
        this._isLoggedin=true;
        this._userInfo=data;
        this.fetchChild();
      },
      error: error => {
        this._isLoggedin=false;
      }
    });
  }

  get username(){
    return this._username;
  }

  get password(){
    return this._password;
  }

  set username(username:string){
     this._username=username;
  }

  set password(password:string){
    this._password=password;
  }

  get isLoggedin(){
    return this._isLoggedin;
  }

  set userInfo(userInfo:User){
    this._userInfo=userInfo;
  }

  get userInfo(){
    return this._userInfo;
  }

  get isSubmitted(){
    return this._isSubmitted;
  }

  fetchChild(){
    return this.pollsService.getChild().subscribe({
      next: data =>{
        console.log(data);
        this._userGiftService=data;
        this._isChild=true;
      },
      error: error => {
      }
    });;
  }

  get userGiftService(){
    return this._userGiftService;
  }

  get isChild(){
    return this._isChild;
  }

}
