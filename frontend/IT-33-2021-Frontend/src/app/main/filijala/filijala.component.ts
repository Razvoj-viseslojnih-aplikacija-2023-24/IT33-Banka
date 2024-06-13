import { Banka } from './../../models/banka';
import { Filijala } from './../../models/filijala';
import { FilijalaService } from './../../services/filijala.service';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { FilijaladialogComponent } from 'src/app/dialog/filijaladialog/filijaladialog.component';

@Component({
  selector: 'app-filijala',
  templateUrl: './filijala.component.html',
  styleUrls: ['./filijala.component.css'],
})
export class FilijalaComponent implements OnInit, OnDestroy {
  displayedColumns = [
    'id',
    'adresa',
    'brojPultova',
    'posedujeSef',
    'banka',
    'actions',
  ];
  dataSource!: MatTableDataSource<Filijala>;
  subscription!: Subscription;

  parentSelectedFilijala!: Filijala;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;
  constructor(private service: FilijalaService, public dialog: MatDialog) {}

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public selectRow(row: Filijala) {
    this.parentSelectedFilijala = row;
  }

  public loadData() { 
    (this.subscription = this.service.getAllFilijalas().subscribe((data) => {
      console.log(JSON.stringify(data));
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  public openDialog(
    flag: number,
    id?: number,
    adresa?: string,
    brojPultova?: number,
    posedujeSef?: boolean,
    banka?: Banka
  ) {
    const dialogRef = this.dialog.open(FilijaladialogComponent, {
      data: { id, adresa, brojPultova, posedujeSef, banka },
    });
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe((result) => {
      if (result == 1) {
        this.loadData();
      }
    });
  }

  public applyFilter(filter: any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }
}
