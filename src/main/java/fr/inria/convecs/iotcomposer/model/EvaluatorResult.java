/**
 * 
 */
package fr.inria.convecs.iotcomposer.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author ajayk
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"result",
	"message",
	"counterexample"
})
public class EvaluatorResult {

	@JsonProperty("result")
	private Boolean result;
	@JsonProperty("message")
	private String message;
	@JsonProperty("counterexample")
	private List<String> counterexample = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("result")
	public Boolean getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(Boolean result) {
		this.result = result;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("counterexample")
	public List<String> getCounterexample() {
		return counterexample;
	}

	@JsonProperty("counterexample")
	public void setCounterexample(List<String> counterexample) {
		this.counterexample = counterexample;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("result", result).append("message", message).append("counterexample", counterexample).append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(message).append(result).append(counterexample).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof EvaluatorResult) == false) {
			return false;
		}
		EvaluatorResult rhs = ((EvaluatorResult) other);
		return new EqualsBuilder().append(message, rhs.message).append(result, rhs.result).append(counterexample, rhs.counterexample).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
