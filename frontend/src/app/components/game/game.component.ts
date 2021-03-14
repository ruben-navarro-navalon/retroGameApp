import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Game } from 'src/app/models/game';
import { RawgApiService } from 'src/app/services/rawg-api.service';
import { UserCatalogService } from 'src/app/services/user-catalog.service';
import { UserLoginService } from 'src/app/services/user-login.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  game: Game = new Game(0,'','',new Date('1900-01-01'),'','',0,0,[],[],[],[],[]);
  notFound: boolean = false;
  errorMessage: string = '';

  constructor(
    private rawgApiService: RawgApiService,
    public userLoginService: UserLoginService,
    private userCatalogService: UserCatalogService,
    public dialog: MatDialog,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.getGame(params.name);
    });
  }

  checkplatform(platforms: {platform: {id: number, name: string}}[]): boolean {
    if (platforms.some(platform => platform.platform.name === "NES") ||
        platforms.some(platform => platform.platform.name === "SNES") ||
        platforms.some(platform => platform.platform.name === "Nintendo 64") ||
        platforms.some(platform => platform.platform.name === "GameCube") ||
        platforms.some(platform => platform.platform.name === "Wii") ||
        platforms.some(platform => platform.platform.name === "Game Boy") ||
        platforms.some(platform => platform.platform.name === "Game Boy Color") ||
        platforms.some(platform => platform.platform.name === "Game Boy Advance" ||
        platforms.some(platform => platform.platform.name === "SEGA Master System") ||
        platforms.some(platform => platform.platform.name === "Genesis") ||
        platforms.some(platform => platform.platform.name === "SEGA Saturn") ||
        platforms.some(platform => platform.platform.name === "Dreamcast") ||
        platforms.some(platform => platform.platform.name === "Game Gear"))) {
          return true;
    }
    this.errorMessage = "This game is not available for RetroGameApp platforms";
    return false;
  }

  getGame(name: string) {
    this.rawgApiService.getGame(name).then(dataResult => {
      if (!this.checkplatform(dataResult.platforms)) {
        this.notFound=true;
      } else {
        this.game = this.rawgApiService.parseGameFromApi(dataResult);
      }
    }, error => {
      console.log(error);
      this.notFound = true;
      this.errorMessage = error.status + ': ' + error.statusText;
    });
  }

  addToCollection(apiId: number){
    let present: boolean = false;
    let userId: number;

    this.userLoginService.getUser(this.userLoginService.getUsername()).subscribe(dataResult => {
      userId = dataResult.id;
      this.userCatalogService.getCollection(userId).subscribe(dataResult =>{
        dataResult.gameList.forEach(game => {if (game.apiId === apiId) {
          present = true;
        }});
        this.userCatalogService.getWanted(userId).subscribe(dataResult =>{
          dataResult.gameList.forEach(game => {if (game.apiId === apiId) {
            present = true;
          }});
          if (!present) {
            this.userCatalogService.addToCollection(apiId).subscribe();
          }
          this.openDialog("collection", present);
      })
    });
    });
  }
  
  addToWanted(apiId: number){
    let present: boolean = false;
    let userId: number;

    this.userLoginService.getUser(this.userLoginService.getUsername()).subscribe(dataResult => {
      userId = dataResult.id;
      this.userCatalogService.getWanted(userId).subscribe(dataResult =>{
        dataResult.gameList.forEach(game => {if (game.apiId === apiId) {
          present = true;
        }});
        this.userCatalogService.getCollection(userId).subscribe(dataResult =>{
          dataResult.gameList.forEach(game => {if (game.apiId === apiId) {
            present = true;
          }});
          if (!present) {
            this.userCatalogService.addToWanted(apiId).subscribe();
          }
          this.openDialog("wanted", present);
      })
    });
    });
  }

  openDialog(whatCollection: string, present: boolean) {
    if (present === false){
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Added to your ' + whatCollection + ' list!',
          body: this.game.name + ' has been added succesfully to your ' + whatCollection + ' list.',
          type: "statusMessage",
          apiId: this.game.id
        }
      });
    } else {
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Already in one of your lists!',
          body: this.game.name + ' is already in one of your lists. You can\'t add it twice.',
          type: "statusMessage",
          apiId: this.game.id
        }
      });
    }
  }

}
