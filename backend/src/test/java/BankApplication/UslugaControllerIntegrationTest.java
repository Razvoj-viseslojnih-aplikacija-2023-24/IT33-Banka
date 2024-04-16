package BankApplication;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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

import BankApplication.models.Usluga;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UslugaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;

	long largestId;

	public void createHighestId() {
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>() {
				});
		ArrayList<Usluga> list = (ArrayList<Usluga>) response.getBody();
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
	void testGetAllUsluga() {
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>() {
				});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluga = response.getBody();

		assertEquals(200, statusCode);
		assertNotNull(usluga);
	}

	@Test
	@Order(2)
	void testFindUslugaById() {
		long id = 1;
		ResponseEntity<Usluga> response = template.getForEntity("/usluga/id/" + id, Usluga.class);
		int statusCode = response.getStatusCode().value();
		Usluga usluga = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(usluga);
		assertEquals(id, usluga.getId());
	}

	@Test
	@Order(3)
	void testFindUslugaByNaziv() {
		String naziv = "Elektronsko bankarstvo";
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/naziv/" + naziv, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluga =  response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(usluga.get(0));
		for(Usluga u : usluga) {
			assertTrue(u.getNaziv().contains(naziv));
		}
	}

	@Test
	@Order(4)
	void testGetUslugaByFilijala() {
		long filijalaId = 3;
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/filijala/" + filijalaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluga =  response.getBody();
		
		assertEquals(200, statusCode );
		assertTrue(!usluga.isEmpty());
		for(Usluga u: usluga) {
			assertTrue(u.getFilijala().getId() == 3);
		}
	}
	
	@Test
	@Order(5)
	void testGetUslugaByKorisnik() {
		long userId = 1;
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/korisnik/" + userId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluga =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(usluga.get(0));
		for(Usluga u: usluga) {
			assertTrue(u.getKorisnik().getId() == 1);
		}
	}

	@Test
	@Order(6)
	void testCreateUsluga() {
		Usluga usluga = new Usluga();
		usluga.setNaziv("Test name");
		usluga.setOpis_usluge("Test Description");
		Date date = new Date();
		usluga.setDatum_ugovora(date);
		usluga.setProvizija(5);
		
		HttpEntity<Usluga> entity = new HttpEntity<Usluga>(usluga);
		createHighestId();
		ResponseEntity<Usluga> response  = template.postForEntity("/usluga", entity, Usluga.class);		
		
		assertTrue(response.hasBody());
		assertEquals(largestId, response.getBody().getId());
		assertEquals(201, response.getStatusCode().value());
		assertEquals(usluga.getNaziv(), response.getBody().getNaziv());
		assertEquals(usluga.getOpis_usluge(), response.getBody().getOpis_usluge());
		assertEquals(usluga.getDatum_ugovora(), response.getBody().getDatum_ugovora());
		assertEquals(usluga.getProvizija(), response.getBody().getProvizija());
	}

	@Test
	@Order(7)
	void testUpdateUsluga() {
		Usluga usluga = new Usluga();
		usluga.setNaziv("Changed name");
		usluga.setOpis_usluge("Changed description");
		usluga.setProvizija(9);
		
		HttpEntity<Usluga> entity = new HttpEntity<Usluga>(usluga);
		getHighestId();
		ResponseEntity<Usluga> response  = template.exchange("/usluga/id/" + largestId, HttpMethod.PUT, entity, Usluga.class);
		
		assertTrue(response.hasBody());
		assertEquals(200, response.getStatusCode().value());
		assertEquals(usluga.getNaziv(), response.getBody().getNaziv());
		assertEquals(usluga.getOpis_usluge(), response.getBody().getOpis_usluge());
		assertEquals(usluga.getProvizija(), response.getBody().getProvizija());
	}

	@Test
	@Order(8)
	void testDeleteUsluga() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/usluga/id/" + largestId, HttpMethod.DELETE, null, String.class);
		
		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().contains("has been deleted!"));
	}
}
