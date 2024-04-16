package BankApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import BankApplication.models.*;

public interface UslugaRepository extends JpaRepository<Usluga, Integer> {

	List<Usluga> findByNazivContainingIgnoreCase (String naziv);
	List<Usluga> findByFilijala (Filijala filijala );
	List<Usluga> findByKorisnik(Korisnik korisnik);
}
