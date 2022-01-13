import { User } from "./User";

export class UserResult{
    constructor(private _user:User,private _vote:Number){

    }
    get user(){
        return this._user;
    }
    get vote(){
        return this._vote;
    }
}