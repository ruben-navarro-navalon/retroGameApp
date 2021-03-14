export class Platform {

    constructor(
        private _id: number,
        private _apiId: number,
        private _name: string,
        private _shortName: string,
        private _gamesCount: number,
        private _description: string,
        private _image: string,
        private _released: Date,
        private _portable: boolean
    ){}
    
    public get shortName(): string {
        return this._shortName;
    }
    public set shortName(value: string) {
        this._shortName = value;
    }
    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get gamesCount(): number {
        return this._gamesCount;
    }
    public set gamesCount(value: number) {
        this._gamesCount = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }
    public get portable(): boolean {
        return this._portable;
    }
    public set portable(value: boolean) {
        this._portable = value;
    }
    public get released(): Date {
        return this._released;
    }
    public set released(value: Date) {
        this._released = value;
    }
    public get image(): string {
        return this._image;
    }
    public set image(value: string) {
        this._image = value;
    }
    public get apiId(): number {
        return this._apiId;
    }
    public set apiId(value: number) {
        this._apiId = value;
    }
}