package de.rc.jobticket.entities;


import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;

import de.rc.Identifizierbar;
import static javax.persistence.GenerationType.IDENTITY;


/**
 * The persistent class for the produkteigenschaften database table.
 * 
 */

@Entity
@Table(name="produkteigenschaften")
public class Produkteigenschaften implements Serializable, Identifizierbar {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	private int beschnitt;

	@Column(length=50)
	private String bindung;

	private int dummy;

	@Column(length=50)
	private String falzung;

	@Column(length=50)
	private String farbe;
	
	@Column(length=50)
	private String format;

    @Lob()
	private String produktbeschreibung;

	private int proof;

	private int seitenzahl;

	@Column(length=50)
	private String sonderfarbe;

	//bi-directional many-to-one association to Job
    @ManyToOne
	@JoinColumn(name="jobs_id", nullable=false)
	private Job job;
    
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date eingang;

	@Column(length = 10)
	private String print;

	@Temporal(TemporalType.DATE)
	private Date vorlage;

	@Temporal(TemporalType.DATE)
	private Date ausgang;

    public Produkteigenschaften() {
    	//System.out.println("erstellt object");
    }
    
    public Produkteigenschaften(String produktbeschreibung, Job job) {
    	this.setProduktbeschreibung(produktbeschreibung);
    	this.setJob(job);
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBeschnitt() {
		return this.beschnitt;
	}

	public void setBeschnitt(int beschnitt) {
		this.beschnitt = beschnitt;
	}

	public String getBindung() {
		return this.bindung;
	}

	public void setBindung(String bindung) {
		this.bindung = bindung;
	}

	public int getDummy() {
		return this.dummy;
	}

	public void setDummy(int dummy) {
		this.dummy = dummy;
	}

	public String getFalzung() {
		return this.falzung;
	}

	public void setFalzung(String falzung) {
		this.falzung = falzung;
	}

	public String getFarbe() {
		return this.farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getProduktbeschreibung() {
		return this.produktbeschreibung;
	}

	public void setProduktbeschreibung(String produktbeschreibung) {
		this.produktbeschreibung = produktbeschreibung;
	}

	public int getProof() {
		return this.proof;
	}

	public void setProof(int proof) {
		this.proof = proof;
	}

	public int getSeitenzahl() {
		return this.seitenzahl;
	}
	public Date getEingang() {
		return this.eingang;
	}

	public void setEingang(Date eingang) {
		this.eingang = eingang;
	}
	
	public Date getAusgang() {
		return this.ausgang;
	}

	public void setAusgang(Date ausgang) {
		this.ausgang = ausgang;
	}
	public String getPrint() {
		return this.print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public Date getVorlage() {
		return this.vorlage;
	}

	public void setVorlage(Date vorlage) {
		this.vorlage = vorlage;
	}

	public void setSeitenzahl(int seitenzahl) {
		this.seitenzahl = seitenzahl;
	}

	public String getSonderfarbe() {
		return this.sonderfarbe;
	}

	public void setSonderfarbe(String sonderfarbe) {
		this.sonderfarbe = sonderfarbe;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
}