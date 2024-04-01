package BankApplication.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BankApplication.models.Banka;
import BankApplication.models.Filijala;
import BankApplication.repositories.FilijalaRepository;
import BankApplication.services.FilijalaService;

@Component
public class FilijalaServiceImpl implements FilijalaService {

	@Autowired
	private FilijalaRepository repo;
	@Override
	public List<Filijala> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		// TODO Auto-generated method stub
		return repo.existsById(id);
	}

	@Override
	public Optional<Filijala> findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Filijala create(Filijala t) {
		// TODO Auto-generated method stub
		return repo.save(t);
	}

	@Override
	public Optional<Filijala> update(Filijala t, int id) {
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
	public List<Filijala> getFilijalaByForeignKey(Banka banka) {
		// TODO Auto-generated method stub
		return repo.findByBanka(banka);
	}

	@Override
	public List<Filijala> getFilijalaByAdresa(String adresa) {
		// TODO Auto-generated method stub
		return repo.findByAdresaContainingIgnoreCase(adresa);
	}

	@Override
	public List<Filijala> getFilijalaByPosedujeSef(boolean posedujeSef) {
		// TODO Auto-generated method stub
		return repo.findByPosedujeSef(posedujeSef);
	}

}
