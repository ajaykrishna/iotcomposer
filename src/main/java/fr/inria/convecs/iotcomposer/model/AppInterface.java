/** */
package fr.inria.convecs.iotcomposer.model;

/** @author ajayk */

public class AppInterface {

	String id;
	InterfaceType type;

	/** @return the id */
	public String getId() {
		return id;
	}
	
	/** @param id the id to set */
	public void setId(String id) {
		this.id = id;
	}
	
	/** @return the type */
	public InterfaceType getType() {
		return type;
	}
	
	/** @param type the type to set */
	public void setType(InterfaceType type) {
		this.type = type;
	}
}
