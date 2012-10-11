/**
 * 
 */
package de.rc.jobticket.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 * Eine Testklasse, um die Verwendung und Verarbeitung von Tabs zu zeigen
 * 
 * @author janine & atilla
 * 
 */
@ManagedBean
public class TabTestBean {

	private int activeTab;

	private List<String> data;

	/**
	 * Erstellt die Liste
	 */
	public TabTestBean() {
		data = new ArrayList<String>();
	}

	/**
	 * @return umgekehrt ausgabe von data
	 */
	private List<String> reverseList() {
		List<String> data_temp = new ArrayList<String>();
		for (int i = data.size(); i > 0; i--) {
			data_temp.add(data.get(i - 1));
		}
		return data_temp;
	}

	/**
	 * @return the data
	 */
	public List<String> getData() {
		System.out.println("jo");
		return reverseList();
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<String> data) {
		this.data = data;
	}

	/**
	 * FŸgt einen Tab der liste hinzu
	 */
	public void addTab() {
		activeTab = data.size();
		System.out.println(activeTab);
		if (data.size() <= 15)
			data.add("" + (data.size()+1));
	}
	
	/**
	 * @return the activeTab
	 */
	public int getActiveTab() {
		return activeTab;
	}

	/**
	 * @param activeTab the activeTab to set
	 */
	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	public void onTabChange(TabChangeEvent event) {
		activeTab = (data.size() - 1)
				- ((TabView) event.getComponent()).getActiveIndex();
		System.out.println(activeTab);
	}

}
