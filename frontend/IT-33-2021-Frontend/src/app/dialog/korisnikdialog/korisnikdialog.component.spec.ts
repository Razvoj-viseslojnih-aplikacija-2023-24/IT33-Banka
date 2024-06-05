import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KorisnikdialogComponent } from './korisnikdialog.component';

describe('KorisnikdialogComponent', () => {
  let component: KorisnikdialogComponent;
  let fixture: ComponentFixture<KorisnikdialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KorisnikdialogComponent]
    });
    fixture = TestBed.createComponent(KorisnikdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
