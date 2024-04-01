package BankApplication.services;

import java.util.List;

import org.springframework.stereotype.Service;

import BankApplication.models.Korisnik;

@Service
public interface KorisnikService extends CrudService<Korisnik> {
	
	List<Korisnik> getKorisnikByIme(String ime); 
	List<Korisnik> getKorisnikByPrezime(String prezime);
	List<Korisnik> getKorisnikByMaticniBroj (String maticniBroj);
	
	
}