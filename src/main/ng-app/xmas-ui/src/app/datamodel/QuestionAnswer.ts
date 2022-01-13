import { Question } from "./Question";
import { Teams } from "./Teams";
import { User } from "./User";

export class QuestionAnswer{
    constructor(private _id?:String,private _question?:Question,private _answer?:User[],private _team?:Teams,private _trackingId?:String){

    }


    get teams(){
        return this._team;
    }

    get question(){
        return this._question;
    }

    get answer():User[]{
        return this.answer;
    }

    getRequestObject() {
       let  userArr:Array<any>=[];
       this._answer?.forEach(data =>userArr.push({"userId":data.userId}));
       let data:any = {
            "question": {"questionId":this._question?.questionId},
            "answer":userArr ,
            "team": this._team
        }
        return data;
    }
}