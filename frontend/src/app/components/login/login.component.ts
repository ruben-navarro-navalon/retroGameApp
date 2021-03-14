import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserLoginService } from 'src/app/services/user-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formImage: string = "";
  formImageList: string[] = ["mario.png", "luigi.png", "link.png", "donkeykong.png", "toad.png", "peach.png", "diddy.png","kirby.png","zelda.png", "samus.png", "megaman.png", "pikachu.png", "sonic.png"];

  invalidUsernameOrPassword: boolean = false;
  invalidUsernameOrPasswordError: string = '';


  form: FormGroup;

  username: FormControl;
  password: FormControl;

  constructor(
    private userLoginService: UserLoginService,
    private router: Router
  ) {
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);

    this.form = new FormGroup({
      username: this.username,
      password: this.password
    });
  }

  ngOnInit(): void {
    this.getRandomImage();
  }

  getRandomImage(){
    let randomNum: number = Math.floor(Math.random() * 13);
    this.formImage = this.formImageList[randomNum];
  }

  onSubmit(formDirective: FormGroupDirective) {
    let user = {username: this.username.value, password: this.password.value};
    this.userLoginService.login(user).subscribe(dataResult => {
      this.userLoginService.setToken(dataResult.jwt, this.username.value);
      this.router.navigate([""]);
    }, error => {
      this.invalidUsernameOrPassword = true;
      this.invalidUsernameOrPasswordError = error.error.message;
      console.log(error);
    })

  }
}
