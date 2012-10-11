package de.rc.jobticket.entities;
import java.io.Serializable;

import javax.persistence.*;

import de.rc.Identifizierbar;

import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * The persistent class for the angestellte database table.
 * 
 */

@Entity
@Table(name = "angestellte")
public class Angestellte implements Serializable, Comparable<Angestellte>,
		Identifizierbar {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(nullable = false, length = 50)
	private String nachname;

	@Column(nullable = false, length = 50)
	private String vorname;

	// bi-directional many-to-one association to Angestelltenbezeichnungen
	@ManyToOne
	@JoinColumn(name = "angestelltenbezeichnung_id", nullable = false)
	private Angestelltenbezeichnungen angestelltenbezeichnungen;

	// bi-directional many-to-one association to Jobbearbeiter
	@OneToMany(mappedBy = "angestellte")
	private List<Jobbearbeiter> jobbearbeiters;

	// bi-directional many-to-one association to Kosten
	@OneToMany(mappedBy = "angestellte")
	private List<Kosten> kostens;

	public Angestellte() {
	}

	public Angestellte(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}

	public Angestellte(String vorname, String nachname,
			Angestelltenbezeichnungen angestelltenbezeichnungen) {
		this.setVorname(vorname);
		this.setNachname(nachname);
		this.setAngestelltenbezeichnungen(angestelltenbezeichnungen);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Angestelltenbezeichnungen getAngestelltenbezeichnungen() {
		return this.angestelltenbezeichnungen;
	}

	public void setAngestelltenbezeichnungen(
			Angestelltenbezeichnungen angestelltenbezeichnungen) {
		this.angestelltenbezeichnungen = angestelltenbezeichnungen;
	}

	public List<Jobbearbeiter> getJobbearbeiters() {
		return this.jobbearbeiters;
	}

	public void setJobbearbeiters(List<Jobbearbeiter> jobbearbeiters) {
		this.jobbearbeiters = jobbearbeiters;
	}

	public List<Kosten> getKostens() {
		return this.kostens;
	}

	public void setKostens(List<Kosten> kostens) {
		this.kostens = kostens;
	}

	/**
	 * Vergleicht die Angestellten und sucht nach bereits eingetragenden 0 =
	 * identische Bezeichnung gefunden 0 != nicht identisch
	 */
	public int compareTo(Angestellte o) {
		if ((this.getVorname().trim().toLowerCase()
				.compareTo(o.getVorname().trim().toLowerCase()) != 0)
				|| (this.getNachname().trim().toLowerCase()
						.compareTo(o.getNachname().trim().toLowerCase()) != 0)) {
			return 1;
		} else {
			return 0;
		}
	}
}