import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Teams } from '../datamodel/Teams';
import { User } from '../datamodel/User';
import { UserAuth } from '../datamodel/UserAuth';
import { UserGiftPreference } from '../datamodel/UserGiftPreference';

const endpoint = './';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(endpoint.concat("user"))
  }

  public getUsersByTeams(team: Teams): Observable<User[]> {
    let params = new HttpParams().set('team', team);
    return this.http.get<User[]>(endpoint.concat("user"),{ params: params })
  }

  public getTeams(): Observable<Teams[]> {
    return this.http.get<Teams[]>(endpoint.concat("user/teams"))
  }

  public saveUser(pref:UserGiftPreference): Observable<UserAuth>{
    let res=pref.getRequestBody();
    return this.http.post<UserAuth>(endpoint.concat("user/signup"),res);
  }

}
