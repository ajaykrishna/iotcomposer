package com.labs.collab.majo.vo;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"flags",
	"elems",
	"env"
})
public class CoCoData {

	@JsonProperty("flags")
	private Object flags;
	@JsonProperty("env")
	private Object env;
	@JsonProperty("elems")
	private List<Elem> elems = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("flags")
	public Object getFlags() {
		return flags;
	}

	@JsonProperty("flags")
	public void setFlags(Object flags) {
		this.flags = flags;
	}

	@JsonProperty("elems")
	public List<Elem> getElems() {
		return elems;
	}

	@JsonProperty("elems")
	public void setElems(List<Elem> elems) {
		this.elems = elems;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	/* public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("flags", flags).append("elems", elems).append("additionalProperties", additionalProperties).toString();
	}

	/**
	 * @return the env
	 */
	@JsonProperty("env")
	public Object getEnv() {
		return env;
	}

	/**
	 * @param env the env to set
	 */
	@JsonProperty("env")
	public void setEnv(Object env) {
		this.env = env;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(flags).append(additionalProperties).append(elems).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof CoCoData) == false) {
			return false;
		}
		CoCoData rhs = ((CoCoData) other);
		return new EqualsBuilder().append(flags, rhs.flags).append(additionalProperties, rhs.additionalProperties).append(elems, rhs.elems).isEquals();
	}

}
