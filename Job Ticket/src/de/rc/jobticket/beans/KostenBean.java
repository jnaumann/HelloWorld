/**
 * 
 */
package de.rc.jobticket.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import de.rc.jobticket.entities.Angestellte;
import de.rc.jobticket.entities.Kosten;

/**
 * juni 2012
 * <p>
 * Verwaltungsklasse für die Kosten zwischen Layout und Datenbank
 * </p>
 * 
 * @author janine und atilla
 * 
 */
@ManagedBean
public class KostenBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String kostenInStd;
	private String kostenInEuro;
	private Kosten kosten;
	private Angestellte angestellte;
	private boolean abrechnungInEuro;
	private boolean abrechnungInStd;

	/**
	 * Setzt Standartwerte
	 */
	public KostenBean() {
		abrechnungInEuro = true;// Standart
		abrechnungInStd = false;// Standart
		kostenInEuro = "";
		kostenInStd = "";
	}

	/**
	 * @return the kostenInStd
	 */
	public String getKostenInStd() {
		return kostenInStd;
	}

	/**
	 * @param kostenInStd
	 *            the kostenInStd to set
	 */
	public void setKostenInStd(String kostenInStd) {
		this.kostenInStd = kostenInStd;
	}

	/**
	 * @return the kostenInEuro
	 */
	public String getKostenInEuro() {
		if (kostenInEuro.compareTo("") == 0) {
			return kostenInEuro;
		} else {
			return kostenInEuro + " €";
		}
	}

	/**
	 * @param kostenInEuro
	 *            the kostenInEuro to set
	 */
	public void setKostenInEuro(String kostenInEuro) {
		this.kostenInEuro = kostenInEuro.replace("€", "").trim();// entfernt
																	// eventuelles
																	// €-Zeichen
	}

	/**
	 * @return the kosten
	 */
	public Kosten getKosten() {
		return kosten;
	}

	/**
	 * @param kosten
	 *            the kosten to set
	 */
	public void setKosten(Kosten kosten) {
		this.kosten = kosten;
	}

	/**
	 * @return the angestellte
	 */
	public Angestellte getAngestellte() {
		return angestellte;
	}

	/**
	 * @param angestellte
	 *            the angestellte to set
	 */
	public void setAngestellte(Angestellte angestellte) {
		this.angestellte = angestellte;
	}

	/**
	 * @return the abrechnungInEuro
	 */
	public boolean isAbrechnungInEuro() {
		return abrechnungInEuro;
	}

	/**
	 * @param abrechnungInEuro
	 *            the abrechnungInEuro to set
	 */
	public void setAbrechnungInEuro(boolean abrechnungInEuro) {
		this.abrechnungInEuro = abrechnungInEuro;
	}

	/**
	 * @return the abrechnungInStd
	 */
	public boolean isAbrechnungInStd() {
		return abrechnungInStd;
	}

	/**
	 * @param abrechnungInStd
	 *            the abrechnungInStd to set
	 */
	public void setAbrechnungInStd(boolean abrechnungInStd) {
		this.abrechnungInStd = abrechnungInStd;
	}

	/**
	 * Bei Buttondruck wird der Abrechnungstyp gewechselt (trigger)
	 * 
	 * @param e
	 *            nicht relevant
	 */
	public void switchKosten(ActionEvent e) {
		abrechnungInEuro = !abrechnungInEuro;
		abrechnungInStd = !abrechnungInStd;
	}

	/**
	 * Überprüft die eingegeben Kosten in Euro nach "," und wandelt diesen in
	 * "." um damit ein gültiger Zahlenwert entsteht
	 * 
	 * @return kosten in euro
	 */
	public BigDecimal validateKostenEuro() {
		BigDecimal kosten_return = null;
		kostenInEuro = kostenInEuro.replace(",", ".");
		try {
			Integer.parseInt(kostenInEuro);
			kosten_return = new BigDecimal(kostenInEuro);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return kosten_return;
		} catch (Exception e) {
			e.printStackTrace();
			return kosten_return;
		}
		return kosten_return;
	}

	/**
	 * Überprüft die eingegeben Kosten in Stunden nach "," und wandelt diesen in
	 * "." um damit ein gültiger Zahlenwert entsteht
	 * 
	 * @return kosten in Stunden
	 */
	public BigDecimal validateKostenStd() {
		BigDecimal kosten_return = null;
		kostenInStd = kostenInStd.replace(",", ".");
		try {
			Integer.parseInt(kostenInStd);
			kosten_return = new BigDecimal(kostenInStd);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return kosten_return;
		} catch (Exception e) {
			e.printStackTrace();
			return kosten_return;
		}
		return kosten_return;
	}

}
