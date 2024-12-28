import { Routes } from '@angular/router';
import { LandingPageComponent } from '../pages/landing-page/landing-page.component';
import { SignupPageComponent } from '../pages/signup-page/signup-page.component';
import { LoginPageComponent } from '../pages/login-page/login-page.component';
import { HomePageComponent } from '../pages/home-page/home-page.component';

export const routes: Routes = [
    { path: '', redirectTo: '', pathMatch: 'full', component: LandingPageComponent},
    { path: 'signup', component: SignupPageComponent },
    { path: 'login', component: LoginPageComponent },
    { path: 'dashboard', component: HomePageComponent },
];
