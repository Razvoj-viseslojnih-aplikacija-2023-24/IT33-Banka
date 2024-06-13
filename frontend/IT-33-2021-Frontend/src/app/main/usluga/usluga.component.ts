import { Filijala } from './../../models/filijala';
import { UslugadialogComponent } from './../../dialog/uslugadialog/uslugadialog.component';
import { UslugaService } from './../../services/usluga.service';
import { Usluga } from './../../models/usluga';
import { Korisnik } from './../../models/korisnik';
import { Component, Input, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-usluga',
  templateUrl: './usluga.component.html',
  styleUrls: ['./usluga.component.css'],
})
export class UslugaComponent {
  dataSource!: MatTableDataSource<Usluga>;
  displayedColumns = [
    'id',
    'naziv',
    'opisUsluge',
    'datumUgovora',
    'provizija',
    'korisnik',
    'actions',
  ];
  subscription!: Subscription;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @Input() childSelectedFilijala!: Filijala;
  constructor(private uslugaService: UslugaService, public dialog: MatDialog) {}

  ngOnChanges(changes: SimpleChanges): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    (this.subscription = this.uslugaService
      .getUslugaByFilijala(this.childSelectedFilijala.id)
      .subscribe((data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
      })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  public openDialog(
    flag: number,
    id?: number,
    naziv?: string,
    opisUsluge?: string,
    datumUgovora?: Date,
    provizija?: number,
    korisnik?: Korisnik,
    filijala?: Filijala
  ): void {
    const dialogRef = this.dialog.open(UslugadialogComponent, {
      data: { id, naziv, opisUsluge, datumUgovora, provizija, korisnik },
    });
    dialogRef.componentInstance.flag = flag;
    dialogRef.componentInstance.data.filijala = this.childSelectedFilijala;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
  }
}
