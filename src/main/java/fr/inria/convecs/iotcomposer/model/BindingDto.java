/**
 * 
 */
package fr.inria.convecs.iotcomposer.model;

import fr.inria.convecs.iotcomposer.model.Binding.BindingType;

/**
 * @author ajayk
 *
 */
public class BindingDto {
	
	String id;
	String source;
	String target;
	BindingType type;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * @return the type
	 */
	public BindingType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(BindingType type) {
		this.type = type;
	}

}
