import { Component, signal } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Advert, AdvertService } from '../../core/services/advert.service';
import { AppUser } from '../../core/services/app-user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { openPaywall } from '../../core/services/payload.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-advert',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-advert.html',
  styleUrl: './add-advert.css',
})
export class AddAdvert {
  connectedUser!: AppUser;
  adverts = signal<Advert[]>([]);
  loading = signal(false);
  errorMessage = signal('');

  newAdvert!: Advert;

  constructor(private advertService: AdvertService, private cookieService: CookieService) {}

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
        const userAdverts = data.filter((advert) => advert.publisher?.id === this.connectedUser.id);
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

  createAdvert(): void {
  if (!this.newAdvert.title || !this.newAdvert.desc) {
    Swal.fire({
      icon: 'warning',
      title: 'Champs manquants',
      text: 'Veuillez remplir le titre et la description',
    });
    return;
  }

  this.loading.set(true);

  this.advertService.create(this.newAdvert).subscribe({
    next: () => {
      this.loading.set(false);

      Swal.fire({
        icon: 'success',
        title: 'Succès 🎉',
        text: 'Annonce ajoutée avec succès',
        timer: 2000,
        showConfirmButton: false,
      });

      this.newAdvert = {
        title: '',
        desc: '',
        publisher: { id: this.connectedUser.id } as AppUser,
        advertType: 'OTHER',
      };

      this.loadUserAdverts();
    },
    error: (err) => {
      console.error(err);
      this.loading.set(false);

      Swal.fire({
        icon: 'error',
        title: 'Erreur ❌',
        text: 'Échec de la création de l’annonce',
      });

      this.errorMessage.set('Erreur lors de la création de l’annonce');
    },
  });
}


  onApplyClick() {
    openPaywall();
  }
}
