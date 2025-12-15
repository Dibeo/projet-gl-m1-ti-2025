import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Advert } from '../../core/services/advert.service';

@Component({
  selector: 'app-advert-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './advert-card.html',
  styleUrls: ['./advert-card.css'],
})
export class AdvertCard {
  @Input() advert!: Advert;
}
