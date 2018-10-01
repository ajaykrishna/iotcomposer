/**
 * 
 */
package fr.inria.convecs.iotcomposer.model;

import java.util.List;

/**
 * @author ajayk
 *
 */
public class ConnectedObject {

	String id;
	List<AppInterface> appInterfaces;
	Lts lts;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the appInterfaces
	 */
	public List<AppInterface> getAppInterfaces() {
		return appInterfaces;
	}

	/**
	 * @param appInterfaces
	 *            the appInterfaces to set
	 */
	public void setAppInterfaces(List<AppInterface> appInterfaces) {
		this.appInterfaces = appInterfaces;
	}

	/**
	 * @return the lts
	 */
	public Lts getLts() {
		return lts;
	}

	/**
	 * @param lts
	 *            the lts to set
	 */
	public void setLts(Lts lts) {
		this.lts = lts;
	}

}
