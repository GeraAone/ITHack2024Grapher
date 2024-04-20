import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandardImagesTableComponent } from './standard-images-table.component';

describe('StandardImagesTableComponent', () => {
  let component: StandardImagesTableComponent;
  let fixture: ComponentFixture<StandardImagesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StandardImagesTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StandardImagesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
