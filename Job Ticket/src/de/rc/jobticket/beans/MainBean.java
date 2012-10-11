package de.rc.jobticket.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import de.rc.AngestellteModel;
import de.rc.DBZugriff;
import de.rc.jobticket.entities.Angestellte;
import de.rc.jobticket.entities.Job;
import de.rc.jobticket.entities.Jobbearbeiter;
import de.rc.jobticket.entities.Kosten;

/**
 * 
 */

/**
 * juni 2012
 * <p>
 * Haubtverwaltungsklasse für alle UnterBeans
 * </p>
 * 
 * @author janine und atilla
 * 
 */
@ManagedBean
public class MainBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8219147740616521605L;
	private ProduktBean produktBean;
	private JobBean jobBean;
	private KundenBean kundenBean;
	private AngestellteModel angestellteModel;
	private AngestellteBean angestellteBean;
	private DBZugriff dbAccess;
	private KostenBean kostenBean;

	/**
	 * Initialisiert den Datenbankzugriff und alle UnterBeanObjekte
	 */
	public MainBean() {
		produktBean = new ProduktBean();
		jobBean = new JobBean();
		kundenBean = new KundenBean();
		angestellteModel = new AngestellteModel();
		angestellteBean = new AngestellteBean();
		kostenBean = new KostenBean();
		dbAccess = new DBZugriff();
	}

	/**
	 * @return the produktBean
	 */
	public ProduktBean getProduktBean() {
		return produktBean;
	}

	/**
	 * @param produktModel
	 *            the produktBean to set
	 */
	public void setProduktBean(ProduktBean produktModel) {
		this.produktBean = produktModel;
	}

	/**
	 * @return the jobBean
	 */
	public JobBean getJobBean() {
		return jobBean;
	}

	/**
	 * @return the kundenBean
	 */
	public KundenBean getKundenBean() {
		return kundenBean;
	}

	/**
	 * @param kundenBean
	 *            the kundenBean to set
	 */
	public void setKundenBean(KundenBean kundenBean) {
		this.kundenBean = kundenBean;
	}

	/**
	 * @param jobBean
	 *            the jobBean to set
	 */
	public void setJobBean(JobBean jobBean) {
		this.jobBean = jobBean;
	}

	/**
	 * @return the angestellteBean
	 */
	public AngestellteModel getAngestellteModel() {
		return angestellteModel;
	}

	/**
	 * @param angestellteModel
	 *            the angestellteBean to set
	 */
	public void setAngestellteModel(AngestellteModel angestellteModel) {
		this.angestellteModel = angestellteModel;
	}

	/**
	 * @return the kostenBean
	 */
	public KostenBean getKostenBean() {
		return kostenBean;
	}

	/**
	 * @param kostenBean
	 *            the kostenBean to set
	 */
	public void setKostenBean(KostenBean kostenBean) {
		this.kostenBean = kostenBean;
	}

	/**
	 * @return the angestellteBean
	 */
	public AngestellteBean getAngestellteBean() {
		return angestellteBean;
	}

	/**
	 * @param angestellteBean
	 *            the angestellteBean to set
	 */
	public void setAngestellteBean(AngestellteBean angestellteBean) {
		this.angestellteBean = angestellteBean;
	}

	/**
	 * Erstellt einen Jobbearbeiter der für die Zuordung von Angestellten und
	 * Job verantwortlich ist ( SQL many-to-many)
	 * 
	 * @param job
	 *            der zum Jobbearbeiter dazugehörige Job
	 * @return Jobbearbeiter aus der Datenbank
	 */
	private Jobbearbeiter erstelleJobbearbeiter(Job job) {
		Jobbearbeiter jobbe_return = null;
		try {
			Jobbearbeiter jobbearbeiter = new Jobbearbeiter();

			// Wenn kein Angestellter eingetragen wurde
			if (angestellteModel.getAngestellte_name().trim().compareTo("") == 0) {
				return null;
			}
			Angestellte ange = dbAccess
					.findAngestelltenWithFullname(angestellteModel
							.getAngestellte_name());

			if (ange == null) {
				throw new Exception(
						"In der Datenbak wurde kein Angestellter gefunden");
			}

			jobbearbeiter.setAngestellte(ange);
			jobbearbeiter.setJob(job);
			dbAccess.addEintrag(jobbearbeiter, dbAccess.createEntitymanager());
			jobbe_return = dbAccess.findJobbearbeiter(jobbearbeiter);
		} catch (Exception e) {
			e.printStackTrace();
			return jobbe_return;
		}

		return jobbe_return;
	}

	/**
	 * Erstellt die Kosten die pro Job und pro Angestellten erzeugt werden
	 * können ( SQL many-to-many)
	 * 
	 * @param job
	 *            der zu den Kosten dazugehörige Job
	 * @return Kosten aus der Datenbank
	 */

	private Kosten erstelleKosten(Job job) {
		Kosten kosten_return = null;
		try {
			Kosten kosten = new Kosten();

			// Wenn kein Angestellter eingetragen wurde
			if (angestellteModel.getAngestellte_name().trim().compareTo("") == 0) {
				return null;
			}
			Angestellte ange = dbAccess
					.findAngestelltenWithFullname(angestellteModel
							.getAngestellte_name());

			if (ange == null) {
				throw new Exception(
						"In der Datenbak wurde kein Angestellter gefunden");
			}

			kosten.setAngestellte(ange);
			kosten.setJob(job);
			if (kostenBean.isAbrechnungInEuro()) {
				if (kostenBean.validateKostenEuro() != null)
					kosten.setArbeitsaufwandInEuro(kostenBean
							.validateKostenEuro());
			} else {
				if (kostenBean.validateKostenStd() != null)
					kosten.setArbeitsaufwandInStd(kostenBean
							.validateKostenStd());
			}
			dbAccess.addEintrag(kosten, dbAccess.createEntitymanager());
			kosten = dbAccess.findKosten(kosten);
		} catch (Exception e) {
			e.printStackTrace();
			return kosten_return;
		}

		return kosten_return;
	}

	/**
	 * Speichert alle Einträge zu einem Job in die Datenbank
	 * 
	 */
	public void speichern() {
		Job job_db = jobBean.erstelleJob();
		if (job_db != null) {
			Jobbearbeiter jobbearbeiter = erstelleJobbearbeiter(job_db);

			if (jobbearbeiter != null) {
				job_db.getJobbearbeiters().add(jobbearbeiter);

				Kosten kosten = erstelleKosten(job_db);
				if (kosten != null) {
					job_db.getKostens().add(kosten);
				}
			}
			for (int i = 0; i < produktBean.getProduktSammlung().size(); i++) {
				produktBean.getProduktSammlung().get(i).erstelleProdukt(job_db);
			}
		}System.out.println("fertig");
	}


}
