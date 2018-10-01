package com.labs.collab.majo.vo;

import java.util.HashMap;
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
    "1",
    "2"
})
public class IdsSetElementId {

    @JsonProperty("1")
    private String _1;
    @JsonProperty("2")
    private String _2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("1")
    public String get1() {
        return _1;
    }

    @JsonProperty("1")
    public void set1(String _1) {
        this._1 = _1;
    }

    @JsonProperty("2")
    public String get2() {
        return _2;
    }

    @JsonProperty("2")
    public void set2(String _2) {
        this._2 = _2;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    /*public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("_1", _1).append("_2", _2).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(_1).append(_2).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof IdsSetElementId) == false) {
            return false;
        }
        IdsSetElementId rhs = ((IdsSetElementId) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(_1, rhs._1).append(_2, rhs._2).isEquals();
    }

}
