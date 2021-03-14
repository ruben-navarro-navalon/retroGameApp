import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Game } from '../models/game';
import { GameFromApi } from '../models/interfaces';

@Injectable({
  providedIn: 'root'
})
export class RawgApiService {

  readonly baseUrl: string = "https://api.rawg.io/api/";

  // RAWG KEY HERE. TAKE A LOOK AT THE README
  key: string = '';

  constructor(
    private http: HttpClient,
  ) { }

  getTopGames(platformId: number): Observable<{results: GameFromApi[]}> {
    const url: string = this.baseUrl + 'games?key=' + this.key + '&&platforms=' + platformId + '&&ordering=-rating';
    return this.http.get<{results: GameFromApi[]}>(url);
  }

  async getGame(name: string): Promise<GameFromApi> {
    const url: string = this.baseUrl + 'games/' + name + '?key=' + this.key;
    return await this.http.get<GameFromApi>(url).toPromise();
  }


  // PARSE GAME FROM API TO GAME:

  parseGameFromApi(gameFromApi: GameFromApi): Game {
    let game: Game;

    let platforms: {id: number, name: string}[] = [];
    gameFromApi.platforms.forEach(platform => platforms.push({id: platform.platform.id, name: platform.platform.name}));

    let developers: {id: number, name: string}[] = [];
    gameFromApi.developers.forEach(developer => developers.push({id: developer.id, name: developer.name}));

    let genres: {id: number, name: string}[] = [];
    gameFromApi.genres.forEach(genre => genres.push({id: genre.id, name: genre.name}));

    let tags: {id: number, name: string, language: string}[] = [];
    gameFromApi.tags.forEach(tag => tags.push({id: tag.id, name: tag.name, language: tag.language}));

    let publishers: {id: number, name: string}[] = [];
    gameFromApi.publishers.forEach(publisher => publishers.push({id: publisher.id, name: publisher.name}));

    game = new Game(
      gameFromApi.id,
      gameFromApi.name,
      gameFromApi.description.replace(/<p>/g, '').replace(/<\/p>/g, '').replace(/<br \/>/g,'').replace(/&#39;/g,'').replace(/<br>/g,''),
      gameFromApi.released,
      gameFromApi.background_image,
      gameFromApi.background_image_additional,
      gameFromApi.rating,
      gameFromApi.playtime,
      platforms,
      developers,
      genres,
      tags,
      publishers
    );

    return game;
  }


  
}