/**
 * 
 */
package fr.inria.convecs.iotcomposer.model;

/**
 * @author ajayk
 *
 */
public class Step {

	public enum StepOperation {
		ADD, BIND, START, STOP
	}

	String id;
	String element;
	StepOperation operation;

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
	 * @return the element
	 */
	public String getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(String element) {
		this.element = element;
	}

	/**
	 * @return the operation
	 */
	public StepOperation getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(StepOperation operation) {
		this.operation = operation;
	}
}
