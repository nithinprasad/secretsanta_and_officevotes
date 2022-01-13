import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from './datamodel/User';
import { UserService } from './service/user.service';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private messageSource:BehaviorSubject<any> = new BehaviorSubject([]);
  currentMessage = this.messageSource.asObservable();

  constructor(private userService:UserService) { }


  updateUser(){
    this.userService.getUsers().subscribe({
      next: data => this.messageSource.next(data)
    });
    
  }
}
