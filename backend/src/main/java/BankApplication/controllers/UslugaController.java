package BankApplication.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BankApplication.models.Filijala;
import BankApplication.models.Korisnik;
import BankApplication.models.Usluga;
import BankApplication.services.FilijalaService;
import BankApplication.services.KorisnikService;
import BankApplication.services.UslugaService;


@RestController
public class UslugaController {

	@Autowired
	private UslugaService service;
	
	@Autowired
	private FilijalaService filijalaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@GetMapping("/usluga")
	public List<Usluga> getAllUsluga(){
		return service.getAll();
	}
	
	@GetMapping("/usluga/id/{id}")
	public ResponseEntity<?> getUsluga(@PathVariable int id){
		Optional<Usluga> usluga = service.findById(id);
		if(usluga.isPresent()) {
			return ResponseEntity.ok(usluga.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/usluga/filijala/{foreignKey}")
	public ResponseEntity<?> getUslugaByFilijala(@PathVariable int foreignKey){
		Optional<Filijala> filijala = filijalaService.findById(foreignKey);
		if(filijala.isPresent()) {
			List<Usluga> usluga = service.getUslugaByForeignKey(filijala.get());
			if(usluga.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(usluga);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
	
	@GetMapping("/usluga/korisnik/{foreignKey}")
	public ResponseEntity<?> getUslugaByKorisnik(@PathVariable int foreignKey){
		Optional<Korisnik> korisnik = korisnikService.findById(foreignKey);
		if(korisnik.isPresent()) {
			List<Usluga> usluga = service.getUslugaByForeignKey(korisnik.get());
			if(usluga.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(usluga);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
	
	@GetMapping("/usluga/naziv/{naziv}")
	public ResponseEntity<?> getUslugaByNaziv(@PathVariable String naziv){
		List<Usluga> usluga = service.getUslugaByNaziv(naziv);
		if(usluga.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with Cena: " + naziv + " do not exist!");
		}
		return ResponseEntity.ok(usluga);
	}
	
	@PostMapping("/usluga")
	public ResponseEntity<?> createUsluga(@RequestBody Usluga usluga){
		if(service.existsById(usluga.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Usluga savedUsluga = service.create(usluga);
		URI uri = URI.create("/usluga/id/" + savedUsluga.getId());
		return ResponseEntity.created(uri).body(savedUsluga);
	}
	
	@PutMapping("/usluga/id/{id}")
	public ResponseEntity<?> updateBankService(@RequestBody Usluga usluga, @PathVariable int id){
		Optional<Usluga> updatedUsluga = service.update(usluga, id);
		if(updatedUsluga.isPresent()) {
			return ResponseEntity.ok(updatedUsluga.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/usluga/id/{id}")
	public ResponseEntity<?> deleteUsluga(@PathVariable int id ){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
}
