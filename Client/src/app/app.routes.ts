import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Home } from './components/home/home';
import { Signup } from './components/signup/signup';

export const routes: Routes = [
    {path:"", component: Home},
    {path:"login", component: Login},
    {path:"signup", component: Signup},
];
