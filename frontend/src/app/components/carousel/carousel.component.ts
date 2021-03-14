import { Component, EventEmitter, ElementRef, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { RawgApiService } from 'src/app/services/rawg-api.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

  @ViewChild('widgetsContent') widgetsContent: ElementRef;

  platformName: string = '';
  @Input() games: {name: string, image: string, apiId: number}[] = [];
  @Input() type: string= '';

  @Output() removeFromCollectionEvent = new EventEmitter();
  @Output() removeFromWantedEvent = new EventEmitter();
  @Output() moveFromWantedToCollectionEvent = new EventEmitter();
  @Output() addToSellEvent = new EventEmitter();
  @Output() removeFromSellEvent = new EventEmitter();
  @Output() soldEvent = new EventEmitter();


  constructor(
    public dialog: MatDialog,
    ) { }

  ngOnInit(): void {
  }

  scrollLeft(){
    this.widgetsContent.nativeElement.scrollLeft -= 250;
  }

  scrollRight(){
    this.widgetsContent.nativeElement.scrollLeft += 250;
  }

  openOptions(name: string, apiId: number, index: number) {
    let dialogRef = this.dialog.open(DialogComponent, {
      data: {
        title: 'Options for ' + name,
        body: index,
        type: this.type,
        apiId: apiId
      }
    });

    dialogRef.afterClosed().subscribe(dataResult => {
      if (dataResult.action === "removeFromCollection") {
        this.removeFromCollectionEvent.emit(dataResult.index);
      } else if (dataResult.action === "removeFromWanted") {
        this.removeFromWantedEvent.emit(dataResult.index);
      } else if (dataResult.action === "moveFromWantedToCollection") {
        this.moveFromWantedToCollectionEvent.emit(dataResult.index);
      } else if (dataResult.action === "addToSell") {
        this.addToSellEvent.emit(dataResult.index);
      } else if (dataResult.action === "removeFromSell") {
        this.removeFromSellEvent.emit(dataResult.index);
      } else if (dataResult.action === "sold") {
        this.soldEvent.emit(dataResult.index);
      }
    });
  }


  

}
