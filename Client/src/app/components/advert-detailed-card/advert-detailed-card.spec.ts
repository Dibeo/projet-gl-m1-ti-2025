import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertDetailedCard } from './advert-detailed-card';

describe('AdvertDetailedCard', () => {
  let component: AdvertDetailedCard;
  let fixture: ComponentFixture<AdvertDetailedCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvertDetailedCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertDetailedCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
