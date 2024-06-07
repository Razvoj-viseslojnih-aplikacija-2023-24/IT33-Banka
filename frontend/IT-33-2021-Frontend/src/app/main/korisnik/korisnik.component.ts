import { KorisnikdialogComponent } from './../../dialog/korisnikdialog/korisnikdialog.component';
import { KorisnikService } from './../../services/korisnik.service';
import { Korisnik } from './../../models/korisnik';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-korisnik',
  templateUrl: './korisnik.component.html',
  styleUrls: ['./korisnik.component.css']
})
export class KorisnikComponent implements OnInit, OnDestroy{
  displayedColumns = ['id', 'ime', 'prezime', 'maticniBroj' , 'actions'];
  dataSource!:MatTableDataSource<Korisnik>;
  subscription!:Subscription;

  @ViewChild(MatSort, {static:false}) sort!:MatSort;
  @ViewChild(MatPaginator, {static:false}) paginator!:MatPaginator;
  constructor(private service:KorisnikService, public dialog:MatDialog){}

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  public loadData() {
    this.subscription = this.service.getAllKorisnik().subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }
    ),
    (error:Error) => {
      console.log(error.name + ' ' + error.message);
    }
  }

  public openDialog(flag:number, id?:number, ime?:string, prezime?: string, maticniBroj?:string) {
    const dialogRef = this.dialog.open(KorisnikdialogComponent, {data : {id, ime, prezime, maticniBroj}});
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(
      (result) => {
        if(result == 1) {
          this.loadData();
        }
      }
    )
  }

  public applyFilter(filter:any) {
    filter = filter.target.value;
    filter = filter.trim();
    filter = filter.toLocaleLowerCase();
    this.dataSource.filter = filter;
  }

  // public sortByName(column: keyof Korisnik) {
  //   this.dataSource.data = this.dataSource.data.sort((a, b) => a[column].localeCompare(b[column]));
  // }
  
  // public sortbyPreyime(column: keyof Korisnik) {
  //   this.dataSource.data = this.dataSource.data.sort((a, b) => a[column].localeCompare(b[column]));
  // }

}

