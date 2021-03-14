export class Game {

    constructor(
        private _id: number,
        private _name: string,
        private _description: string,
        private _released: Date,
        private _background_image: string,
        private _background_image_additional: string,
        private _rating: number,
        private _playtime: number,
        private _platforms: { id: number; name: string; }[],
        private _developers: { id: number; name: string; }[],
        private _genres: { id: number; name: string; }[],
        private _tags: { id: number; name: string; language: string; }[],
        private _publishers: { id: number; name: string; }[],
    ){ }

    public get publishers(): { id: number; name: string; }[] {
        return this._publishers;
    }
    public set publishers(value: { id: number; name: string; }[]) {
        this._publishers = value;
    }
    public get tags(): { id: number; name: string; language: string; }[] {
        return this._tags;
    }
    public set tags(value: { id: number; name: string; language: string; }[]) {
        this._tags = value;
    }
    public get genres(): { id: number; name: string; }[] {
        return this._genres;
    }
    public set genres(value: { id: number; name: string; }[]) {
        this._genres = value;
    }
    public get developers(): { id: number; name: string; }[] {
        return this._developers;
    }
    public set developers(value: { id: number; name: string; }[]) {
        this._developers = value;
    }
    public get platforms(): { id: number; name: string; }[] {
        return this._platforms;
    }
    public set platforms(value: { id: number; name: string; }[]) {
        this._platforms = value;
    }
    public get playtime(): number {
        return this._playtime;
    }
    public set playtime(value: number) {
        this._playtime = value;
    }
    public get rating(): number {
        return this._rating;
    }
    public set rating(value: number) {
        this._rating = value;
    }
    public get background_image_additional(): string {
        return this._background_image_additional;
    }
    public set background_image_additional(value: string) {
        this._background_image_additional = value;
    }
    public get background_image(): string {
        return this._background_image;
    }
    public set background_image(value: string) {
        this._background_image = value;
    }
    public get released(): Date {
        return this._released;
    }
    public set released(value: Date) {
        this._released = value;
    }
    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
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
}
