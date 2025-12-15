import { Component, OnInit, HostListener } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css'],
})
export class Navbar implements OnInit {
  isScrolled = false;
  connected: boolean = false;
  userIcon: string = 'icons/user_icon.svg'; // icone par défaut

  constructor(private cookieService: CookieService, private router: Router) { }

  ngOnInit(): void {
    this.checkConnection();
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.pageYOffset > 50;
  }

  checkConnection() {
    const user = this.cookieService.get('connected_user');
    if (user) {
      this.connected = true;
      this.userIcon = 'icons/logout_icon.svg';
    } else {
      this.connected = false;
      this.userIcon = 'icons/user_icon.svg';
    }
  }

  handleUserButton() {
    if (this.connected) {
      // Déconnexion
      this.cookieService.delete('connected_user', '/');
      this.connected = false;
      this.userIcon = 'icons/user_icon.svg';
    } else {
      // Redirection vers la page de connexion
      this.router.navigate(['/login']);
    }
  }
}
