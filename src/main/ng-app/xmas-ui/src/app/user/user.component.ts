import { Component, Injectable, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { User } from '../datamodel/User';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
@Injectable({
  providedIn: 'root'
})
export class UserComponent implements OnInit {

  private users: User[]=[];

  constructor(private service: UserService , private dataService:DataService) {
    this.getUserList();
    this.dataService.currentMessage.subscribe(data => this.Users=data);
  }


  getUserList() {
    let res = this.service.getUsers();
    res.subscribe({
      next: data => {
        this.users = data;
        this.users.forEach(element => {
          let teams=element.team;
        });
      
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }


  ngOnInit(): void {
    this.getUserList();
  }

  set Users(value: User[]) {
    this.users = value;
  }

  get Users() {
    return this.users;
  }

}
