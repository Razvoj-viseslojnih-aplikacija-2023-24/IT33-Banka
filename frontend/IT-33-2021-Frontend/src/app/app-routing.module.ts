import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BankaComponentComponent } from './main/banka-component/banka-component.component';
import { FilijalaComponentComponent } from './main/filijala-component/filijala-component.component';
import { KorisnikComponentComponent } from './main/korisnik-component/korisnik-component.component';
import { UslugaComponentComponent } from './main/usluga-component/usluga-component.component';
import { HomeComponent } from './utility/home/home.component';
import { AboutComponent } from './utility/about/about.component';
import { AuthorComponent } from './utility/author/author.component';

const routes: Routes = [
  { path: 'banka', component: BankaComponentComponent },
  { path: 'filijala', component: FilijalaComponentComponent },
  { path: 'korisnik', component: KorisnikComponentComponent },
  { path: 'usluga', component: UslugaComponentComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'author', component: AuthorComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
