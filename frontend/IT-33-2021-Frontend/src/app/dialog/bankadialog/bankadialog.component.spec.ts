import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankadialogComponent } from './bankadialog.component';

describe('BankadialogComponent', () => {
  let component: BankadialogComponent;
  let fixture: ComponentFixture<BankadialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BankadialogComponent]
    });
    fixture = TestBed.createComponent(BankadialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
