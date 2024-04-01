package BankApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import BankApplication.models.Banka;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Banka>> response = template.exchange(
				"/banka", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Banka>>() {});
		
		List<Banka> list = response.getBody();
		for(int i=0; i < list.size(); i++) {
			if(highestId <= list.get(i).getId()){
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
	void testGetAllBanka() {
		ResponseEntity<List<Banka>> response = template.exchange(
				"/banka", HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Banka>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Banka> banka = response.getBody();
		
		assertEquals(200, statusCode);
		assertTrue(!banka.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetBankById() {
		int id = 1;
		ResponseEntity<Banka> response = template.exchange(
				"/banka/id/" + id, HttpMethod.GET, null, Banka.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertEquals(id, response.getBody().getId());
	
	}
	
	@Test
	@Order(3)
	void testGetBankaByPib() {
		int pib = 1208002;
		ResponseEntity<List<Banka>> response = template.exchange(
				"/banka/pib/" + pib, HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Banka>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Banka> banka = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(banka.get(0));
		for(Banka b : banka) {
			assertTrue(b.getPib()== pib);
			
		}
	}
	
	@Test
	@Order(4)
	void testgetBankaByNaziv() {
		String naziv = "Halk";
		ResponseEntity<List<Banka>> response = template.exchange(
				"/banka/naziv/" + naziv, HttpMethod.GET, null, new 
					ParameterizedTypeReference<List<Banka>>() {});
		
		int statusCode = response.getStatusCode().value();
		List<Banka> banka = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(banka.get(0));
		for(Banka b : banka) {
			assertTrue(b.getNaziv().contains(naziv));

		}
	}
	
	@Test
	@Order(5)
	void testCreateBanka() {
		Banka banka = new Banka();
		banka.setNaziv("Test name");
		banka.setKontakt("Test contact");
		banka.setPib(3456098);
		
		HttpEntity<Banka> entity = new HttpEntity<Banka>(banka);
		createHighestId();
		
		ResponseEntity<Banka> response = template.exchange(
				"/banka", HttpMethod.POST, entity, Banka.class);
		int statusCode = response.getStatusCode().value();
		
		assertEquals(201, statusCode);
		assertEquals("/banka/" + highestId, response.getHeaders().getLocation().getPath());
		assertEquals(banka.getNaziv(), response.getBody().getNaziv());
		assertEquals(banka.getKontakt(), response.getBody().getKontakt());
		assertEquals(banka.getPib(), response.getBody().getPib());
	}
	

	@Test
	@Order(6)
	void TestUpdateBank() {
		Banka banka = new Banka();
		banka.setNaziv("Test name");
		banka.setKontakt("Test contact");
		banka.setPib(3456098);
		
		HttpEntity<Banka> entity = new HttpEntity<Banka>(banka);
		getHighestId();
		
		ResponseEntity<Banka> response = template.exchange(
				"/banka/id/" + highestId, HttpMethod.PUT, entity, Banka.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody() instanceof Banka);
		assertEquals(banka.getNaziv(), response.getBody().getNaziv());
		assertEquals(banka.getKontakt(), response.getBody().getKontakt());
		assertEquals(banka.getPib(), response.getBody().getPib());
			
	
	}
	
	@Test
	@Order(7)
	void TestDeleteBankaById() {
		getHighestId();
		ResponseEntity<String> response = template.exchange(
				"/banka/id/" + highestId, HttpMethod.DELETE, null, String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been deleted."));
				
	}
}
