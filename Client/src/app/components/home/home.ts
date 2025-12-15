import { Component, OnInit, effect, signal } from '@angular/core';
import { Advert, AdvertService } from '../../core/services/advert.service';
import { CommonModule } from '@angular/common';
import { AdvertCard } from '../advert-card/advert-card';

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
  imports: [CommonModule, AdvertCard],
  standalone: true
})
export class Home implements OnInit {
  // Signals pour l'asynchronisation
  adverts = signal<Advert[]>([]);
  loading = signal<boolean>(false);
  errorMessage = signal<string>('');

  constructor(private advertService: AdvertService) {}

  ngOnInit(): void {
    this.loadAdverts();
  }

  loadAdverts(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.advertService.getAll().subscribe({
      next: (data) => {
        // s'assurer que c'est un array
        this.adverts.set(Array.isArray(data) ? data : []);
        this.loading.set(false);
      },
      error: (err) => {
        console.error('Erreur chargement annonces', err);
        this.errorMessage.set('Erreur lors du chargement des annonces');
        this.adverts.set([]);
        this.loading.set(false);
      }
    });
  }
}
