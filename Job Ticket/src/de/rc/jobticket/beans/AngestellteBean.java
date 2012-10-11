/**
 * 
 */
package de.rc.jobticket.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import de.rc.AngestellteModel;
import de.rc.ColumnModel;

/**
 * * juni 2012
 * <p>
 * Dynamische Verwaltungsklasse für die Angestellten zwischen Layout und
 * Datenbank
 * </p>
 * 
 * @author janine und atilla
 * 
 */
public class AngestellteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5747001996571770612L;

	private List<AngestellteModel> angestelltenSammlung;
	private List<ColumnModel> columns;
	private List<Boolean> shouldOutputtextRender;
	private List<Boolean> shouldInputtextRender;
	private List<Boolean> shouldButtonRender;
	private boolean dlgShouldBeHidden;

	/**
	 * Erzeugung einer leere Liste von Angestellten
	 */
	public AngestellteBean() {

		angestelltenSammlung = new ArrayList<AngestellteModel>();
		AngestellteModel a = new AngestellteModel(this);
		a.setAngestelltenbezeichnung("Angestellter");
		a.setAngestellte_name("Klaus Günther");
		angestelltenSammlung.add(a);
		a = new AngestellteModel();
		a.setAngestelltenbezeichnung("Grafiker");
		a.setAngestellte_name("Olaf Ziegler");
		angestelltenSammlung.add(a);

		createColumns();

	}
	/**
	 * Fügt einen leeren Angestellten hinzu
	 */
	public void addAngestellten() {
		AngestellteModel a = new AngestellteModel();
		a.setAngestelltenbezeichnung("Angestellter");
		a.setAngestellte_name("");
		angestelltenSammlung.add(a);
	}

	/**
	 * Enthält eine Liste aller Propertys aus dem AngestelltenModel
	 */
	public void createColumns() {
		columns = new ArrayList<ColumnModel>();
		// shouldButtonRender = new ArrayList<Boolean>();

		shouldOutputtextRender = new ArrayList<Boolean>();
		shouldOutputtextRender.add(new Boolean(true));
		shouldOutputtextRender.add(new Boolean(false));

		shouldInputtextRender = new ArrayList<Boolean>();
		shouldInputtextRender.add(new Boolean(false));
		shouldInputtextRender.add(new Boolean(true));

		columns.add(new ColumnModel("Bezeichnung", "angestelltenbezeichnung"));
		columns.add(new ColumnModel("Angestellte_name", "angestellte_name"));

	}

	/**
	 * @return the angestelltenSammlung
	 */
	public List<AngestellteModel> getAngestelltenSammlung() {
		return angestelltenSammlung;
	}

	/**
	 * @param angestelltenSammlung
	 *            the angestelltenSammlung to set
	 */
	public void setAngestelltenSammlung(
			List<AngestellteModel> angestelltenSammlung) {
		this.angestelltenSammlung = angestelltenSammlung;
	}

	/**
	 * @return the columns
	 */
	public List<ColumnModel> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	/**
	 * @return the shouldOutputtextRender
	 */
	public List<Boolean> getShouldOutputtextRender() {
		return shouldOutputtextRender;
	}

	/**
	 * @param shouldOutputtextRender
	 *            the shouldOutputtextRender to set
	 */
	public void setShouldOutputtextRender(List<Boolean> shouldOutputtextRender) {
		this.shouldOutputtextRender = shouldOutputtextRender;
	}

	/**
	 * @return the shouldInputtextRender
	 */
	public List<Boolean> getShouldInputtextRender() {
		return shouldInputtextRender;
	}

	/**
	 * @param shouldInputtextRender
	 *            the shouldInputtextRender to set
	 */
	public void setShouldInputtextRender(List<Boolean> shouldInputtextRender) {
		this.shouldInputtextRender = shouldInputtextRender;
	}

	/**
	 * @return the shouldButtonRender
	 */
	public List<Boolean> getShouldButtonRender() {
		return shouldButtonRender;
	}

	/**
	 * @return the dlgShouldBeHidden
	 */
	public boolean isDlgShouldBeHidden() {
		return dlgShouldBeHidden;
	}

	/**
	 * @param dlgShouldBeHidden
	 *            the dlgShouldBeHidden to set
	 */
	public void setDlgShouldBeHidden(ActionEvent e) {
		this.dlgShouldBeHidden = !this.dlgShouldBeHidden;
	}

	/**
	 * @param shouldButtonRender
	 *            the shouldButtonRender to set
	 */
	public void setShouldButtonRender(List<Boolean> shouldButtonRender) {
		this.shouldButtonRender = shouldButtonRender;
	}
	/**
	 * Löscht einen ausgewählten Angestellten
	 */
	public void deleteAngestellten(ActionEvent e) {
		angestelltenSammlung.remove(Integer.parseInt(e.getComponent()
				.getClientId().split(":")[2]));

	}

}
