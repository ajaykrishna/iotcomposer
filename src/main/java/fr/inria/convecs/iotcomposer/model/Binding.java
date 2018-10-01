/** */
package fr.inria.convecs.iotcomposer.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author ajayk
 */

public class Binding {

	public enum BindingType {
		WEAK,
		STRONG
	}

	String id;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	AppInterface source;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	AppInterface target;
	BindingType type;

	/** @return the id */
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

	/** @return the source */
	public AppInterface getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(AppInterface source) {
		this.source = source;
	}

	/** @return the target */
	public AppInterface getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(AppInterface target) {
		this.target = target;
	}

	/** @return the type */
	public BindingType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(BindingType type) {
		this.type = type;
	}
}
