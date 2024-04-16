package BankApplication.services;

import java.util.List;

import org.springframework.stereotype.Service;

import BankApplication.models.Banka;
import BankApplication.models.Filijala;


@Service
public interface FilijalaService extends CrudService<Filijala> {
	
	List<Filijala> getFilijalaByForeignKey (Banka banka);
	List<Filijala> getFilijalaByAdresa(String adresa);
	List<Filijala> getFilijalaByPosedujeSef (boolean posedujeSef);


}
