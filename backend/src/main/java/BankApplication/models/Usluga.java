package BankApplication.models;

import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Usluga implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "USLUGA_SEQ_GENERATOR", sequenceName = "USLUGA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USLUGA_SEQ_GENERATOR")
	private int id;
	private String naziv;
	private String opisUsluge;
	private Date datumUgovora;
	private double provizija;
	
	@ManyToOne
	@JoinColumn(name = "korisnik")
	private Korisnik korisnik;
	
	@ManyToOne
	@JoinColumn(name = "filijala")
	private Filijala filijala;

	public Usluga() {
		
	}
	
	public Usluga(int id, String naziv, String opisUsluge, Date datumUgovora, double provizija, Korisnik korisnik, Filijala filijala) {
		this.id = id;
		this.naziv = naziv;
		this.opisUsluge = opisUsluge;
		this.datumUgovora = datumUgovora;
		this.provizija = provizija;
		this.korisnik = korisnik;
		this.filijala = filijala;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis_usluge() {
		return opisUsluge;
	}

	public void setOpis_usluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}

	public Date getDatum_ugovora() {
		return datumUgovora;
	}

	public void setDatum_ugovora(Date datumUgovora) {
		this.datumUgovora = datumUgovora;
	}

	public double getProvizija() {
		return provizija;
	}

	public void setProvizija(double provizija) {
		this.provizija = provizija;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Filijala getFilijala() {
		return filijala;
	}

	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}

	
}
