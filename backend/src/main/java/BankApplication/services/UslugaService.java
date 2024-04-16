package BankApplication.services;

import java.util.List;

import org.springframework.stereotype.Service;

import BankApplication.models.Filijala;
import BankApplication.models.Korisnik;
import BankApplication.models.Usluga;


@Service
public interface UslugaService extends CrudService<Usluga> {

	List<Usluga> getUslugaByNaziv(String naziv);
	List<Usluga> getUslugaByForeignKey(Filijala filijala);
	List<Usluga> getUslugaByForeignKey(Korisnik korisnik);
}
