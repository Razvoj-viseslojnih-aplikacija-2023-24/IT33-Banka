import { BankaService } from './../../services/banka.service';
import { Banka } from './../../models/banka';
import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-bankadialog',
  templateUrl: './bankadialog.component.html',
  styleUrls: ['./bankadialog.component.css']
})
export class BankadialogComponent {
  flag!: number;

  constructor(
    public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<Banka>,
    @Inject (MAT_DIALOG_DATA) public data: Banka,
    public service:BankaService
  ){}

  public add(){
    this.service.createBanka(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno dodata banka sa nazivom: ${this.data.naziv}`,
                            `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno dodavanje`, `Zatvori`, {duration:1000});
    }
  }

  public update(){
    this.service.updateBanka(this.data).subscribe(
      (data) => {
        this.snackBar.open(`Uspesno azurirana banka sa nazivom: ${this.data.naziv}`,
                            `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno azuriranje`, `Zatvori`, {duration:1000});
    }
  }

  public delete(){
    this.service.deleteBanka(this.data.id).subscribe(
      (data) => {
        this.snackBar.open(`${data}`,
                            `U redu`, {duration:2500});
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
      this.snackBar.open(`Neuspesno brisanje`, `Zatvori`, {duration:1000});
    }
  }

  public cancel(){
    this.dialogRef.close();
    this.snackBar.open(`Odustali ste od izmena`, `Zatvori`, {duration:2500});
  }
}
