import { Injectable } from '@angular/core';
import { HttpClient, HttpParams ,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../datamodel/Question';
import { Teams } from '../datamodel/Teams';
import { UserGiftPreference } from '../datamodel/UserGiftPreference';
import { QuestionAnswer } from '../datamodel/QuestionAnswer';
import { Results } from '../datamodel/Results';

const endpoint = './';

@Injectable({
  providedIn: 'root'
})
export class PollsService {
  
  private  myStorage:Storage =window.localStorage;

  private uniqueId:any;

  constructor(private http: HttpClient) {
    if(this.myStorage.getItem("XMAS-UNIQUE")==null){
      this.uniqueId=this.getUniqueId(4);
      this.myStorage.setItem("XMAS-UNIQUE", this.uniqueId);
    }else{
      this.uniqueId=this.myStorage.getItem("XMAS-UNIQUE")
    }

  }

  public getQuestions(team: Teams): Observable<Question[]> {
    let uniqueId=this.myStorage.getItem("XMAS-UNIQUE");
    const headers= new HttpHeaders()
    .set('XMAS-UNIQUE', this.uniqueId);

    let params = new HttpParams().set('team', team);
    return this.http.get<Question[]>(endpoint.concat("polls/question"),{ params: params,headers:headers },)
  }

  public getChild(): Observable<UserGiftPreference[]> {
    
    return this.http.get<UserGiftPreference[]>(endpoint.concat("user/child"))
  }

  vote(answer: QuestionAnswer):Observable<any> {
    let uniqueId=this.myStorage.getItem("XMAS-UNIQUE");
    const headers= new HttpHeaders()
    .set('XMAS-UNIQUE', this.uniqueId);
    return this.http.post<any>(endpoint.concat("polls/vote"),answer.getRequestObject(),{ 'headers': headers });
  }

  public getResults(team: Teams): Observable<Results[]> {
    let params = new HttpParams().set('team', team);
    return this.http.get<Results[]>(endpoint.concat("polls/results"),{ params: params })
  }

  getUniqueId(parts: number): string {
    const stringArr = [];
    for(let i = 0; i< parts; i++){
      // tslint:disable-next-line:no-bitwise
      const S4 = (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
      stringArr.push(S4);
    }
    return stringArr.join('-');
  }

}
