/**
 * 
 */
package de.rc.jobticket.entities;

/**
 * @author janine
 * 
 */
public class KomplexerDatentyp {

	private String bezeichnung;
	private String eingabe;
	private boolean isStandartButton;

	public KomplexerDatentyp() {
		isStandartButton = true;
	}

	/**
	 * @return the bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * @param bezeichnung
	 *            the bezeichnung to set
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	/**
	 * @return the eingabe
	 */
	public String getEingabe() {
		return eingabe;
	}

	/**
	 * @param eingabe
	 *            the eingabe to set
	 */
	public void setEingabe(String eingabe) {
		this.eingabe = eingabe;
	}

	/**
	 * @return the isStandartButton
	 */
	public boolean isStandartButton() {
		return isStandartButton;
	}

	/**
	 * @param isStandartButton
	 *            the isStandartButton to set
	 */
	public void setStandartButton(boolean isStandartButton) {
		this.isStandartButton = isStandartButton;
	}

}
