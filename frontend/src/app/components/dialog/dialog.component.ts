import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef ,MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserCatalogService } from 'src/app/services/user-catalog.service';
import { UserLoginService } from 'src/app/services/user-login.service';
import { CarouselComponent } from '../carousel/carousel.component';
import { ProfileComponent } from '../profile/profile.component';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  returnData: {index: number, action: string};

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: {title: string, body: string, type: string, apiId: number},
    public userLoginService: UserLoginService,
    private userCatalogService: UserCatalogService,
    private router: Router,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<CarouselComponent>
    ) {}

  ngOnInit(): void {
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
  removeFromCollection(apiId: number){
    this.userCatalogService.removeFromCollection(apiId).subscribe();
    this.returnData = {index: parseInt(this.data.body), action: "removeFromCollection"};
    this.dialogRef.close(this.returnData);
  }
  addToSell(apiId: number) {
    this.userCatalogService.addToSell(apiId).subscribe();
    this.returnData = {index: parseInt(this.data.body), action: "addToSell"};
    this.dialogRef.close(this.returnData);
  }
  removeFromWanted(apiId: number) {
    this.userCatalogService.removeFromWanted(apiId).subscribe();
    this.returnData = {index: parseInt(this.data.body), action: "removeFromWanted"};
    this.dialogRef.close(this.returnData);
  }
  moveFromWantedToCollection(apiId: number) {
    this.userCatalogService.moveFromWantedToCollection(apiId).subscribe();
    this.returnData = {index: parseInt(this.data.body), action: "moveFromWantedToCollection"};
    this.dialogRef.close(this.returnData);
  }

  removeFromSell(apiId: number) {
    this.userCatalogService.removeFromSell(apiId).subscribe();
    this.returnData = {index: parseInt(this.data.body), action: "removeFromSell"};
    this.dialogRef.close(this.returnData);
  }

  soldAndRemoveFromCollection(apiId: number) {
    this.userCatalogService.removeFromSell(apiId).subscribe();
    this.userCatalogService.removeFromCollection(apiId).subscribe();
    this.returnData = {index: parseInt(this.data.body), action: "sold"};
    this.dialogRef.close(this.returnData);
  }


  openDialog(whatCollection: string, present: boolean) {
    if (present === false){
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Added to your ' + whatCollection + ' list!',
          body: '',
          type: "statusMessage",
          apiId: 0
        }
      });
    } else {
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Already in one of your lists!',
          body: '',
          type: "statusMessage",
          apiId: 0
        }
      });
    }
  }
}
