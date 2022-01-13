import { Question } from "./Question";
import { UserResult } from "./UserResult";

export class Results{
    
    constructor(private _question:Question,private _results:Array<UserResult>,private _totalVotes:Number,private _totalUniqueVotes:Number){
        
    }
    get question(){
        return this._question;
    }
    get results(){
        return this._results;
    }
    get totalVotes(){
        return this._totalVotes;
    }
    get totalUniqueVotes(){
        return this._totalUniqueVotes;
    }


}