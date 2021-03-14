export class User {

    constructor (
        private _id: number,
        private _username: string,
        private _name: string,
        private _email: string,
        private _state: string,
        private _town: string
    ) {}

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get username(): string {
        return this._username;
    }
    public set username(value: string) {
        this._username = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get email(): string {
        return this._email;
    }
    public set email(value: string) {
        this._email = value;
    }
    public get state(): string {
        return this._state;
    }
    public set state(value: string) {
        this._state = value;
    }
    public get town(): string {
        return this._town;
    }
    public set town(value: string) {
        this._town = value;
    }
}
