package BankApplication.services;

import java.util.List;

import org.springframework.stereotype.Service;

import BankApplication.models.Banka;

@Service
public interface BankaService extends CrudService<Banka> {

	List<Banka> getBankaByNaziv (String naziv);
	List<Banka> getBankaByPib (int pib);

}
