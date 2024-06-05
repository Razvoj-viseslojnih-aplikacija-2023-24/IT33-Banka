import { FilijalaService } from './../../services/filijala.service';
import { BankaService } from './../../services/banka.service';
import { Filijala } from './../../models/filijala';
import { Banka } from './../../models/banka';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-filijaladialog',
  templateUrl: './filijaladialog.component.html',
  styleUrls: ['./filijaladialog.component.css']
})
export class FilijaladialogComponent implements OnInit{
  flag!:number;
  banka!:Banka[];

  constructor(
    public snackBar:MatSnackBar,
    public dialogRef:MatDialogRef<Filijala>,
    @Inject (MAT_DIALOG_DATA) public data: Filijala,
    public service:FilijalaService,
    public bankaService:BankaService
  ){}

  ngOnInit(): void {
    this.bankaService.getAllBanks().subscribe(
      (data) => {
        this.banka = data;
      }
    )
  }

public compare(a:any, b:any) {
  return a.id == b.id;
}

public add() {
  this.service.createFilijala(this.data).subscribe(
    (data) => {
      this.snackBar.open(`Uspešno dodata filijala sa ID: ${data.id}`, `U redu`, {duration:2500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open(`Neuspešno dodavanje`, `Zatvori`, {duration:2500});
  }
}

public update() {
  this.service.updateFilijala(this.data).subscribe(
    (data) => {
      this.snackBar.open(`Filijala sa ID: ${data.id} je uspešno ažurirana`, `U redu`, {duration:2500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open(`Neuspešno ažuriranje`, `Zatvori`, {duration:2500});
  }
}

public delete() {
  this.service.deleteFilijala(this.data.id).subscribe(
    (data) => {
      this.snackBar.open(`Filijala sa ID: ${data.id} je uspešno obrisana`, `U redu`, {duration:2500});
    }
  ),
  (error:Error) => {
    console.log(error.name + ' ' + error.message);
    this.snackBar.open(`Neuspešno brisanje`, `Zatvori`, {duration:2500});
  }
}

public cancel() {
  this.dialogRef.close();
  this.snackBar.open(`Odustali ste od izmena!`, `Zatvori`, {duration:2500});
}


}