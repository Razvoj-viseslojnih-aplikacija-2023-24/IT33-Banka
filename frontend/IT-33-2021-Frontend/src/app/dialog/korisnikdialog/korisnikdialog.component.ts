import { KorisnikService } from './../../services/korisnik.service';
import { Korisnik } from './../../models/korisnik';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-korisnikdialog',
  templateUrl: './korisnikdialog.component.html',
  styleUrls: ['./korisnikdialog.component.css']
})
export class KorisnikdialogComponent {
  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Korisnik>,
    @Inject(MAT_DIALOG_DATA) public data: Korisnik,
    public service: KorisnikService
  ) {}

  public cancel() {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmena!', 'Zatvori', { duration: 1000 });
  }

  public add() {
    this.service.createKorisnik(this.data).subscribe({
      
      next: (data) => {
        console.log(data);
        this.snackBar.open(
          `Korisnik sa imenom ${data.ime} je dodat!`,
          'Zatvori!',
          {
            duration: 1000,
          }
        );
      },
      error: (err) => {
        console.error(err);
        this.snackBar.open('Neuspesno dodavanje!', 'Zatvori', {
          duration: 1000,
        });
      },
    });
  }

  public update() {
    this.service.updateKorisnik(this.data).subscribe({
      next: (data) => {
        this.snackBar.open(
          `Korisnik sa imenom ${data.ime} je uspesno azuriran!`,
          'Zatvori',
          {
            duration: 1000,
          }
        );
      },
      error: (err) => {
        console.error(err);
        this.snackBar.open('Neuspesno azuriranje!', 'Zatvori', {
          duration: 1000,
        });
      },
    });
  }

  public delete() {
    this.service.deleteKorisnik(this.data.id).subscribe(
      (data) => {
        this.snackBar.open("Uspešno brisanje", "U redu", {duration:3500});
      }
    ),
    (error: Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open("Neuspešno brisanje!", "Zatvori", {duration:3500});
    }
  }
}
