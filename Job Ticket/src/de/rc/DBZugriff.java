package de.rc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.rc.jobticket.entities.Angestellte;
import de.rc.jobticket.entities.Angestelltenbezeichnungen;
import de.rc.jobticket.entities.Job;
import de.rc.jobticket.entities.Jobbearbeiter;
import de.rc.jobticket.entities.Kosten;
import de.rc.jobticket.entities.Kunden;
import de.rc.jobticket.entities.Produkteigenschaften;

/**
 * Juni 2012 
 * 
 * <p>Eine Klasse, die den Zugriff zur Datenbank regelt</p>
 * 
 * @author janine und atilla

 */

public class DBZugriff implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf;

	/**
	 * Erstellt eine EntityManagerFactory, um sich mit der Datenbank zu
	 * verbinden.
	 */
	public DBZugriff() {
		emf = Persistence.createEntityManagerFactory("Job Ticket");
	}

	/**
	 * Gibt einem eine komplette Liste der jeweiligen Tabelle zur�ck.
	 * 
	 * @param c
	 *            Klasse der Tabelle
	 * @param em
	 *            EntityManager f�r die Verbindung
	 * @return die Liste mit den Daten
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getDatalist(Class<T> c, EntityManager em) {
		List<T> list = new ArrayList<T>();
		try {
			String[] klasse_arr = c.getName().split("\\.");
			String klasse;
			if (klasse_arr.length != 0) {
				klasse = klasse_arr[klasse_arr.length - 1];
			} else {
				klasse = c.getName();
			}

			Query q = em.createQuery("select n from " + klasse + " n");
			list = q.getResultList();
			for (T t : list) {
				em.refresh(t);
			}
		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			return list;
		}
		em.close();
		return list;
	}

	/**
	 * Mit dieser methode wird die, zum String passende, Angestellenbezeichnung
	 * aus der datenbank gesucht.
	 * 
	 * @param bezeichnung
	 *            nach der zu suchenden Bezeichnung
	 * @return gibt, falls gefunden(!=null), die Bezeichnung aus der Datenbank
	 *         zur�ck.
	 */
	public Angestelltenbezeichnungen findAngestelltenbezeichnungen(
			String bezeichnung) {
		Angestelltenbezeichnungen bez = null;
		List<Angestelltenbezeichnungen> list = getDatalist(
				Angestelltenbezeichnungen.class, createEntitymanager());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBezeichnung().compareTo(bezeichnung) == 0) {
				bez = list.get(i);
				break;
			}
		}
		return bez;
	}

	/**
	 * Mit dieser methode wird die, zum String passende, Angestellenbezeichnung
	 * aus der datenbank gesucht.
	 * 
	 * @param bezeichnung
	 *            nach der zu suchenden Bezeichnung
	 * @return gibt, falls gefunden(!=null), die Bezeichnung aus der Datenbank
	 *         zur�ck.
	 */
	public Kunden findKundenWithName(String kundenname) {
		Kunden kunde = null;
		List<Kunden> list = getDatalist(Kunden.class, createEntitymanager());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKunde().compareTo(kundenname) == 0) {
				kunde = list.get(i);
				break;
			}
		}
		return kunde;
	}

	/**
	 * Mit dieser methode wird die, zum String passende, Angestellenbezeichnung
	 * aus der Datenbank gesucht.
	 * 
	 * @param bezeichnung
	 *            nach der zu suchenden Bezeichnung
	 * @return gibt, falls gefunden(!=null), die Bezeichnung aus der Datenbank
	 *         zur�ck.
	 */
	public Kunden findKundenWithKuerzel(String kundenkuerzel) {
		Kunden kunde = null;
		List<Kunden> list = getDatalist(Kunden.class, createEntitymanager());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKundenkuerzel().compareTo(kundenkuerzel) == 0) {
				kunde = list.get(i);
				break;
			}
		}
		return kunde;
	}

	/**
	 * Findet mit hilfe des Jobs und mit hilfe des Angestellten, den jeweiligen 
	 * Jobbearbeiter.
	 * 
	 * @param jobbearbeiter
	 * @return Jobbearbeiter pendant aus der Datenbank
	 */
	public Jobbearbeiter findJobbearbeiter(Jobbearbeiter jobbearbeiter) {
		List<Jobbearbeiter> list = getDatalist(Jobbearbeiter.class,
				createEntitymanager());
		for (int i = 0; i <= list.size(); i++) {
			if (list.size() == i) {
				jobbearbeiter = null;
				break;
			}
			if ((list.get(i).getAngestellte()
					.compareTo(jobbearbeiter.getAngestellte()) == 0)
					&& (list.get(i).getJob().compareTo(jobbearbeiter.getJob()) == 0)) {
				jobbearbeiter = list.get(i);
				break;
			}
		}
		return jobbearbeiter;
	}
	
	/**
	 * Findet mit hilfe des Jobs und mit hilfe des Angestellten, den jeweiligen 
	 * Arbeitsaufwand.
	 * 
	 * @param kosten
	 * @return Kosten pendant aus der Datenbank
	 */
	public Kosten findKosten(Kosten kosten) {
		List<Kosten> list = getDatalist(Kosten.class,
				createEntitymanager());
		for (int i = 0; i <= list.size(); i++) {
			if (list.size() == i) {
				kosten = null;
				break;
			}
			if ((list.get(i).getAngestellte()
					.compareTo(kosten.getAngestellte()) == 0)
					&& (list.get(i).getJob().compareTo(kosten.getJob()) == 0)) {
				kosten = list.get(i);
				break;
			}
		}
		return kosten;
	}

	/**
	 * Sucht mit Hilfe des �bergebenen Teilstrings, die dazu passenden
	 * Angestelltenbezichnungen. Hierf�r wird eine Charsquenz verwendet damit
	 * auch teile innerhalb von Bezeichnungen wiedererkannt werden.
	 * 
	 * @param bez
	 *            Teilstring
	 * @return die gefundenen Eintr�ge die mit dem Teilstring �bereinstimmen.
	 */
	public List<String> completeAngestelltenbezeichnung(String bez) {
		List<Angestelltenbezeichnungen> list = getDatalist(
				Angestelltenbezeichnungen.class, createEntitymanager());
		List<String> fillStr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBezeichnung().toLowerCase()
					.contains(bez.toLowerCase())) {
				System.err.println(list.get(i).getBezeichnung());
				fillStr.add(list.get(i).getBezeichnung());
			}
		}
		return fillStr;
	}

	/**
	 * Sucht mit Hilfe des �bergebenen Teilstrings, die dazu passenden
	 * Kudennamen. Hierf�r wird eine Charsquenz verwendet damit auch teile
	 * innerhalb von Bezeichnungen wiedererkannt werden.
	 * 
	 * @param kundenname
	 *            Teilstring
	 * @return die gefundenen Eintr�ge die mit dem Teilstring �bereinstimmen.
	 */
	public List<String> completeKundennamen(String kundenname) {
		List<Kunden> list = getDatalist(Kunden.class, createEntitymanager());
		List<String> fillStr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKunde().toLowerCase()
					.contains(kundenname.toLowerCase())) {
				System.err.println(list.get(i).getKunde());
				fillStr.add(list.get(i).getKunde());
			}
		}
		return fillStr;
	}

	/**
	 * Sucht mit Hilfe des �bergebenen Teilstrings, die dazu passenden
	 * Kudennamen. Hierf�r wird eine Charsquenz verwendet damit auch teile
	 * innerhalb von Bezeichnungen wiedererkannt werden.
	 * 
	 * @param kundenname
	 *            Teilstring
	 * @return die gefundenen Eintr�ge die mit dem Teilstring �bereinstimmen.
	 */
	public List<String> completeKundenkuerzel(String kundenname) {
		List<Kunden> list = getDatalist(Kunden.class, createEntitymanager());
		List<String> fillStr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getKundenkuerzel().toLowerCase()
					.contains(kundenname.toLowerCase())) {
				System.err.println(list.get(i).getKundenkuerzel());
				fillStr.add(list.get(i).getKundenkuerzel());
			}
		}
		return fillStr;
	}

	/**
	 * Sucht mit Hilfe des �bergebenen Teilstrings, die dazu passenden
	 * Angestelltennamen. Hierf�r wird eine Charsquenz verwendet damit auch
	 * teile innerhalb von Bezeichnungen wiedererkannt werden.
	 * 
	 * @param angestelltenname
	 *            Teilstring
	 * @return die gefundenen Eintr�ge die mit dem Teilstring �bereinstimmen.
	 */
	public List<String> completeAngestelltenname(String angestellenname) {
		List<Angestellte> list = getDatalist(Angestellte.class,
				createEntitymanager());
		List<String> fillStr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String namekomplett = list.get(i).getVorname() + " "
					+ list.get(i).getNachname();
			if (namekomplett.toLowerCase().contains(
					angestellenname.toLowerCase())) {
				System.err.println(list.get(i).getVorname());
				fillStr.add(namekomplett);
			}
		}
		return fillStr;
	}

	/**
	 * Mit dieser methode wird der, zum String passende, Angestellente aus der
	 * Datenbank gesucht.
	 * 
	 * @param Angestelltenname
	 *            nach der zu suchenden Bezeichnung
	 * @return gibt, falls gefunden(!=null), die Bezeichnung aus der Datenbank
	 *         zur�ck.
	 */
	public Angestellte findAngestelltenWithFullname(String angestellenname) {
		Angestellte angestellter = null;
		List<Angestellte> list = getDatalist(Angestellte.class,
				createEntitymanager());

		for (int i = 0; i < list.size(); i++) {
			String namekomplett = list.get(i).getVorname() + " "
					+ list.get(i).getNachname();
			if (namekomplett.toLowerCase().compareTo(
					angestellenname.toLowerCase()) == 0) {
				angestellter = list.get(i);
				break;
			}
		}
		return angestellter;
	}

	/**
	 * f�gt ein Element hinzu, sofern dieses noch nicht in der datenbank
	 * voarhanden ist. Vorraussetzung ist, dass das Element auch Comparable ist
	 * 
	 * @param comparator
	 *            das zu vergleichende Element
	 * @param em
	 *            der EntityManager �ber den die Daten zum vergleich geholt
	 *            werden
	 * @return false: wenn die person schon vorhanden ist und nicht hinzugef�ght
	 *         werden kann<br>
	 *         true: wenn das Element hinzugef�gt wurde
	 */
	@SuppressWarnings("unchecked")
	public boolean addElement(Comparable comparator, EntityManager em) {
		List<Comparable> list = (List<Comparable>) getDatalist(
				comparator.getClass(), em);
		for (Comparable arbeiter : list) {
			if (comparator.compareTo(arbeiter) == 0) {
				System.err.println("Datenbankeintrag ist bereits vorhanden");
				return false;
			}
		}
		try {
			try {
				em = this.createEntitymanager();
				// System.out.println(em);
				em.getTransaction().begin();
				em.persist(comparator.getClass().cast(comparator));
				em.getTransaction().commit();

				return true;
			} finally {
				em.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * F�gt ein jobticket in die Datenbank ein
	 * 
	 * @param job
	 *            das jobticket
	 * @param em
	 *            EntityManager
	 * @return !=-1 wenn eingetragen und PrimaryKey gefunden
	 */
	public int addJob(Job job, EntityManager em) {
		try {
			try {
				em.getTransaction().begin();
				em.persist(job);
				em.getTransaction().commit();
			} finally {
				em.close();
			}
			em = createEntitymanager();
			List<Job> list = (List<Job>) getDatalist(Job.class, em);
			return list.get(list.size() - 1).getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * F�gt einen Eintrag in die Datenbank ein
	 * 
	 * @param Identifizierbar
	 *            der Eintrag
	 * @param em
	 *            EntityManager
	 */
	public void addEintrag(Identifizierbar i, EntityManager em) {
		try {
			try {
				em.getTransaction().begin();
				em.persist(i.getClass().cast(i));
				em.getTransaction().commit();
			} finally {
				em.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.close();
		}
	}

	/**
	 * Entfernt das �bergebene identifizierbare Objekt aus der Datenbank Daf�r
	 * muss das Interface Identifizierbar implementiert sein, wodurch man an die
	 * Eigentliche ID des Eintrages kommt.
	 * 
	 * @param ident
	 *            das zu entfernende Objekt
	 * @param em
	 *            Manager f�r die datenbank
	 * @return true falls es entfernt wurde
	 */
	public boolean removeElement(Identifizierbar ident, EntityManager em) {
		try {
			em.getTransaction().begin();
			ident = em.find(ident.getClass(), ident.getId());
			em.remove(ident);

			try {
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}
		} finally {
			em.close();

		}
		return true;
	}

	/**
	 * Findet jede art von Element in der datenbank durch den Primarykey ID
	 * 
	 * @param c
	 *            Klasse von dem zu suchenden Eintrag
	 * @param id
	 *            Primarykey
	 * @return != null wenn ein Eintrag gefunden wurde
	 */
	public <T> T findElement(Class<T> c, int id) {
		T found = null;
		EntityManager em = createEntitymanager();
		try {
			em.getTransaction().begin();
			found = (T) em.find(c, id);
		} finally {
			em.close();
		}

		return found;
	}

	/**
	 * Entfernt ein Objekt der Klasse mit dem dazu geh�rigen Primary key
	 * 
	 * @param c
	 *            die klasse des zu l�schenden Objektes
	 * @param id
	 *            der Primary Key
	 * @param em
	 *            EntityManager
	 * @return true wenn Objekt gel�scht wurde
	 */
	public <T> boolean removeElement(Class<T> c, int id, EntityManager em) {
		try {
			em.getTransaction().begin();
			T element = em.find(c, id);
			em.remove(element);

			try {
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}

		} finally {
			em.close();

		}
		return true;
	}

	/**
	 * Updated alle Eigenschaften von job die ge�ndert werden sollen
	 * 
	 * @param job
	 *            das geupdatete Objekt
	 * @param em
	 *            Manager
	 * @return true wenn geupdated wurde
	 */
	public Job updateJob(Job job, EntityManager em) {
		Job job_db = null;
		try {
			job_db = em.find(job.getClass(), job.getId());
			em.getTransaction().begin();
			job_db.setAlteJobnummer(job.getAlteJobnummer());
			job_db.setBudgetInEuro(job.getBudgetInEuro());
			job_db.setBudgetInStd(job.getBudgetInStd());
			job_db.setErledigt(job.getErledigt());
			job_db.setJobbearbeiters(job.getJobbearbeiters());
			job_db.setJobbeschreibung(job.getJobbeschreibung());
			job_db.setKostens(job.getKostens());
			job_db.setKunden(job.getKunden());
			job_db.setProdukteigenschaftens(job.getProdukteigenschaftens());
			job_db.setName(job.getName());
			try {
				// System.out.println(job_db.getKunden().getKunde());
				em.merge(job_db);
				em.getTransaction().commit();

			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return job_db;
			}
		} finally {
			em.close();
		}
		return job_db;
	}

	/**
	 * Updated alle Eigenschaften von jobbearbeiter die ge�ndert werden sollen
	 * 
	 * @param jobbearbeiter
	 *            das geupdatete Objekt
	 * @param em
	 *            Manager
	 * @return true wenn geupdated wurde
	 */
	public boolean updateJobbearbeiter(Jobbearbeiter jobbearbeiter,
			EntityManager em) {
		try {
			em.getTransaction().begin();
			Jobbearbeiter jobbearbeiter_db = em.find(jobbearbeiter.getClass(),
					jobbearbeiter.getId());
			jobbearbeiter_db.setAngestellte(jobbearbeiter.getAngestellte());
			jobbearbeiter_db.setJob(jobbearbeiter.getJob());
			try {
				em.merge(jobbearbeiter_db);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}
		} finally {
			em.close();
		}
		return true;
	}

	/**
	 * Updated alle Eigenschaften von produkteigenschaften die ge�ndert werden
	 * sollen
	 * 
	 * @param produkteigenschaften
	 *            das geupdatete Objekt
	 * @param em
	 *            Manager
	 * @return true wenn geupdated wurde
	 */
	public boolean updateProdukteigenschaften(
			Produkteigenschaften produkteigenschaften, EntityManager em) {
		try {
			em.getTransaction().begin();
			Produkteigenschaften produkteigenschaften_db = em.find(
					produkteigenschaften.getClass(),
					produkteigenschaften.getId());
			produkteigenschaften_db.setFormat(produkteigenschaften.getFormat());
			produkteigenschaften_db.setBeschnitt(produkteigenschaften
					.getBeschnitt());
			produkteigenschaften_db.setSeitenzahl(produkteigenschaften
					.getSeitenzahl());
			produkteigenschaften_db.setFalzung(produkteigenschaften
					.getFalzung());
			produkteigenschaften_db.setFarbe(produkteigenschaften.getFarbe());
			produkteigenschaften_db.setSonderfarbe(produkteigenschaften
					.getSonderfarbe());
			produkteigenschaften_db.setBindung(produkteigenschaften
					.getBindung());
			produkteigenschaften_db.setProof(produkteigenschaften.getProof());
			produkteigenschaften_db.setDummy(produkteigenschaften.getDummy());
			produkteigenschaften_db.setProduktbeschreibung(produkteigenschaften
					.getProduktbeschreibung());
			produkteigenschaften_db.setJob(produkteigenschaften.getJob());
			produkteigenschaften_db.setAusgang(produkteigenschaften
					.getAusgang());
			produkteigenschaften_db.setPrint(produkteigenschaften.getPrint());
			produkteigenschaften_db.setVorlage(produkteigenschaften
					.getVorlage());
			produkteigenschaften_db.setEingang(produkteigenschaften
					.getEingang());
			try {
				em.merge(produkteigenschaften_db);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}
		} finally {
			em.close();
		}
		return true;
	}

	/**
	 * Updated alle Eigenschaften von Angestelltenbezeichnungen die ge�ndert
	 * werden sollen
	 * 
	 * @param bezeichnungen
	 *            das geupdatete Objekt
	 * @param em
	 *            Manager
	 * @return true wenn geupdated wurde
	 */
	public boolean updateAngestelltenbezeichnung(
			Angestelltenbezeichnungen bezeichnung, EntityManager em) {
		try {
			em.getTransaction().begin();
			Angestelltenbezeichnungen bezeichnung_db = em.find(
					bezeichnung.getClass(), bezeichnung.getId());
			bezeichnung_db.setBezeichnung(bezeichnung.getBezeichnung());
			bezeichnung_db.setAngestelltes(bezeichnung.getAngestelltes());
			try {
				em.merge(bezeichnung_db);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}
		} finally {
			em.close();
		}
		return true;
	}

	/**
	 * Updated alle Eigenschaften von Angestellte die ge�ndert werden sollen
	 * 
	 * @param angestellte
	 *            das geupdatete Objekt
	 * @param em
	 *            Manager
	 * @return true wenn geupdated wurde
	 */
	public boolean updateAngestellte(Angestellte angestellte, EntityManager em) {
		try {
			em.getTransaction().begin();
			Angestellte angestellte_db = em.find(angestellte.getClass(),
					angestellte.getId());
			angestellte_db.setAngestelltenbezeichnungen(angestellte
					.getAngestelltenbezeichnungen());
			angestellte_db.setJobbearbeiters(angestellte.getJobbearbeiters());
			angestellte_db.setKostens(angestellte.getKostens());
			angestellte_db.setNachname(angestellte.getNachname());
			angestellte_db.setVorname(angestellte.getVorname());
			try {
				em.merge(angestellte_db);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}
		} finally {
			em.close();
		}
		return true;
	}

	public EntityManager createEntitymanager() {
		return emf.createEntityManager();
	}

	/**
	 * Updated alle Eigenschaften von Kosten die ge�ndert werden sollen
	 * 
	 * @param kosten
	 *            das geupdatete Objekt
	 * @param em
	 *            Manager
	 * @return true wenn geupdated wurde
	 */
	public boolean updateKosten(Kosten kosten, EntityManager em) {
		try {
			em.getTransaction().begin();
			Kosten kosten_db = em.find(kosten.getClass(), kosten.getId());
			kosten_db.setAngestellte(kosten.getAngestellte());
			kosten_db.setArbeitsaufwandInEuro(kosten.getArbeitsaufwandInEuro());
			kosten_db.setArbeitsaufwandInStd(kosten.getArbeitsaufwandInStd());
			kosten_db.setJob(kosten.getJob());
			kosten_db.setStundenlohn(kosten.getStundenlohn());
			kosten_db.setKommentar(kosten.getKommentar());
			try {
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}
		} finally {
			em.close();
		}
		return true;
	}

	/**
	 * Updated alle Eigenschaften von Kunden die ge�ndert werden sollen
	 * 
	 * @param kunden
	 *            das geupdatete Objekt
	 * @param em
	 *            Manager
	 * @return true wenn geupdated wurde
	 */
	public boolean updateKunden(Kunden kunden, EntityManager em) {
		try {
			em.getTransaction().begin();
			Kunden kunden_db = em.find(kunden.getClass(), kunden.getId());
			kunden_db.setAdresse(kunden.getAdresse());
			kunden_db.setJobs(kunden.getJobs());
			kunden_db.setKunde(kunden.getKunde());
			kunden_db.setKundenkuerzel(kunden.getKundenkuerzel());
			kunden_db.setTelefon(kunden.getTelefon());
			try {
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.close();
				return false;
			}
		} finally {
			em.close();
		}
		return true;
	}
}
