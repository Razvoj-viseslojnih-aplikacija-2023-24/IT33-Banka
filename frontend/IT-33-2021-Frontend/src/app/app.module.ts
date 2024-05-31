import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar'
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatTableModule} from '@angular/material/table';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

import { FormsModule } from '@angular/forms';
import { HomeComponent } from './utility/home/home.component';
import { AboutComponent } from './utility/about/about.component';
import { AuthorComponent } from './utility/author/author.component';
import { BankaComponentComponent } from './main/banka-component/banka-component.component';
import { FilijalaComponentComponent } from './main/filijala-component/filijala-component.component';
import { KorisnikComponentComponent } from './main/korisnik-component/korisnik-component.component';
import { UslugaComponentComponent } from './main/usluga-component/usluga-component.component';

@NgModule({
  declarations: [
    AppComponent,
    BankaComponentComponent,
    FilijalaComponentComponent,
    KorisnikComponentComponent,
    UslugaComponentComponent,
    HomeComponent,
    AboutComponent,
    AuthorComponent
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
    MatButtonModule,
    MatToolbarModule,
    MatTableModule,
    MatSnackBarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
