import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserLoginService } from 'src/app/services/user-login.service';

@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css']
})
export class GameSearchComponent implements OnInit {

  form: FormGroup;

  game: FormControl;

  constructor(
    private userLoginService: UserLoginService,
    private router: Router
  ) {
    this.game = new FormControl('', [Validators.required]);

    this.form = new FormGroup({
      game: this.game
    });
  }

  ngOnInit(): void {
  }

  onSubmit(formDirective: FormGroupDirective){
    let gameName: string = this.game.value;

    gameName = gameName.replace(/ /g,"-");
    
    this.router.navigate(["games/" + gameName]);
  }

}
