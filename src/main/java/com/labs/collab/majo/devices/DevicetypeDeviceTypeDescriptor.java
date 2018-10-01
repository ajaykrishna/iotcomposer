package com.labs.collab.majo.devices;

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
    "devManName",
    "devType",
    "devVersion"
})
public class DevicetypeDeviceTypeDescriptor {

    @JsonProperty("devManName")
    private String devManName;
    @JsonProperty("devType")
    private String devType;
    @JsonProperty("devVersion")
    private String devVersion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("devManName")
    public String getDevManName() {
        return devManName;
    }

    @JsonProperty("devManName")
    public void setDevManName(String devManName) {
        this.devManName = devManName;
    }

    @JsonProperty("devType")
    public String getDevType() {
        return devType;
    }

    @JsonProperty("devType")
    public void setDevType(String devType) {
        this.devType = devType;
    }

    @JsonProperty("devVersion")
    public String getDevVersion() {
        return devVersion;
    }

    @JsonProperty("devVersion")
    public void setDevVersion(String devVersion) {
        this.devVersion = devVersion;
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
        return new ToStringBuilder(this).append("devManName", devManName).append("devType", devType).append("devVersion", devVersion).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(devVersion).append(devType).append(additionalProperties).append(devManName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DevicetypeDeviceTypeDescriptor) == false) {
            return false;
        }
        DevicetypeDeviceTypeDescriptor rhs = ((DevicetypeDeviceTypeDescriptor) other);
        return new EqualsBuilder().append(devVersion, rhs.devVersion).append(devType, rhs.devType).append(additionalProperties, rhs.additionalProperties).append(devManName, rhs.devManName).isEquals();
    }

}
