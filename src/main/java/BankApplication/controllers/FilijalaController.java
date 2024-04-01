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

import BankApplication.models.Banka;
import BankApplication.models.Filijala;
import BankApplication.services.BankaService;
import BankApplication.services.FilijalaService;

@RestController
public class FilijalaController {

	@Autowired
	private FilijalaService service;
	
	@Autowired
	private BankaService bankaService;
	
	
	@GetMapping("/filijala")
	public List<Filijala> getAllFilijala(){
		return service.getAll();
	}
	
	@GetMapping("/filijala/id/{id}")
	public ResponseEntity<?> getFilijalaById(@PathVariable int id){
		Optional<Filijala> filijala = service.findById(id);
		if(filijala.isPresent()) {
			return ResponseEntity.ok(filijala.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " does not exist!");
	}
	
	@GetMapping("/filijala/banka/{foreignKey}")
	public ResponseEntity<?> getFilijalaByBanka(@PathVariable int foreignKey){
		Optional<Banka> banka = bankaService.findById(foreignKey);
		if(banka.isPresent()) {
			List<Filijala> filijala = service.getFilijalaByForeignKey(banka.get());
			if(filijala.isEmpty()) {
				return ResponseEntity.status(404).body("Resources with foreign key: " + foreignKey
						+ " do not exist!");
			}else {
				return ResponseEntity.ok(filijala);
			}
		}
		return ResponseEntity.status(400).body("Invalid foreign key!");
	}
	
	@GetMapping("/filijala/adresa/{adresa}")
	public ResponseEntity<?> getFilijalaByAdresa(@PathVariable String adresa){
		List<Filijala> filijala = service.getFilijalaByAdresa(adresa);
		if(filijala.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with adress: " + adresa + " do not exist!");
		}
		return ResponseEntity.ok(filijala);
	}
	
	@GetMapping("/filijala/posedujeSef/{posedujeSef}")
	public ResponseEntity<?> getFilijalaByPosedujeSef(@PathVariable boolean posedujeSef){
		List<Filijala> filijala = service.getFilijalaByPosedujeSef(posedujeSef);
		if(filijala.isEmpty()) {
			return ResponseEntity.status(404).body("Resources with boss: " + posedujeSef + " do not exist!");
		}
		return ResponseEntity.ok(filijala);
	}
	
	@PostMapping("/filijala")
	public ResponseEntity<?> createFilijala(@RequestBody Filijala filijala){
		if(service.existsById(filijala.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Filijala savedFilijala = service.create(filijala);
		URI uri = URI.create("/filijala/id/" + savedFilijala.getId());
		return ResponseEntity.created(uri).body(savedFilijala);
	}
	
	@PutMapping("/filijala/id/{id}")
	public ResponseEntity<?> updateFilijala(@RequestBody Filijala filijala, @PathVariable int id){
		Optional<Filijala> updatedFilijala = service.update(filijala, id);
		if(updatedFilijala.isPresent()) {
			return ResponseEntity.ok(updatedFilijala.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/filijala/id/{id}")
	public ResponseEntity<?> deleteFilijala(@PathVariable int id ){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
	
	
	
}
