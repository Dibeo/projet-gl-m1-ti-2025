import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAdvert } from './add-advert';

describe('AddAdvert', () => {
  let component: AddAdvert;
  let fixture: ComponentFixture<AddAdvert>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAdvert]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAdvert);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
