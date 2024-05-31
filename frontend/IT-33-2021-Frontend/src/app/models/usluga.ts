import { Filijala } from "./filijala";
import { Korisnik } from "./korisnik";

export class Usluga{
    id!: number;
    naziv!: string;
    opisUsluge!: string;
    datumUgovora!: Date;
    provizija!: number;
    filijala!: Filijala;
    korisnik!: Korisnik;
}