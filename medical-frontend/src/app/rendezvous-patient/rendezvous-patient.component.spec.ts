import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RendezvousPatientComponent } from './rendezvous-patient.component';

describe('RendezvousPatientComponent', () => {
  let component: RendezvousPatientComponent;
  let fixture: ComponentFixture<RendezvousPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RendezvousPatientComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RendezvousPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
