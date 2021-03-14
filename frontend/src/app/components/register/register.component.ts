import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmailService } from 'src/app/services/email.service';
import { GeoApiService } from 'src/app/services/geo-api.service';
import { UserLoginService } from 'src/app/services/user-login.service';
import { CustomValidators } from 'src/app/utils/custom-validators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  formImage: string = "";
  formImageList: string[] = ["mario.png", "luigi.png", "link.png", "donkeykong.png", "toad.png", "peach.png", "diddy.png","kirby.png","zelda.png", "samus.png", "megaman.png", "pikachu.png", "sonic.png"];
  stateSelected: string = "";
  stateList: {CPRO: string, PRO: string}[] = [];
  townList: {CMUM: string, DMUN50: string}[] = [];
  usernameTaken: boolean = false;
  usernameTakenError: string = '';
  openedForm: boolean = false;

  form: FormGroup;

  username: FormControl;
  name: FormControl;
  email: FormControl;
  state: FormControl;
  town: FormControl;
  password: FormControl;
  passwordCheck: FormControl;

  constructor(
    private userLoginService: UserLoginService,
    private geoApiService: GeoApiService,
    private emailService: EmailService,
    private router: Router
  ) {
    this.username = new FormControl('', [Validators.required, CustomValidators.usernameValidator]);
    this.name = new FormControl('', [Validators.required, CustomValidators.nameValidator]);
    this.email = new FormControl('', [Validators.required, CustomValidators.emailValidator]);
    this.state = new FormControl('', [Validators.required]);
    this.town = new FormControl('',[Validators.required]);
    this.password = new FormControl('', [Validators.required, CustomValidators.passwordValidator]);
    this.passwordCheck = new FormControl('', [Validators.required, CustomValidators.passwordMatchValidator("password")]);

    this.form = new FormGroup({
      username: this.username,
      name: this.name,
      email: this.email,
      state: this.state,
      town: this.town,
      password: this.password,
      passwordCheck: this.passwordCheck
    })
    this.form.controls.password.valueChanges.subscribe(() => {
      this.form.controls.passwordCheck.updateValueAndValidity();
    });
  }

  ngOnInit(): void {
    this.getRandomImage();
    this.getStates();
  }

  getRandomImage(){
    let randomNum: number = Math.floor(Math.random() * 13);
    this.formImage = this.formImageList[randomNum];
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

  onSubmit(formDirective: FormGroupDirective) {
    let user = {username: this.username.value, name: this.name.value, email: this.email.value, state: this.state.value, town: this.town.value, password: this.password.value};
    this.userLoginService.register(user).subscribe(dataResult =>{
      this.emailService.sendRegisterEmail(this.email.value).subscribe(data => console.log(data));
      this.form.reset();
      formDirective.resetForm();
      this.usernameTaken = false;
      this.router.navigate(["login"]);
    }, error => {
      this.usernameTaken = true;
      this.usernameTakenError = error.error;
    });
  
  }

  openForm() {
    this.openedForm = true;
  }
}
