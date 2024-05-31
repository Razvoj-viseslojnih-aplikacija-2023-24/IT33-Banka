import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-korisnik-component',
  templateUrl: './korisnik-component.component.html',
  styleUrls: ['./korisnik-component.component.css'],
})
export class KorisnikComponentComponent implements OnInit, OnDestroy {
  displayedColumns = ['id', 'ime', 'prezime', 'maticniBroj', 'actions'];
  dataSource!: MatTableDataSource<KorisnikComponentComponent>;
  subscription!: Subscription;

  constructor(private service: KorisnikService, public dialog: MatDialog) {}
  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  loadData() {
    (this.subscription = this.service.getAllKorisnik().subscribe((data) => {
      this.dataSource = new MatTableDataSource(data);
    })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  public openDialog(
    flag: number,
    id?: number,
    ime?: string,
    prezime?: string,
    maticniBroj?: string
  ) {
    // const dialogRef = this.dialog.open(ArtiklDialogComponent, {data : {id, naziv, proizvodjac}});
    // dialogRef.componentInstance.flag = flag;
    // dialogRef.afterClosed().subscribe(
    //   (result) => {
    //     if(result == 1) {
    //       this.loadData();
    //     }
    //   }
    // )
  }
}
