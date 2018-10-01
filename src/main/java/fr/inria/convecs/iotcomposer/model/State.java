/**
 * 
 */
package fr.inria.convecs.iotcomposer.model;

import java.util.List;

/**
 * @author ajayk
 *
 */
public class State {

	String id;
	List<Transition> transitions;

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
	 * @return the transitions
	 */
	public List<Transition> getTransitions() {
		return transitions;
	}

	/**
	 * @param transitions
	 *            the transitions to set
	 */
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

}
