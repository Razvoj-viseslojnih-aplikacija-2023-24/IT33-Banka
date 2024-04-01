package BankApplication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import BankApplication.models.Korisnik;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KorisnikControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	
	void createHighestId()
	{
		ResponseEntity<List<Korisnik>> response = template.exchange(
				"/korisnik", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Korisnik>>() {});
		
		List<Korisnik> list = response.getBody();
		for(int i=0; i < list.size(); i++)
		{
			if(highestId <= list.get(i).getId()) {
				highestId = list.get(i).getId() + 1;
			}
		}
	}
	
	void getHighestId() {
		createHighestId();
		highestId--;
	}
	
	@Test
	@Order(1)
	void TestGetAllKorisnik() {
		ResponseEntity<List<Korisnik>> response = template.exchange(
				"/korisnik", HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Korisnik>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Korisnik> korisnici = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!korisnici.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetKorisnikById() {
		int id = 1;
		ResponseEntity<Korisnik> response = template.exchange(
				"/korisnik/id/" + id, HttpMethod.GET, null, Korisnik.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
	
	}
	
	@Test
	@Order(3)
	void testGetKorisnikByIme() {
		String ime = "Neda";
		ResponseEntity<List<Korisnik>> response = template.exchange(
				"/korisnik/ime/" + ime, HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Korisnik>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Korisnik> korisnici = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(korisnici.get(0));
		for(Korisnik k : korisnici) {
			assertTrue(k.getIme().contains(ime));

			// U slucaju brojcane vrednosti
			// assertTrue(a.getVrednost() < predefinisanaVrednost)
			// ILI
			// assertTrue(a.getVrednost() > predefinisanaVrednost)
			
			//U slucaju boolean vrednosti
			// assertTrue(a.getBooleanVrednost());
		}
	}
}
