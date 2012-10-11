package de.rc.jobticket.entities;


import java.io.Serializable;

import javax.persistence.*;

import de.rc.Identifizierbar;
import de.rc.jobticket.entities.Angestellte;
import static javax.persistence.GenerationType.IDENTITY;



/**
 * The persistent class for the jobbearbeiter database table.
 * 
 */

@Entity
@Table(name="jobbearbeiter")
public class Jobbearbeiter implements Serializable, Identifizierbar {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = IDENTITY)
	private int id;

	//bi-directional many-to-one association to Angestellte
    @ManyToOne
	@JoinColumn(name="angestellte_id", nullable=false)
	private Angestellte angestellte;

	//bi-directional many-to-one association to Job
    @ManyToOne
	@JoinColumn(name="jobs_id", nullable=false)
	private Job job;

    public Jobbearbeiter() {
    }
    
    public Jobbearbeiter(Angestellte angestellte, Job job){
    	this.setAngestellte(angestellte);
    	this.setJob(job);
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Angestellte getAngestellte() {
		return this.angestellte;
	}

	public void setAngestellte(Angestellte angestellte) {
		this.angestellte = angestellte;
	}
	
	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
}