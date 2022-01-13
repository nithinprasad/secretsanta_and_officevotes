import { Teams } from "./Teams";
import { User } from "./User";

export class UserGiftPreference {
    constructor(
        private _id: String,
        private _user: User,
        private _preference: String[],
        private _deliveryAddress: String,
        private _postalCode: String
    ) { }

    get Id() {
        return this._id;
    }

    set Id(id: String) {
        this._id = id;
    }
    get User() {
        return this._user;
    }

    set User(user: User) {
        this._user = user;
    }
    get Preference() {
        return this._preference;
    }

    set Preference(preference: String[]) {
        this._preference = preference;
    }
    get DeliveryAddress() {
        return this._id;
    }

    set DeliveryAddress(deliveryAddress: String) {
        this._deliveryAddress = deliveryAddress;
    }
    get postalCode() {
        return this._postalCode;
    }

    set PostalCode(id: String) {
        this._postalCode = id;
    }

    getRequestBody() {
        let data = {
            "user": this._user.getRequestObject(),
            "preference": this._preference,
            "deliveryAddress": this._deliveryAddress,
            "postalCode": this._postalCode
        }
        
        return data;
    }
}