import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Home } from './components/home/home';
import { Signup } from './components/signup/signup';
import { Dashboard } from './components/dashboard/dashboard';
import { AddAdvert } from './components/add-advert/add-advert';

export const routes: Routes = [
    {path:"", component: Home},
    {path:"login", component: Login},
    {path:"signup", component: Signup},
    {path:"dashboard", component: Dashboard},
    {path:"home", component: Home},
    {path:"add-advert", component: AddAdvert},
];
