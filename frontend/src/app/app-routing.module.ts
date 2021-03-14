import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { PlatformsComponent } from './components/platforms/platforms.component';
import { LoginComponent } from './components/login/login.component';
import { GameComponent } from './components/game/game.component';
import { AuthGuard } from './auth/auth.guard';
import { ProfileComponent } from './components/profile/profile.component';
import { GameSearchComponent } from './components/game-search/game-search.component';
import { StoreComponent } from './components/store/store.component';



const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "platforms/:shortName",
    component: PlatformsComponent
  },
  {
    path: "games",
    component: GameSearchComponent
  },
  {
    path: "games/:name",
    component: GameComponent
  },
  {
    path: "store",
    component: StoreComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "profile",
    component: ProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "login",
    component: LoginComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
