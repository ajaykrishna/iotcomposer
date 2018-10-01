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
    "ids@Set@ElementId"
})
public class PredicateIdPredicate {

    @JsonProperty("ids@Set@ElementId")
    private IdsSetElementId idsSetElementId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ids@Set@ElementId")
    public IdsSetElementId getIdsSetElementId() {
        return idsSetElementId;
    }

    @JsonProperty("ids@Set@ElementId")
    public void setIdsSetElementId(IdsSetElementId idsSetElementId) {
        this.idsSetElementId = idsSetElementId;
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
        return new ToStringBuilder(this).append("idsSetElementId", idsSetElementId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(idsSetElementId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PredicateIdPredicate) == false) {
            return false;
        }
        PredicateIdPredicate rhs = ((PredicateIdPredicate) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(idsSetElementId, rhs.idsSetElementId).isEquals();
    }

}
