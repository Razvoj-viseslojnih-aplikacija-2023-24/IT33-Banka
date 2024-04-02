package BankApplication.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import BankApplication.models.Banka;
import BankApplication.services.BankaService;


@RestController
public class BankaController {

	
	@Autowired
	private BankaService service;
	
	@GetMapping("/banka")
	public List<Banka> getAllBanka(){
		return service.getAll();
	}
	
	@GetMapping("/banka/id/{id}")
	public ResponseEntity<?> getBankaById(@PathVariable int id){
		Optional<Banka> banka = service.findById(id);
		if(banka.isPresent()) {
			return ResponseEntity.ok(banka.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID:" + 
		id + " does not exist.");
	}
	
	
	@GetMapping("/banka/naziv/{naziv}")
	public ResponseEntity<?> getBankaByNaziv(@PathVariable String naziv){
		List<Banka> banka = service.getBankaByNaziv(naziv);
		if(banka.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources"
					+ " with Naziv: " + naziv + " could not be found.");
		}
		return ResponseEntity.ok(banka);
	}
	
	@GetMapping("/banka/pib/{pib}")
	public ResponseEntity<?> getBankaByPib(@PathVariable int pib){
		List<Banka> banka = service.getBankaByPib(pib);
		if(banka.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resources" + " with PIB: " + pib + " could not be found.");
		}
		return ResponseEntity.ok(banka);
	}
	
	@PostMapping("/banka")
	public ResponseEntity<?> createBanka(@RequestBody Banka banka){
		if(service.existsById(banka.getId())) {
			return ResponseEntity.status(409).body("Resource already exists!");
		}
		Banka savedBanka = service.create(banka);
		URI uri = URI.create("/banka/" + savedBanka.getId());
		return ResponseEntity.created(uri).body(savedBanka);
	}
	
	@PutMapping("/banka/id/{id}")
	public ResponseEntity<?> updateBanka(@RequestBody Banka banka, @PathVariable int id){
		Optional<Banka> updatedBanka = service.update(banka, id);
		if(updatedBanka.isPresent()) {
			return ResponseEntity.ok(updatedBanka);
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" updated because it does not exist!");
	}
	
	@DeleteMapping("/banka/id/{id}")
	public ResponseEntity<?> deleteBanka(@PathVariable int id ){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: " + id + " has been deleted!");
		}
		return ResponseEntity.status(404).body("Resource with requested ID: " + id + " could not be" + 
				" deleted because it does not exist!");
	}
}
