import { User } from "./User";
import { UserRoles } from "./UserRoles";

export class UserAuth{
    constructor(private _userId:User,private _password:String,private _userRoles:UserRoles[]){

    }

    get userId(){
        return this._userId;
    }

    get password(){
        return this._password;
    }

    get userRoles(){
        return this._userRoles;
    }
}