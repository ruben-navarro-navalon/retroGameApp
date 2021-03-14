import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Platform } from 'src/app/models/platform';
import { RawgApiService } from 'src/app/services/rawg-api.service';
import { UserCatalogService } from 'src/app/services/user-catalog.service';
import { UserLoginService } from 'src/app/services/user-login.service';

@Component({
  selector: 'app-platforms',
  templateUrl: './platforms.component.html',
  styleUrls: ['./platforms.component.css']
})

export class PlatformsComponent implements OnInit {

  @ViewChild('widgetsContent') widgetsContent: ElementRef;

  platform: Platform;
  games: {name: string, image: string, apiId: number}[] = [];
  type: string = "platform";

  constructor(
    private userCatalogService: UserCatalogService,
    public userLoginService: UserLoginService,
    public rawgApiService: RawgApiService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.getPlatformByShortName(params.shortName);
    });
    
  }

  getPlatformByShortName(shortName: string) {
    this.userCatalogService.getPlatformByShortName(shortName).subscribe(dataResult => {
      this.platform = dataResult;
      this.getTopGames(this.platform.apiId);
    })
  }
  getTopGames(platformId: number) {
    this.games = [];
    this.rawgApiService.getTopGames(platformId).subscribe(dataResult => {
      dataResult.results.forEach(game => this.games.push({name: game.name, image: game.background_image, apiId: game.id}));
    });
  }
}
