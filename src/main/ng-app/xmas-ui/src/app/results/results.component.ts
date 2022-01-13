import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { Question } from '../datamodel/Question';
import { QuestionAnswer } from '../datamodel/QuestionAnswer';
import { Teams } from '../datamodel/Teams';
import { User } from '../datamodel/User';
import { PollsService } from '../service/polls.service';
import { UserService } from '../service/user.service';
import { Results } from '../datamodel/Results';
import { map } from 'rxjs';



@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {

  private _teams: Teams[] = [];

  private _users = new Map();

  private _results = new Map();

  constructor(private service: PollsService, private userService: UserService, private dataService: DataService, private formBuilder: FormBuilder) {
    
  }
  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true,
    plugins: {
      legend: {
        position: 'right',
      },
      title: {
        display: true,
        text: 'Chart.js Horizontal Bar Chart'
      }
    }
  };
  public barChartLabels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'LMNR Awards 1' }

  ];

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
          this.fetchResults(team);

        })
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }
  fetchResults(team: Teams) {
    this.service.getResults(team).subscribe({
      next: data => {
        this._results.set(team, data)
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }

  get Teams() {
    return this._teams;
  }

  getResults(team: Teams) {
   
    return this._results.get(team);
  
  }
  get Users() {
    return this._users;
  }

  getBarChartData(res : Results) : any{
    let dataArray : Array<Number> = [];
    res.results.forEach(element => {
      dataArray.push(element.vote)
    });
     let barChartData = [
      { data: dataArray, label: "Vote"}
  
    ];
    return barChartData
  }
  getBarChartLabels(res : Results) : any{
    let lablesArray : Array<String> = [];
    res.results.forEach(element => {
      lablesArray.push(element.user.name)
    });
    let barChartLabels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
    return lablesArray
  }

  getBarChartOptions(res : Results) : any{
  
    let barChartOptions = {
      scaleShowVerticalLines: false,
      animation:false,
      responsive: true,
      plugins: {
        legend: {
          position: 'right',
        },
        title: {
          display: true,
          text: res.question.questionText
        }
      }
    };
    return barChartOptions
  }
  getBarChartLegend(res : Results) : any{
    
    return true
  }

}
