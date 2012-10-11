package de.rc.jobticket.entities;
import java.io.Serializable;

import javax.persistence.*;

import de.rc.Identifizierbar;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the jobs database table.
 * 
 */

@Entity
@Table(name = "jobs")
public class Job implements Serializable, Identifizierbar, Comparable<Job> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private int id;

	@Column(name = "alte_jobnummer")
	private int alteJobnummer;

	@Column(name = "budget_in_euro", precision = 10, scale = 2)
	private BigDecimal budgetInEuro;

	@Column(name = "budget_in_std", precision = 10, scale = 2)
	private BigDecimal budgetInStd;

	private int erledigt;

	@Lob()
	private String jobbeschreibung;

	@Column(nullable = false, length = 30)
	private String name;

	// bi-directional many-to-one association to Jobbearbeiter
	@OneToMany(mappedBy = "job")
	private List<Jobbearbeiter> jobbearbeiters;

	// bi-directional many-to-one association to Kunden
	@ManyToOne
	@JoinColumn(name = "kunden_id", nullable = false)
	private Kunden kunden;

	// bi-directional many-to-one association to Kosten
	@OneToMany(mappedBy = "job")
	private List<Kosten> kostens;

	// bi-directional many-to-one association to Produkteigenschaften
	@OneToMany(mappedBy = "job")
	private List<Produkteigenschaften> produkteigenschaftens;

	public Job() {
		this.name = "00000_empty";
		this.jobbearbeiters = null;
		this.kostens = null;
		this.produkteigenschaftens = null;
	}

	public Job(String jobbeschreibung) {
		this.setJobbeschreibung(jobbeschreibung);
	}

	public Job(String jobbeschreibung, Kunden kunden) {
		this.setJobbeschreibung(jobbeschreibung);
		this.setKunden(kunden);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlteJobnummer() {
		return this.alteJobnummer;
	}

	public void setAlteJobnummer(int alteJobnummer) {
		this.alteJobnummer = alteJobnummer;
	}

	public BigDecimal getBudgetInEuro() {
		return this.budgetInEuro;
	}

	public void setBudgetInEuro(BigDecimal budgetInEuro) {
		this.budgetInEuro = budgetInEuro;
	}

	public BigDecimal getBudgetInStd() {
		return this.budgetInStd;
	}

	public void setBudgetInStd(BigDecimal budgetInStd) {
		this.budgetInStd = budgetInStd;
	}

	public int getErledigt() {
		return this.erledigt;
	}

	public void setErledigt(int erledigt) {
		this.erledigt = erledigt;
	}

	public String getJobbeschreibung() {
		return this.jobbeschreibung;
	}

	public void setJobbeschreibung(String jobbeschreibung) {
		this.jobbeschreibung = jobbeschreibung;
	}

	public void generateName() {
		this.name = generateID() + "_" + this.getKunden().getKundenkuerzel()
				+ "_";
		char[] carr = this.jobbeschreibung.toCharArray();
		int länge = this.name.length();
		for (int i = this.name.length(); i < 26; i++) {
			try {
				this.name += carr[i - länge];
				// System.out.println(länge);
			} catch (ArrayIndexOutOfBoundsException e) {
				// System.err.println("Jobbeschreibung zu kurz");
				// der job name könnte noch länger sein
				break;
			}
		}
		this.name = this.name.replace(' ', '_');
	}

	/**
	 * Setzt den namen aus generatedID(), kundenkuerzel und jobbeschreibung
	 * zusammen
	 * 
	 * @return zusammengesetzter Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Generiert aus der standart ID, die mit nullen(auf 5 Stellen) aufgefüllte
	 * variante
	 * 
	 * @return generierte ID
	 */
	private String generateID() {
		String generatedID = "";
		char[] carr = String.valueOf(this.getId()).toCharArray();
		if (carr.length < 5) {
			for (int i = carr.length; i < 5; i++) {
				generatedID += "0";
			}
			for (int i = 0; i < carr.length; i++) {
				generatedID += carr[i];
			}
		}
		return generatedID;
	}

	public void setName(String name) {
		// this.name = name;
		this.name = name;
	}

	public List<Jobbearbeiter> getJobbearbeiters() {
		return this.jobbearbeiters;
	}

	public void setJobbearbeiters(List<Jobbearbeiter> jobbearbeiters) {
		this.jobbearbeiters = jobbearbeiters;
	}

	public Kunden getKunden() {
		return this.kunden;
	}

	public void setKunden(Kunden kunden) {
		this.kunden = kunden;
	}

	public List<Kosten> getKostens() {
		return this.kostens;
	}

	public void setKostens(List<Kosten> kostens) {
		this.kostens = kostens;
	}

	public List<Produkteigenschaften> getProdukteigenschaftens() {
		return this.produkteigenschaftens;
	}

	public void setProdukteigenschaftens(
			List<Produkteigenschaften> produkteigenschaftens) {
		this.produkteigenschaftens = produkteigenschaftens;
	}

	public int compareTo(Job o) {
		return this.getJobbeschreibung().compareTo(o.getJobbeschreibung());
	}

}