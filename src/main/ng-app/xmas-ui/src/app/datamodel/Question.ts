import { Teams } from "./Teams";

export class Question{
    

    constructor(private _questionId: String,private _questionText: String,private _description : String,private _teams :Teams[]){

    }

    get questionText(){
        return this._questionText;
    }

    get description(){
        return this._description;
    }

    get questionId(){
        return this._questionId;
    }

    
    get teams(){
        return this._teams;
    }

    set questionText(questionText){
        this._questionText=questionText;
    }

    set description(desciption){
        this._description=desciption;
    }

    set questionId(questionId){
        this._questionId=questionId;
    }

    
    set teams(team){
        this._teams=team;;
    }

    getRequestObject() {
        let data = {
            "questionId":this._questionId
        }
        return data;
    }

}