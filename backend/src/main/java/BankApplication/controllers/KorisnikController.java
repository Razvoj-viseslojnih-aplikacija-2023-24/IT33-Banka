package BankApplication.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BankApplication.models.Korisnik;
import BankApplication.services.KorisnikService;

@CrossOrigin
@RestController
public class KorisnikController {

	@Autowired
	private KorisnikService service;
	
	@GetMapping("/korisnik")
	public List<Korisnik> getAllKorisnik()
	{
		return service.getAll();
	}
	
	
	@GetMapping("/korisnik/id/{id}")
	public ResponseEntity<?> getKorisnikById(@PathVariable int id){
		Optional<Korisnik> korisnik = service.findById(id);
		if(korisnik.isPresent()) {
			return ResponseEntity.ok(korisnik.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID:" + 
		id + " does not exist.");
	}
	
	@GetMapping("/korisnik/ime/{ime}")
	public ResponseEntity<?> getKorisnikByIme(@PathVariable String ime){
		List<Korisnik> korisnik = service.getKorisnikByIme(ime);
		if(korisnik.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources"
					+ " with name: " + ime + " could not be found.");
		}
		return ResponseEntity.ok(korisnik);
	}
	
	@GetMapping("/korisnik/prezime/{prezime}") 
	public ResponseEntity<?> getKorisnikByPrezime(@PathVariable String prezime){
		List<Korisnik> korisnik = service.getKorisnikByPrezime(prezime);
		if(korisnik.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources"
					+ " with surname: " + prezime + " could not be found.");
		}
		return ResponseEntity.ok(korisnik);
	}
	
	@GetMapping("/korisnik/maticniBroj/{maticniBroj}") 
	public ResponseEntity<?> getKorisnikByMaticniBroj(@PathVariable String maticniBroj){
		List<Korisnik> korisnik = service.getKorisnikByMaticniBroj(maticniBroj);
		if(korisnik.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources"
					+ " with identification number: " + maticniBroj + " could not be found.");
		}
		return ResponseEntity.ok(korisnik);
	}
	
	@PostMapping("/korisnik")
	public ResponseEntity<?> createKorisnik(@RequestBody Korisnik korisnik){
		if(service.existsById(korisnik.getId())) {
			return ResponseEntity.status(409).body("Resource with" +
		" inserted values already exists.");
		} 
		Korisnik savedKorisnik = service.create(korisnik);
		URI uri = URI.create("/korisnik/" + savedKorisnik.getId());
		return ResponseEntity.created(uri).body(savedKorisnik);
	}
	
	@PutMapping("/korisnik/id/{id}")
	public ResponseEntity<?> updateKorisnik(@RequestBody Korisnik korisnik, @PathVariable int id){
		Optional<Korisnik> updatedKorisnik = service.update(korisnik, id);
		if(updatedKorisnik.isPresent()) {
			return ResponseEntity.ok(updatedKorisnik);
		} 
		return ResponseEntity.status(404).body("Resource with requested ID: " +
		+ id + " cannont be updated as it doesn't exist.");
	}
	
	@DeleteMapping("/korisnik/id/{id}")
	public ResponseEntity<?> deletedKorisnik(@PathVariable int id) {
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id +
					"has been deleted.");
		}
		return ResponseEntity.status(404).body("Resourse with requested ID: " +
		" cannont be deleted as it doesn't exist.");
				
	}
}
