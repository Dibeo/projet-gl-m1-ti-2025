import { Component, Input, OnInit, signal } from '@angular/core';
import { Advert } from '../../core/services/advert.service';
import { Application, ApplicationService } from '../../core/services/application.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-advert-detailed-card',
  templateUrl: './advert-detailed-card.html',
  styleUrls: ['./advert-detailed-card.css'],
  standalone: true,
  imports: [CommonModule],
})
export class AdvertDetailedCard implements OnInit {
  @Input() advert!: Advert;

  applications = signal<Application[]>([]);
  loadingApplications = signal(false);
  error = signal('');

  constructor(private applicationService: ApplicationService) {}

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications(): void {
    if (!this.advert?.id) return;
    this.loadingApplications.set(true);

    this.applicationService.getAll().subscribe({
      next: (data) => {
        const filtered = data.filter(app => app.advertId === this.advert.id);
        this.applications.set(filtered);
        this.loadingApplications.set(false);
      },
      error: (err) => {
        console.error(err);
        this.error.set('Erreur lors du chargement des candidatures');
        this.loadingApplications.set(false);
      },
    });
  }
}
