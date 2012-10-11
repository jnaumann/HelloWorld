package de.rc.jobticket.entities;
import java.io.Serializable;

import javax.persistence.*;

import de.rc.Identifizierbar;

import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * The persistent class for the kunden database table.
 * 
 */

@Entity
@Table(name = "kunden")
public class Kunden implements Serializable, Comparable<Kunden>,
		Identifizierbar {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(length = 50)
	private String adresse;

	@Column(nullable = false, length = 50)
	private String kunde;

	@Column(nullable = false, length = 10)
	private String kundenkuerzel;

	@Column(length = 50)
	private String telefon;

	// bi-directional many-to-one association to Job
	@OneToMany(mappedBy = "kunden")
	private List<Job> jobs;

	public Kunden() {
	}

	public Kunden(String kunde, String kundenkuerzel) {
		this.setKunde(kunde);
		this.setKundenkuerzel(kundenkuerzel);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getKunde() {
		return this.kunde;
	}

	public void setKunde(String kunde) {
		this.kunde = kunde;
	}

	public String getKundenkuerzel() {
		return this.kundenkuerzel;
	}

	public void setKundenkuerzel(String kundenkuerzel) {
		this.kundenkuerzel = kundenkuerzel;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public int compareTo(Kunden o) {
		if ((this.getKunde().trim().toLowerCase()
				.compareTo(o.getKunde().trim().toLowerCase()) != 0)
				|| (this.getKundenkuerzel().trim().toLowerCase()
						.compareTo(o.getKundenkuerzel().trim().toLowerCase()) != 0)) {
			return 1;
		} else {
			return 0;
		}
	}

}