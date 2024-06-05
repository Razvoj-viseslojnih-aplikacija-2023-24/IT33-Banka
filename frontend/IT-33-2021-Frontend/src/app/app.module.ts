import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/compiler';
import { NO_ERRORS_SCHEMA } from '@angular/compiler';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutComponent } from './utility/about/about.component';
import { AuthorComponent } from './utility/author/author.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { MatSortModule } from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { KorisnikComponent } from './main/korisnik/korisnik.component';
import { BankaComponent } from './main/banka/banka.component';
import { FilijalaComponent } from './main/filijala/filijala.component';
import { KorisnikdialogComponent } from './dialog/korisnikdialog/korisnikdialog.component';
import { BankadialogComponent } from './dialog/bankadialog/bankadialog.component';
import { FilijaladialogComponent } from './dialog/filijaladialog/filijaladialog.component';
import { UslugadialogComponent } from './dialog/uslugadialog/uslugadialog.component';
import { UslugaComponent } from './main/usluga/usluga.component';
import { HomeComponent } from './utility/home/home.component';
//import { CardComponent } from './utility/card/card.component';

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    AuthorComponent,
    KorisnikComponent,
    BankaComponent,
    FilijalaComponent,
    KorisnikdialogComponent,
    BankadialogComponent,
    FilijaladialogComponent,
    UslugadialogComponent,
    UslugaComponent,
    HomeComponent,
    //CardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    MatExpansionModule,
    HttpClientModule,
    MatToolbarModule,
    MatTableModule,
    MatDialogModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatSortModule,
    MatPaginatorModule,
    MatCheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  // schemas: [
  //   CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA
  // ]
})
export class AppModule {}