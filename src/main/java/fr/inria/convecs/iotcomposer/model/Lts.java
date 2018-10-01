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
public class Lts {

	List<State> states;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	State initialState;
	
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	List<AppInterface> actions;

	/**
	 * @return the states
	 */
	public List<State> getStates() {
		return states;
	}

	/**
	 * @param states
	 *            the states to set
	 */
	public void setStates(List<State> states) {
		this.states = states;
	}

	/**
	 * @return the initialState
	 */
	public State getInitialState() {
		return initialState;
	}

	/**
	 * @param initialState
	 *            the initialState to set
	 */
	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	/**
	 * @return the actions
	 */
	public List<AppInterface> getActions() {
		return actions;
	}

	/**
	 * @param actions
	 *            the actions to set
	 */
	public void setActions(List<AppInterface> actions) {
		this.actions = actions;
	}

}
