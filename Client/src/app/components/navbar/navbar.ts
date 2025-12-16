import { Component, OnInit, HostListener, signal } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css'],
})
export class Navbar implements OnInit {
  isScrolled = false;
  connected = signal(false);
  userIcon = signal('icons/user_icon.svg');

  constructor(private cookieService: CookieService, private router: Router) {}

  ngOnInit(): void {
    this.checkConnection();
    console.log(this.connected());
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.pageYOffset > 50;
  }

  checkConnection() {
    const user = this.cookieService.get('connected_user');
    if (user) {
      this.connected.set(true);
      this.userIcon.set('icons/logout_icon.svg');
    } else {
      this.connected.set(false);
      this.userIcon.set('icons/user_icon.svg');
    }
  }

  handleUserButton() {
    if (this.connected()) {
      // Déconnexion
      this.cookieService.delete('connected_user', '/');
      this.connected.set(false);
      this.userIcon.set('icons/user_icon.svg');
    } else {
      this.router.navigate(['/login']);
    }
  }
}
