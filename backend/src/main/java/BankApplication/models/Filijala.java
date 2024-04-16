package BankApplication.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Filijala implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "FILIJALA_SEQ_GENERATOR", sequenceName = "FILIJALA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILIJALA_SEQ_GENERATOR")
	private int id;
	private String adresa;
	private int brojPultova;
	private boolean posedujeSef;
	
	@ManyToOne //banka je strani kljuc u filijali
	@JoinColumn(name = "banka")
	private Banka banka;
	
	@OneToMany(mappedBy = "filijala") //filijala je strani kljuc u usluzi
	@JsonIgnore
	private List<Usluga> usluga;

	public Filijala()
	{
		
	}
	
	public Filijala(int id, String adresa, int brojPpultova, boolean posedujeSef, Banka banka, List<Usluga> usluga) {
		this.id = id;
		this.adresa = adresa;
		this.brojPultova = brojPpultova;
		this.posedujeSef = posedujeSef;
		this.banka = banka;
		this.usluga = usluga;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getBrojPpultova() {
		return brojPultova;
	}

	public void setBrojPpultova(int brojPpultova) {
		this.brojPultova = brojPpultova;
	}

	public boolean isPosedujeSef() {
		return posedujeSef;
	}

	public void setPosedujeSef(boolean posedujeSef) {
		this.posedujeSef = posedujeSef;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	public List<Usluga> getUsluga() {
		return usluga;
	}

	public void setUsluga(List<Usluga> usluga) {
		this.usluga = usluga;
	}
	
	
	
	
}
