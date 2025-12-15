import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Advert } from '../../core/services/advert.service';
import { openPaywall } from '../../core/services/payload.service';

@Component({
  selector: 'app-advert-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './advert-card.html',
  styleUrls: ['./advert-card.css'],
})
export class AdvertCard {
  @Input() advert!: Advert;
  imageUrl!: string;

  ngOnInit(): void {
    this.imageUrl = `https://picsum.photos/400/250?random=${this.advert.id}`;
  }

  onApplyClick() {
    openPaywall();
  }
}
