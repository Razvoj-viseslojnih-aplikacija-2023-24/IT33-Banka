package BankApplication.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Korisnik implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "KORISNIK_SEQ_GENERATOR", sequenceName = "KORISNIK_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KORISNIK_SEQ_GENERATOR")
	private int id;
	private String ime;
	private String prezime;
	private String maticniBroj;
	
	@OneToMany(mappedBy = "korisnik")
	@JsonIgnore
	private List<Usluga> usluga;

	public Korisnik() {
		
	}
	
	public Korisnik(int id, String ime, String prezime, String maticniBroj) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.maticniBroj = maticniBroj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getMaticni_broj() {
		return maticniBroj;
	}

	public void setMaticni_broj(String maticniBroj) {
		this.maticniBroj = maticniBroj;
	}

	public List<Usluga> getUsluga() {
		return usluga;
	}

	public void setUsluga(List<Usluga> usluga) {
		this.usluga = usluga;
	}
}
