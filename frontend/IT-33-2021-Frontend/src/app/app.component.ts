// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.css']
// })
// export class AppComponent {
//   title = 'IT-33-2021-Frontend';
// }
import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'IT-33-2021-Frontend';

  // Promenljive za praćenje statusa hover-a
  bankaHovered = false;
  filijalaHovered = false;
  korisnikHovered = false;
  oNamaHovered = false;
  pocetnaHovered = false;
  autorHovered = false;

  // Metode za hvatanje događaja miša
  @HostListener('mouseenter', ['$event.target'])
  onHover(target: any): void {
    if (target.tagName === 'A') {
      switch (target.getAttribute('routerLink')) {
        case '/banka':
          this.bankaHovered = true;
          break;
        case '/filijala':
          this.filijalaHovered = true;
          break;
        case '/korisnik':
          this.korisnikHovered = true;
          break;
        case '/about':
          this.oNamaHovered = true;
          break;
        case '/home':
          this.pocetnaHovered = true;
          break;
        case '/author':
          this.autorHovered = true;
          break;
        default:
          break;
      }
    }
  }

  @HostListener('mouseleave')
  onMouseLeave(): void {
    // Resetovanje promenljivih kada miš napusti element
    this.bankaHovered = false;
    this.filijalaHovered = false;
    this.korisnikHovered = false;
    this.oNamaHovered = false;
    this.pocetnaHovered = false;
    this.autorHovered = false;
  }
}
