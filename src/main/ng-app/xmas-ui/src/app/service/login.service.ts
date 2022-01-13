import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../datamodel/User';

const endpoint = './';

@Injectable({
  providedIn: 'root'
})


export class LoginService {
  constructor(private http: HttpClient) {
}

  public doLogin(form: FormData): Observable<any> {
    return this.http.post(endpoint.concat("login"),form)
  }

  public checkStatus(): Observable<User> {
    return this.http.get<User>(endpoint.concat("user/status"))
  }

}
