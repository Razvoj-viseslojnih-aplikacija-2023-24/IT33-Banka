import { BankaService } from './../../services/banka.service';
import { Banka } from './../../models/banka';
import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { BankadialogComponent } from 'src/app/dialog/bankadialog/bankadialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent {

  dataSource!: MatTableDataSource<Banka>;
  displayedColumns = ['id', 'naziv', 'kontakt', 'pib', 'actions'];
  subscription!: Subscription;

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;


  constructor(
    private bankaService: BankaService,
    public dialog: MatDialog
  ) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    (this.subscription = this.bankaService
      .getAllBanks()
      .subscribe((data) => {
        this.dataSource = new MatTableDataSource(data);
      })),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      };
  }

  public openDialog(
    flag: number,
    id?: number,
    naziv?: string,
    kontakt?: string,
    pib?:number
  ){
    const dialogRef = this.dialog.open(BankadialogComponent, {data: { id, naziv, kontakt, pib }, });
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

  public sortByNaziv(column: keyof Banka) {
    this.dataSource.data = this.dataSource.data.sort((a, b) => a[column].localeCompare(b[column]));
  }

  }



