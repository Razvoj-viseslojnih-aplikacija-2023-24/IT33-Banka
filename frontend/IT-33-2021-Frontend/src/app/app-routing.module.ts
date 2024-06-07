import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './utility/home/home.component';
import { AboutComponent } from './utility/about/about.component';
import { AuthorComponent } from './utility/author/author.component';
import { KorisnikComponent } from './main/korisnik/korisnik.component';
import { BankaComponent } from './main/banka/banka.component';
import { FilijalaComponent } from './main/filijala/filijala.component';

const routes: Routes = [
  { path: 'banka', component: BankaComponent },
  { path: 'filijala', component: FilijalaComponent },
  { path: 'korisnik', component: KorisnikComponent },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'author', component: AuthorComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}