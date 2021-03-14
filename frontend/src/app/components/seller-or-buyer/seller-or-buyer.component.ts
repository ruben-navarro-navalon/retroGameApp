import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SellerOrBuyer } from 'src/app/models/interfaces';
import { User } from 'src/app/models/user';
import { EmailService } from 'src/app/services/email.service';
import { RawgApiService } from 'src/app/services/rawg-api.service';
import { UserLoginService } from 'src/app/services/user-login.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-seller-or-buyer',
  templateUrl: './seller-or-buyer.component.html',
  styleUrls: ['./seller-or-buyer.component.css']
})
export class SellerOrBuyerComponent implements OnInit {

  userName: string = this.userLoginService.getUsername();
  userMail: string;


  @Input() sellerOrBuyer: SellerOrBuyer;
  gameName: string;

  constructor(
    private rawgApiService: RawgApiService,
    private userLoginService: UserLoginService,
    private emailService: EmailService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.rawgApiService.getGame(this.sellerOrBuyer.apiId.toString()).then(dataResult => {
      this.gameName = dataResult.name;
    });
  }

  sendMail() {
    this.userLoginService.getUser(this.userName).subscribe(dataResult => {
      this.userMail = dataResult.email;

      let textMessage: string = "";
      let subject: string = "";

      if (this.sellerOrBuyer.whatWants === "buy") {
        textMessage = "Hi " + this.sellerOrBuyer.username + "!\n\nI am " + this.userName + " and I want to sell you my " + this.gameName + ". Please contact me on " + this.userMail + ". See ya!";
        subject = this.userName + " wants to sell you something";
      } else if (this.sellerOrBuyer.whatWants === "sell") {
        textMessage = "Hi " + this.sellerOrBuyer.username + "!\n\nI am " + this.userName + " and I want to buy your " + this.gameName + ". Please contact me on " + this.userMail + ". See ya!";
        subject = this.userName + " wants to buy you something";
      }
      this.emailService.sendContactEmail(textMessage, this.sellerOrBuyer.email, subject).subscribe();
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Email sent to ' + this.sellerOrBuyer.username,
          body: 'Wait to an answer, maybe it will be a big deal!',
          type: "sellOrBuy",
          apiId: 0
        }});
    });
  }

}
