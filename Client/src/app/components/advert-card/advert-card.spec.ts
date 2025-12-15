import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertCard } from './advert-card';

describe('AdvertCard', () => {
  let component: AdvertCard;
  let fixture: ComponentFixture<AdvertCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvertCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvertCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
