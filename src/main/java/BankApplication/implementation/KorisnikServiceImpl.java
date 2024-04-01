package BankApplication.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BankApplication.models.Korisnik;
import BankApplication.repositories.KorisnikRepository;
import BankApplication.services.KorisnikService;

@Component
public class KorisnikServiceImpl implements KorisnikService {

	@Autowired
	private KorisnikRepository repo;
	@Override
	public List<Korisnik> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public Optional<Korisnik> findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Korisnik create(Korisnik t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public Optional<Korisnik> update(Korisnik t, int id) {
		// TODO Auto-generated method stub
		if(existsById(id))
		{
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
	public List<Korisnik> getKorisnikByIme(String ime) {
		// TODO Auto-generated method stub
		return repo.findByImeContainingIgnoreCase(ime);
	}

	@Override
	public List<Korisnik> getKorisnikByPrezime(String prezime) {
		// TODO Auto-generated method stub
		return repo.findByPrezimeContainingIgnoreCase(prezime);
	}

	@Override
	public List<Korisnik> getKorisnikByMaticniBroj(String maticniBroj) {
		// TODO Auto-generated method stub
		return repo.findBymaticniBroj(maticniBroj);
	}

}
