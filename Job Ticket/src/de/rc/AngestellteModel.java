package de.rc;

/**
 * 
 */

import java.io.Serializable;
import java.util.List;
import de.rc.jobticket.beans.AngestellteBean;
import de.rc.jobticket.entities.Angestellte;
import de.rc.jobticket.entities.Angestelltenbezeichnungen;

/**
 * juni 2012
 * <p>
 * Modelklasse für die Angestellten
 * </p>
 * 
 * @author janine & atilla
 * 
 */

public class AngestellteModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String angestelltenbezeichnung;
	private String angestellte_vorname;
	private String angestellte_nachname;
	private boolean showTable;
	private DBZugriff dbAccess;
	private List<Angestelltenbezeichnungen> ange;
	private List<Angestellte> angestellte;
	private boolean emptyField_vorname;
	private boolean emptyField_nachname;
	private boolean emptyField_bezeichnung;
	private String angestellte_name;
	private boolean renderMinus;
	private AngestellteBean a;

	/**
	 * Erstellt einen Datenbankzugriff
	 */
	public AngestellteModel() {
		dbAccess = new DBZugriff();

	}

	/**
	 * Erstellt einen Datenbankzugriff
	 */
	public AngestellteModel(AngestellteBean a) {
		dbAccess = new DBZugriff();
		this.a = a;

	}

	/**
	 * Trigger für das Anzeigen der Angestelltentabelle wird im Hauptlayout
	 * nicht verwendet
	 */
	public void zeigeTabelle() {
		showTable = true;
	}

	/**
	 * Autokomplete für die Angestelltenbezeichnung
	 * 
	 * @param str
	 *            die im Layout eingegebenen Buchstaben
	 * @return eine Liste der übereinstimmenden Einträge aus der Datenbank
	 */
	public List<String> completeAngestelltenbezeichnung(String str) {
		return dbAccess.completeAngestelltenbezeichnung(str);
	}

	/**
	 * Autokomplete für den Angestelltennamen
	 * 
	 * @param str
	 *            die im Layout eingegebenen Buchstaben
	 * @return eine Liste der übereinstimmenden Einträge aus der Datenbank
	 */
	public List<String> completeAngestelltenname(String str) {
		return dbAccess.completeAngestelltenname(str);
	}

	/**
	 * Erstellt fals möglich einen Angestellten Überprüft ob die Pflichtfelder
	 * ausgefüllt wurden und gibt gegebenfals eine Fehlermeldung zurück
	 */
	public void erstelleAngestellte() {
		if (angestelltenbezeichnung.trim().compareTo("") == 0) {
			System.err.println("Bezeichnung ist leer.");
			emptyField_bezeichnung = true;
		} else {
			emptyField_bezeichnung = false;
		}
		if (angestellte_nachname.trim().compareTo("") == 0) {
			System.err.println("Nachname ist leer.");
			emptyField_nachname = true;
		} else {
			emptyField_nachname = false;
		}
		if (angestellte_vorname.trim().compareTo("") == 0) {
			System.err.println("Vorname ist leer");
			emptyField_vorname = true;
		} else {
			emptyField_vorname = false;
		}
		if ((angestellte_vorname.trim().compareTo("") == 0)
				|| (angestellte_nachname.trim().compareTo("") == 0)
				|| (angestelltenbezeichnung.trim().compareTo("") == 0)) {
			return;
		}

		Angestellte angestellte = new Angestellte();
		Angestelltenbezeichnungen bez = dbAccess
				.findAngestelltenbezeichnungen(angestelltenbezeichnung);
		if (bez == null) {
			System.err.println(angestelltenbezeichnung
					+ " wurde nicht als Bezeichnung gefunden");
			return;
		}
		angestellte.setNachname(angestellte_nachname);
		angestellte.setVorname(angestellte_vorname);
		angestellte.setAngestelltenbezeichnungen(bez);
		dbAccess.addElement(angestellte, dbAccess.createEntitymanager());
		zeigeTabelle();
		a.setDlgShouldBeHidden(null);
	}

	/**
	 * @return angestelltenbezeichnung
	 */
	public String getAngestelltenbezeichnung() {
		return angestelltenbezeichnung;
	}

	/**
	 * @param firstname
	 *            to set
	 */
	public void setAngestelltenbezeichnung(String firstname) {
		this.angestelltenbezeichnung = firstname;
		System.out.println(firstname);
	}

	/**
	 * @return the showPrint
	 */
	public boolean isShowTable() {
		return showTable;
	}

	/**
	 * @param showPrint
	 *            the showPrint to set
	 */
	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	/**
	 * Erstellt eine Liste aller Angestelltenbezeichnungen aus der Datenbank
	 * 
	 * @return the angestelltenbezeichnung Liste aller angestelltenbeichnungen
	 *         aus der Datenbank
	 */
	public List<Angestelltenbezeichnungen> getAnge() {
		ange = dbAccess.getDatalist(Angestelltenbezeichnungen.class,
				dbAccess.createEntitymanager());
		return ange;
	}

	/**
	 * @param ange
	 *            the ange to set
	 */
	public void setAnge(List<Angestelltenbezeichnungen> ange) {

		this.ange = ange;
	}

	/**
	 * Erstellt eine Liste aller Angestellten aus der Datenbank
	 * 
	 * @return the angestellte Liste aller Angestellten aus der Dtaenbank
	 */
	public List<Angestellte> getAngestellte() {
		angestellte = dbAccess.getDatalist(Angestellte.class,
				dbAccess.createEntitymanager());
		return angestellte;
	}

	/**
	 * @param angestellte
	 *            the angestellte to set
	 */
	public void setAngestellte(List<Angestellte> angestellte) {
		this.angestellte = angestellte;
	}

	/**
	 * @return the angestellte_vorname
	 */
	public String getAngestellte_vorname() {
		return angestellte_vorname;
	}

	/**
	 * @param angestellte_vorname
	 *            the angestellte_vorname to set
	 */
	public void setAngestellte_vorname(String angestellte_vorname) {
		this.angestellte_vorname = angestellte_vorname;
	}

	/**
	 * @return the angestellte_nachname
	 */
	public String getAngestellte_nachname() {
		return angestellte_nachname;
	}

	/**
	 * @param angestellte_nachname
	 *            the angestellte_nachname to set
	 */
	public void setAngestellte_nachname(String angestellte_nachname) {
		this.angestellte_nachname = angestellte_nachname;
	}

	/**
	 * @return the emptyField
	 */
	public boolean isEmptyField_vorname() {
		return emptyField_vorname;
	}

	/**
	 * @param emptyField
	 *            the emptyField to set
	 */
	public void setEmptyField_vorname(boolean emptyField_vorname) {
		this.emptyField_vorname = emptyField_vorname;
	}

	/**
	 * @return the emptyField_nachname
	 */
	public boolean isEmptyField_nachname() {
		return emptyField_nachname;
	}

	/**
	 * @param emptyField_nachname
	 *            the emptyField_nachname to set
	 */
	public void setEmptyField_nachname(boolean emptyField_nachname) {
		this.emptyField_nachname = emptyField_nachname;
	}

	/**
	 * @return the emptyField_bezeichnung
	 */
	public boolean isEmptyField_bezeichnung() {
		return emptyField_bezeichnung;
	}

	/**
	 * @param emptyField_bezeichnung
	 *            the emptyField_bezeichnung to set
	 */
	public void setEmptyField_bezeichnung(boolean emptyField_bezeichnung) {
		this.emptyField_bezeichnung = emptyField_bezeichnung;
	}

	/**
	 * @return the angestellte_name
	 */
	public String getAngestellte_name() {
		return angestellte_name;
	}

	/**
	 * @param angestellte_name
	 *            the angestellte_name to set
	 */
	public void setAngestellte_name(String angestellte_name) {
		System.out.println(angestellte_name + " " + this);
		this.angestellte_name = angestellte_name;
	}

	/**
	 * @return the renderMinus
	 */
	public boolean isRenderMinus() {
		return renderMinus;
	}
	/**
	 * Trigger für das Darstellen der AngestelltenMinusButtons
	 */

	public void renderMinusButton() {
		renderMinus = !renderMinus;
	}

}