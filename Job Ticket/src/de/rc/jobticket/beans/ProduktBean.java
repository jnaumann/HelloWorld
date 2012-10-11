/**
 * 
 */
package de.rc.jobticket.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import de.rc.ProduktModel;

/**
 *  juni 2012
 * <p>Verwaltungsklasse für das Produkt zwischen Layout und Datenbank</p>
 * 
 * @author janine und atilla
 *
 */
@ManagedBean
public class ProduktBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5760974394614810997L;
	
	private List<ProduktModel> produktSammlung;
	private boolean showMinusProduktButton;
	
	/**
	 *  Initiiert die ProduktBean  
	 */
	public ProduktBean(){
		
		produktSammlung = new ArrayList<ProduktModel>();
		ProduktModel p = new ProduktModel();
		produktSammlung.add(p);
	}
	
	/**
	 * @return the produktSammlung
	 */
	public List<ProduktModel> getProduktSammlung() {
		return produktSammlung;
	}

	/**
	 * @param produktSammlung the produktSammlung to set
	 */
	public void setProduktSammlung(List<ProduktModel> produktSammlung) {
		this.produktSammlung = produktSammlung;
	}

	/**
	 * @return the minusProduktButton
	 */
	public boolean isShowMinusProduktButton() {
		return showMinusProduktButton;
	}
	/**
	 * Trigger für den MinusProduktButton
	 */

	public void renderMinusProduktButton() {
		showMinusProduktButton = !showMinusProduktButton;
	}
	/**
	 * fügt ein leeres Produkt hinzu
	 */
	
	public void addProdukt() {
		ProduktModel p = new ProduktModel();
		produktSammlung.add(p);
	}
	
	/**
	 * Löscht ein ausgewähltes Produkt
	 */
	public void deleteProdukt(ActionEvent e) {
		produktSammlung.remove(Integer.parseInt(e.getComponent()
				.getClientId().split(":")[2]));

	}
	
	
}
