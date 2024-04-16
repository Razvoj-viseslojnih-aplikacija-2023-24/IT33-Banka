package BankApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import BankApplication.models.Filijala;
import BankApplication.models.Banka;


public interface FilijalaRepository extends JpaRepository<Filijala, Integer> {
	
	List<Filijala> findByBanka (Banka banka);
	List<Filijala> findByAdresaContainingIgnoreCase (String adresa);
	List<Filijala> findByPosedujeSef (boolean posedujeSef);
}
