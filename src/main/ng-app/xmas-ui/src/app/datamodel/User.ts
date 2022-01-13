import { Teams } from "./Teams";

export class User {

    constructor(
        private _userId: String,
        private _name: String,
        private _team: Teams[]=[],
        private _emailId: String,
        private _mobileNumber: String
    ) { }

    get name() {
        return this._name;
    }

    get email() {
        return this._emailId;
    }

    get userId() {
        return this._userId;
    }

    get team() {
        return this._team;
    }

    get mobileNumber() {
        return this._mobileNumber;
    }


    set name(name: String) {
        this._name = name;
    }

    set email(emailId: String) {
        this._emailId;
    }

    set userId(name: String) {
        this._userId;
    }

    set team(name: Teams[]) {
        this._team;
    }

    set mobileNumber(name: String) {
        this._mobileNumber;
    }

    getRequestObject() {
        let data = {
            "userId": this._userId,
            "name": this._name,
            "team": this.team,
            "emailId": this._emailId,
            "mobileNumber": this.mobileNumber
        }
        return data;
    }
}