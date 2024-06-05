import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilijaladialogComponent } from './filijaladialog.component';

describe('FilijaladialogComponent', () => {
  let component: FilijaladialogComponent;
  let fixture: ComponentFixture<FilijaladialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilijaladialogComponent]
    });
    fixture = TestBed.createComponent(FilijaladialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
