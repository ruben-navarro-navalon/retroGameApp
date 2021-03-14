import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SellerOrBuyer } from 'src/app/models/interfaces';
import { User } from 'src/app/models/user';
import { GeoApiService } from 'src/app/services/geo-api.service';
import { UserCatalogService } from 'src/app/services/user-catalog.service';
import { UserLoginService } from 'src/app/services/user-login.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

  wanted: {id: number, apiId: number}[] = [];
  sell: {id: number, apiId: number}[] = [];
  user: User;

  sellerOrBuyerList: SellerOrBuyer[] = [];

  stateSelected: string = "";
  stateList: {CPRO: string, PRO: string}[] = [];
  townList: {CMUM: string, DMUN50: string}[] = [];

  form: FormGroup;

  state: FormControl;
  town: FormControl;

  constructor(
    private geoApiService: GeoApiService,
    private userLoginService: UserLoginService,
    private userCatalogService: UserCatalogService,
    private router: Router
  ) {
    this.state = new FormControl('', [Validators.required]);
    this.town = new FormControl('');

    this.form = new FormGroup({
      state: this.state,
      town: this.town
    });
  }

  ngOnInit(): void {
    this.getUserLists();
    this.getStates();
  }

  getStates() {
    this.geoApiService.getStates().subscribe(dataResult => {
      dataResult.data.forEach(state => this.stateList.push(state));
    });
  }
  getTowns(CPRO: string) {
    this.townList=[];
    this.geoApiService.getTowns(CPRO).subscribe(dataResult => {
      dataResult.data.forEach(town => this.townList.push(town));
    })
  }

  getUserLists() {
    this.userLoginService.getUser(this.userLoginService.getUsername()).subscribe(dataResult => {
      this.user = new User (dataResult.id, dataResult.username, dataResult.name, dataResult.email, dataResult.state, dataResult.town);

      this.userCatalogService.getWanted(this.user.id).subscribe(dataResult => {
        dataResult.gameList.forEach(game => this.wanted.push(game));
      });

      this.userCatalogService.getSell(this.user.id).subscribe(dataResult => {
        dataResult.gameList.forEach(game => this.sell.push(game));
      });

    });
  }
  

  onSubmit(formDirective: FormGroupDirective) {
    this.sellerOrBuyerList = [];

    this.wanted.forEach(game => {
      this.userCatalogService.getSellers(game.apiId).subscribe(dataResult => {
        dataResult.forEach(sellerOrBuyer => {
          if (this.town.value === ''){
            if (sellerOrBuyer.state === this.state.value) {
              this.sellerOrBuyerList.push(sellerOrBuyer);
            }
          } else {
            if (sellerOrBuyer.town === this.town.value) {
              this.sellerOrBuyerList.push(sellerOrBuyer);
            }
          }
        });
      });
    })

    this.sell.forEach(game => {
      this.userCatalogService.getBuyers(game.apiId).subscribe(dataResult => {
        dataResult.forEach(sellerOrBuyer => {
          if (this.town.value === ''){
            if (sellerOrBuyer.state === this.state.value) {
              this.sellerOrBuyerList.push(sellerOrBuyer);
            }
          } else {
            if (sellerOrBuyer.town === this.town.value) {
              this.sellerOrBuyerList.push(sellerOrBuyer);
            }
          }
        });
      });
    })
  

  }



}
