import { Component,EventEmitter, OnInit, Output } from '@angular/core';
import { Teams } from '../datamodel/Teams';
import { UserService } from '../service/user.service';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { UserGiftPreference } from '../datamodel/UserGiftPreference';
import { User } from '../datamodel/User';
import { UserComponent } from '../user/user.component';
import { DataService } from '../data.service';
import { UserAuth } from '../datamodel/UserAuth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {


  private _userPerf: UserGiftPreference | undefined;

  private _teams: Teams[] = [];

  private _userAuth: UserAuth | undefined;

  private _signupCompleted:boolean=false;

  @Output() signupComplete:EventEmitter<any> = new EventEmitter();

  profileForm = this.fb.group({

    userId: ['', Validators.required],
    name: ['', Validators.required],
    teams:  new FormControl([]),
    teams2:['', Validators.required],
    emailId: ['', Validators.required],
    mobileNumber: ['', Validators.required],
    preference: [''],
    deliveryAddress: ['', Validators.required],
    postalCode: ['', Validators.required]

  });

  constructor(private service: UserService, private fb: FormBuilder,private dataService:DataService,private router: Router) {
    this.getTeamList();
  }

  getTeamList() {
    this.service.getTeams().subscribe({
      next: data => this._teams = data,
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }

  ngOnInit(): void {
  }

  get teams() {
    return this._teams;
  }

  get userPerf() {
    return this._userPerf;
  }

  onSubmit() {
    if (this.profileForm.invalid) {
      return;
    }
    const formArray: FormArray = this.profileForm.get('teams') as FormArray;
    let pref = new UserGiftPreference(
      "",
      new User(
        this.profileForm.get("userId")?.value,
        this.profileForm.get("name")?.value,
        formArray.value,
        this.profileForm.get("emailId")?.value,
        this.profileForm.get("mobileNumber")?.value,
      ),
      [this.profileForm.get("preference")?.value],
      this.profileForm.get("deliveryAddress")?.value,
      this.profileForm.get("postalCode")?.value,
    );
    console.log(pref);
    this.service.saveUser(pref).subscribe({
      next: data => {
        this.signupComplete.emit("complete");
        this.profileForm.reset();
        this._userAuth=data;
        this.dataService.updateUser();
        window.location.hash = '';
        window.location.hash = "signup";
        //this.router.navigate([],{fragment:'signup'});
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });;
  }

  get signupCompleted(){
    return this._signupCompleted;
  }

  get userAuth(){
    return this._userAuth;
  }


  onChecked(event:any){
    // console.log(question);
     let formArray: FormArray = this.profileForm.get('teams') as FormArray;
    if(!formArray.value){
      let control =new FormControl([])
      this.profileForm.setControl('teams',control);
      formArray = this.profileForm.get('teams') as FormArray;
    }
     /* Selected */
     if(event.target.checked){  
       formArray?.value.push(event.target.value);
     }
     else{
       let i: number = 0;
       formArray?.value.forEach((ctrl:any) => {
         if(ctrl == event.target.value) {
           formArray.value.splice(i,1);
           return;
         }
   
         i++;
       });
     }
     console.log(formArray.value);
   }

}
