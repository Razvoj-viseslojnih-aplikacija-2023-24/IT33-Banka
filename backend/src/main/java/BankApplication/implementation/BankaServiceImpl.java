package BankApplication.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BankApplication.models.Banka;
import BankApplication.repositories.BankaRepository;
import BankApplication.services.BankaService;

@Component
public class BankaServiceImpl implements BankaService {

	@Autowired
	private BankaRepository repo;
	@Override
	public List<Banka> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public Optional<Banka> findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Banka create(Banka t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public Optional<Banka> update(Banka t, int id) {
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
	public List<Banka> getBankaByNaziv(String naziv) {
		// TODO Auto-generated method stub
		return repo.findByNazivContainingIgnoreCase(naziv);
	}

	@Override
	public List<Banka> getBankaByPib(int pib) {
		// TODO Auto-generated method stub
		return repo.findByPibEquals(pib);
	}

}
