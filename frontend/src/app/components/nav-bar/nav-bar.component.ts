import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Platform } from 'src/app/models/platform';
import { UserCatalogService } from 'src/app/services/user-catalog.service';
import { UserLoginService } from 'src/app/services/user-login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  platforms: Platform[] = [];

  constructor(
    public userLoginService: UserLoginService,
    private router: Router,
    private userCatalogService: UserCatalogService
    ) {}

  ngOnInit(): void {
    this.getPlatformNames();
  }

  logout() {
    this.userLoginService.deleteCookies();
    this.router.navigate(['']);
  }

  getPlatformNames() {
    this.userCatalogService.getPlatforms().subscribe(dataResult => {
      this.platforms = dataResult;
    })
  }


}