package BankApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import BankApplication.models.Filijala;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilijalaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;

	long largestId;
	
	public void createHighestId() {
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Filijala>>() {
				});
		ArrayList<Filijala> list = (ArrayList<Filijala>) response.getBody();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (largestId <= list.get(i).getId()) {
				largestId = list.get(i).getId() + 1;
			}
		}

	}
	

	private void getHighestId() {
		createHighestId();
		largestId--;
	}

	@Test
	@Order(1)
	void testGetAllFilijala() {
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Filijala>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(filijale);
	}
	
	@Test
	@Order(2)
	void testFindByIdFilijala() {
		long id = 1;
		ResponseEntity<Filijala> response = template.getForEntity("/filijala/id/" + id, Filijala.class);
		int statusCode = response.getStatusCode().value();
		Filijala filijala = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(filijala);
		assertEquals(id, filijala.getId());
	}
	
	@Test
	@Order(3)
	void testFindFilijalaByAdresa() {
		String adresa = "Ilije Ognjanovića 33, Novi Sad";
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala/addresa/" + adresa, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Filijala>>(){});
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale =  response.getBody();
		String filijalaAdresa =   filijale.get(0).getAdresa();
		
		assertEquals(200, statusCode );
		assertNotNull(filijale.get(0));
		assertTrue(filijalaAdresa.startsWith(adresa));	
	}
	
	@Test
	@Order(4)
	void testgetFilijalaByBanka() {
		long bankaId = 1;
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala/banka/" + bankaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Filijala>>(){});
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale =  response.getBody();
		
		assertEquals(200, statusCode );
		assertTrue(!filijale.isEmpty());
		for(Filijala f: filijale) {
			assertTrue(f.getBanka().getId() == 1);
		}
	}
	
	@Test
	@Order(5)
	void testgetFilijalaByPosedujeSef() {
		boolean posedujeSef = true;
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala/posedujeSef/" + posedujeSef, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Filijala>>(){});
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(filijale.get(0));
	}
	
	@Test
	@Order(6)
	void testCreateFilijala() {
		Filijala filijala = new Filijala();
		filijala.setAdresa("Test address");
		filijala.setBrojPpultova(7);
		filijala.setPosedujeSef(false);
		
		HttpEntity<Filijala> entity = new HttpEntity<Filijala>(filijala);
		createHighestId();
		ResponseEntity<Filijala> response  = template.postForEntity("/filijala", entity, Filijala.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(filijala.getAdresa(), response.getBody().getAdresa());
		assertEquals(7, response.getBody().getBrojPpultova());
		assertFalse(response.getBody().isPosedujeSef());
	}

	@Test
	@Order(7)
	void testUpdateFilijala() {
		Filijala filijala = new Filijala();
		filijala.setBrojPpultova(6);
		filijala.setPosedujeSef(true);
		
		
		HttpEntity<Filijala> entity = new HttpEntity<Filijala>(filijala);
		getHighestId();
		ResponseEntity<Filijala> response  = template.exchange("/filijala/id/" + largestId, HttpMethod.PUT, entity, Filijala.class);
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(200, response.getStatusCode().value());
		
		assertEquals(5, response.getBody().getBrojPpultova());
		assertTrue(response.getBody().isPosedujeSef());
	}

	@Test
	@Order(8)
	void testDeleteFilijala() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/filijala/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted!"));
	}


}
