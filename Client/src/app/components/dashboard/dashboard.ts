import { Component, OnInit, signal } from '@angular/core';
import { Advert, AdvertService } from '../../core/services/advert.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AppUser } from '../../core/services/app-user.service';
import { CookieService } from 'ngx-cookie-service';
import { AdvertDetailedCard } from "../advert-detailed-card/advert-detailed-card";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'],
  imports: [FormsModule, CommonModule, AdvertDetailedCard],
})
export class Dashboard implements OnInit {
  connectedUser!: AppUser;
  adverts = signal<Advert[]>([]);
  loading = signal(false);
  errorMessage = signal('');

  newAdvert!: Advert;

  constructor(
    private advertService: AdvertService,
    private cookieService: CookieService
  ) {}

  ngOnInit(): void {
    const userCookie = this.cookieService.get('connected_user');
    if (userCookie) {
      this.connectedUser = JSON.parse(userCookie);
    } else {
      this.errorMessage.set('Utilisateur non connecté.');
      return;
    }

    this.newAdvert = {
      title: '',
      desc: '',
      publisher: { id: this.connectedUser.id } as AppUser,
      advertType: 'OTHER',
    };

    this.loadUserAdverts();
  }

  loadUserAdverts(): void {
    this.loading.set(true);
    this.advertService.getAll().subscribe({
      next: (data) => {
        console.log(data);
        const userAdverts = data.filter(
          (advert) => advert.publisher?.id === this.connectedUser.id
        );
        this.adverts.set(userAdverts);
        this.loading.set(false);
      },
      error: (err) => {
        console.error(err);
        this.errorMessage.set('Erreur lors du chargement des annonces');
        this.loading.set(false);
      },
    });
  }
}
