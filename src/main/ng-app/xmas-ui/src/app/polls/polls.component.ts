import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { Question } from '../datamodel/Question';
import { QuestionAnswer } from '../datamodel/QuestionAnswer';
import { Teams } from '../datamodel/Teams';
import { User } from '../datamodel/User';
import { PollsService } from '../service/polls.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-polls',
  templateUrl: './polls.component.html',
  styleUrls: ['./polls.component.css']
})
export class PollsComponent implements OnInit {

  private _teams: Teams[] = [];

  private _questions = new Map();

  private _users = new Map();

  private _isValid:boolean=false;

  
  form: FormGroup;
  form2: FormGroup;

  constructor(private service: PollsService, private userService: UserService, private dataService: DataService,private formBuilder: FormBuilder) {
    this.dataService.currentMessage.subscribe(data => this.getTeams());
    this.form = this.formBuilder.group({
      formAnswer:  new FormControl([])
    });
    this.form2 = this.formBuilder.group({
      formAnswer:  new FormControl([])
    });
  }

  ngOnInit(): void {
    this.getTeams();
  }

  getUserList(team: Teams) {

    this.userService.getUsersByTeams(team).subscribe({
      next: data => this._users.set(team, data),
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }

  getTeams() {

    this.userService.getTeams().subscribe({
      next: data => {
        this._teams = data;
        data.forEach(team => {
          this.getUserList(team);
          this.fetchQuestions(team);
        })
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }

  fetchQuestions(team: Teams) {
    return this.service.getQuestions(team).subscribe({
      next: data => this._questions.set(team, data),
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }



  get Teams() {
    return this._teams;
  }

  get Question() {
    return this._questions;
  }

  get Users() {
    return this._users;
  }

  


  onSubmit(question:Question,team:Teams) {
    let user:Array<User>=[]; 
    let formArray: FormArray = this.form.get('formAnswer') as FormArray;
    formArray?.value.forEach((ctrl:any) => {
      user.push(new User(ctrl,"",undefined,"",""));
    });
     let answer=new QuestionAnswer(undefined,new Question(question.questionId,question.questionText,question.description,question.teams),user,team);
     this.vote(answer);
     formArray.value.length=0;
   
  }


  clearFormArray = () => {
    let formArray: FormArray = this.form.get('formAnswer') as FormArray;
    formArray.value.length=0;
  }

  vote(answer:QuestionAnswer){
    this.service.vote(answer).subscribe({
      next:data=>{
        this.getTeams();
      }
    });
  }

  onChecked(question:Question,event:any){
   console.log(this.form.value);
   // console.log(question);
    let formArray: FormArray = this.form.get('formAnswer') as FormArray;

    if(formArray.value==null || formArray.value==undefined ){
      formArray?.value ===  [];
    }

    /* Selected */
    if(event.target.checked){  
      formArray.value.push(event.target.value);
      this._isValid=true;
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
    this._isValid=formArray.value.length==0?false:true;
    console.log(formArray.value);
  }


  get isValid(){
   return  this._isValid;
  }


}
