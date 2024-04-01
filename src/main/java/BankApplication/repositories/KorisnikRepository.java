package BankApplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import BankApplication.models.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	List<Korisnik> findByImeContainingIgnoreCase(String ime);
	List<Korisnik> findByPrezimeContainingIgnoreCase(String prezime);
	List<Korisnik> findBymaticniBroj(String maticniBroj);
}
