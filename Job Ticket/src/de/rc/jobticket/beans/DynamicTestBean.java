/**
 * 
 */
package de.rc.jobticket.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import de.rc.jobticket.entities.KomplexerDatentyp;

/**
 * @author janine und atilla
 * 
 */
@ManagedBean
public class DynamicTestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7634828705060906934L;

	private List<KomplexerDatentyp> liste;
	private List<ColumnModel> columns;
	private String property = "bezeichnung";

	public DynamicTestBean() {
		columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("Bezeichnung", "bezeichnung"));
		columns.add(new ColumnModel("Eingabe", "eingabe"));

		liste = new ArrayList<KomplexerDatentyp>();
		KomplexerDatentyp komp = new KomplexerDatentyp();
		komp.setEingabe("nur hier sonst nirgens");
		komp.setBezeichnung("Kontakter");
		komp.setStandartButton(true);
		liste.add(komp);

		komp = new KomplexerDatentyp();
		komp.setBezeichnung("Grafiker");
		komp.setStandartButton(false);
		liste.add(komp);
	}

	/**
	 * @return the liste
	 */
	public List<KomplexerDatentyp> getListe() {
		return liste;
	}

	/**
	 * @param liste
	 *            the liste to set
	 */
	public void setListe(List<KomplexerDatentyp> liste) {
		this.liste = liste;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property
	 *            the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
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

	static public class ColumnModel implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6962502002037715573L;
		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}

}
