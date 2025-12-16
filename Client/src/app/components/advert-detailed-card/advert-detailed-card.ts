import { Component, Input, OnInit, signal } from '@angular/core';
import { Advert, AdvertService } from '../../core/services/advert.service';
import { Application, ApplicationService } from '../../core/services/application.service';
import { AppUserService } from '../../core/services/app-user.service';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

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

  constructor(
    private applicationService: ApplicationService,
    private advertService: AdvertService,
    private userService: AppUserService
  ) {}

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications(): void {
    if (!this.advert?.id) return;
    this.loadingApplications.set(true);

    this.applicationService.getAll().subscribe({
      next: (data) => {
        const filtered = data
          .filter((app) => app.advert.id === this.advert.id)
          .map(app => ({ ...app, applicant: app.applicant })); // Convert requester -> applicant
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

  updateStatus(newStatus: string): void {
    if (!this.advert.id) return;

    const updatedAdvert: Advert = { ...this.advert, advertStatus: newStatus };
    this.advertService.update(this.advert.id, updatedAdvert).subscribe({
      next: () => (this.advert.advertStatus = newStatus),
      error: (err) => console.error('Erreur mise à jour status', err),
    });
  }

  deleteAdvert(advertId: any) {
    Swal.fire({
      title: 'Voulez-vous vraiment supprimer cet advert ?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui, supprimer',
      cancelButtonText: 'Annuler',
    }).then((result) => {
      if (result.isConfirmed) {
        this.advertService.delete(advertId).subscribe({
          next: () => Swal.fire('Supprimé !', "L'advert a été supprimé.", 'success'),
          error: (err) => Swal.fire('Erreur', 'Impossible de supprimer cet advert.', 'error'),
        });
      }
    });
  }

  acceptApplication(app: Application) {
    this.applicationService.update(app.id!, { ...app, applicationStatus: 'ACCEPTED' }).subscribe({
      next: () => {
        Swal.fire('✅ Accepté', `${app.applicant?.firstName} a été accepté.`, 'success');
        this.loadApplications(); // rafraîchit la liste
      },
      error: (err) => Swal.fire('Erreur', 'Impossible d’accepter cette candidature.', 'error'),
    });
  }

  rejectApplication(app: Application) {
    this.applicationService.update(app.id!, { ...app, applicationStatus: 'REJECTED' }).subscribe({
      next: () => {
        Swal.fire('❌ Refusé', `${app.applicant?.firstName} a été refusé.`, 'info');
        this.loadApplications(); // rafraîchit la liste
      },
      error: (err) => Swal.fire('Erreur', 'Impossible de refuser cette candidature.', 'error'),
    });
  }
}
