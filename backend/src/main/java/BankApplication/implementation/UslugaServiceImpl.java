package BankApplication.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BankApplication.models.Filijala;
import BankApplication.models.Korisnik;
import BankApplication.models.Usluga;
import BankApplication.repositories.UslugaRepository;
import BankApplication.services.UslugaService;

@Component
public class UslugaServiceImpl implements UslugaService {

	@Autowired
	private UslugaRepository repo;
	@Override
	public List<Usluga> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public Optional<Usluga> findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Usluga create(Usluga t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public Optional<Usluga> update(Usluga t, int id) {
		// TODO Auto-generated method stub
		if(existsById(id)) {
			t.setId(id);
			return Optional.of(repo.save(t));
		}
		return Optional.empty();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
			repo.deleteById(id);
	}

	@Override
	public List<Usluga> getUslugaByNaziv(String naziv) {
		// TODO Auto-generated method stub
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

	@Override
	public List<Usluga> getUslugaByForeignKey(Filijala filijala) {
		// TODO Auto-generated method stub
		return repo.findByFilijala(filijala);
	}

	@Override
	public List<Usluga> getUslugaByForeignKey(Korisnik korisnik) {
		// TODO Auto-generated method stub
		return repo.findByKorisnik(korisnik);
	}

}
