import { TOUCH_BUFFER_MS } from '@angular/cdk/a11y';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { RawgApiService } from 'src/app/services/rawg-api.service';
import { UserCatalogService } from 'src/app/services/user-catalog.service';
import { UserLoginService } from 'src/app/services/user-login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  town: string = '';
  state: string = '';
  collection: {id: number, apiId: number}[] = [];
  wanted: {id: number, apiId: number}[] = [];
  sell: {id: number, apiId: number}[] = [];
  gamesInCollection: {name: string, image: string, apiId: number}[] = [];
  gamesThatIWant: {name: string, image: string, apiId: number}[] = [];
  gamesThatISell: {name: string, image: string, apiId: number}[] = [];

  type: string[] = ['collection', 'wanted', 'sell'];

  constructor(
    private userLoginService: UserLoginService,
    private userCatalogService: UserCatalogService,
    private rawgApiService: RawgApiService,
  ) { }

  ngOnInit(): void {
    this.getUser();
  }

  removeFromCollection(index: number) {
    let gameToRemove = this.gamesInCollection[index];
    this.gamesInCollection.splice(index,1);

    let indexToRemove = this.gamesThatISell.indexOf(gameToRemove);

    if (indexToRemove !== -1) {
      this.gamesThatISell.splice(indexToRemove,1);
    }

  }

  removeFromWanted(index: number) {
    this.gamesThatIWant.splice(index,1);
  }

  moveFromWantedToCollection(index: number) {
    let gameToAddToCollection = this.gamesThatIWant[index];
    this.gamesThatIWant.splice(index,1);

    this.gamesInCollection.push(gameToAddToCollection);
    this.sortGameList(this.gamesInCollection);
  }

  addToSellFromCollection(index: number) {
    let gameToAddToSell = this.gamesInCollection[index];

    this.gamesThatISell.push(gameToAddToSell);
    this.sortGameList(this.gamesThatISell);
  }

  removeFromSell(index: number) {
    this.gamesThatISell.splice(index,1);
  }

  sold(index: number) {
    let gameToRemoveFromCollection = this.gamesThatISell[index];
    this.gamesThatISell.splice(index,1);

    let indexToRemove = this.gamesInCollection.indexOf(gameToRemoveFromCollection);
    if (indexToRemove !== -1){
      this.gamesInCollection.splice(indexToRemove, 1);
    }

  }

  getUser() {

    this.collection=[];
    this.wanted=[];
    this.gamesInCollection=[];
    this.gamesThatIWant=[];

    this.userLoginService.getUser(this.userLoginService.getUsername()).subscribe(dataResult => {
      this.user = new User (dataResult.id, dataResult.username, dataResult.name, dataResult.email, dataResult.state, dataResult.town);

      this.userCatalogService.getCollection(dataResult.id).subscribe(dataResult => {
        dataResult.gameList.forEach(game => this.collection.push(game));
        this.collection.forEach(game => this.rawgApiService.getGame(game.apiId.toString()).then(dataResult => {
          this.gamesInCollection.push({name: dataResult.name, image: dataResult.background_image, apiId: dataResult.id});
          this.sortGameList(this.gamesInCollection);
        }))
      });

      this.userCatalogService.getWanted(dataResult.id).subscribe(dataResult => {
        dataResult.gameList.forEach(game => this.wanted.push(game));
        this.wanted.forEach(game => this.rawgApiService.getGame(game.apiId.toString()).then(dataResult => {
          this.gamesThatIWant.push({name: dataResult.name, image: dataResult.background_image, apiId: dataResult.id});
          this.sortGameList(this.gamesThatIWant);
        }))
      });

      this.userCatalogService.getSell(dataResult.id).subscribe(dataResult => {
        dataResult.gameList.forEach(game => this.sell.push(game));
        this.sell.forEach(game => this.rawgApiService.getGame(game.apiId.toString()).then(dataResult => {
          this.gamesThatISell.push({name: dataResult.name, image: dataResult.background_image, apiId: dataResult.id});
          this.sortGameList(this.gamesThatISell);
        }))
      });

    });
  }

  sortGameList(gameList: {name: string, image: string, apiId: number}[]): {name: string, image: string, apiId: number}[] {
    gameList.sort((a,b) => (a.name < b.name ? -1 : 1));
    return gameList;
  }
  
}
