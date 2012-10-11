/**
 * 
 */
package de.rc;

import java.io.Serializable;

/**juni 2012
 * <p>Model zum dynamischen Erstellen der Colums</p>
 * @author janine und atilla
 *
 */
public class ColumnModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -213958105055357340L;
	private String header;
	private String property;

	/**
	 * @param header to set
	 * @param property to set
	 */
	public ColumnModel(String header, String property) {
		this.header = header;
		this.property = property;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

}
