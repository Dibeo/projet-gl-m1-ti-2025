import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Advert } from '../../core/services/advert.service';
import { ApplicationService, ApplicationDTO } from '../../core/services/application.service';
import { CookieService } from 'ngx-cookie-service';
import Swal from 'sweetalert2';
import { openPaywall } from '../../core/services/payload.service';

@Component({
  selector: 'app-advert-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './advert-card.html',
  styleUrls: ['./advert-card.css'],
})
export class AdvertCard implements OnInit {
  @Input() advert!: Advert;
  imageUrl!: string;
  isApplying = false;

  constructor(
    private applicationService: ApplicationService,
    private cookieService: CookieService
  ) {}

  ngOnInit(): void {
    this.imageUrl = `https://picsum.photos/400/250?random=${this.advert.id}`;
  }

  onApplyClick() {
    openPaywall();
  }

  onProposeClick() {
    const userCookie = this.cookieService.get('connected_user');

    if (!userCookie) {
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Vous devez être connecté pour proposer.',
      });
      openPaywall();
      return;
    }

    const user = JSON.parse(userCookie);

    if (!this.advert?.id) {
      console.error('Advert ID manquant');
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: "Impossible de récupérer l'annonce.",
      });
      return;
    }

    this.isApplying = true;

    // ✅ Utilisation du DTO attendu par le backend
    const applicationDto = {
      userId: user.id,
      advertId: this.advert.id
    };

    this.applicationService.create(applicationDto).subscribe({
      next: () => {
        this.isApplying = false;
        Swal.fire({
          icon: 'success',
          title: '✅ Candidature envoyée',
          text: 'Votre proposition a été envoyée avec succès !',
        });
      },
      error: (err) => {
        this.isApplying = false;
        console.error(err);
        Swal.fire({
          icon: 'error',
          title: '❌ Erreur',
          text: 'Une erreur est survenue lors de l’envoi de la candidature.',
        });
      }
    });
  }
}
