/**
 * 
 */
package fr.inria.convecs.iotcomposer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author ajayk
 *
 */
public class DeploymentPlan {
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	List<ConnectedObject> objects;
	
	List<Binding> bindings;
	List<Step> steps;
	
	/**
	 * @return the bindings
	 */
	public List<Binding> getBindings() {
		return bindings;
	}
	
	/**
	 * @param bindings the bindings to set
	 */
	public void setBindings(List<Binding> bindings) {
		this.bindings = bindings;
	}
	
	/**
	 * @return the steps
	 */
	public List<Step> getSteps() {
		return steps;
	}
	
	/**
	 * @param steps the steps to set
	 */
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	/**
	 * @return the objects
	 */
	public List<ConnectedObject> getObjects() {
		return objects;
	}

	/**
	 * @param objects the objects to set
	 */
	public void setObjects(List<ConnectedObject> objects) {
		this.objects = objects;
	}

}
