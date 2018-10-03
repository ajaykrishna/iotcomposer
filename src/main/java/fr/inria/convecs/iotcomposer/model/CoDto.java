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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"device",
"inItfs",
"outItfs"
})
public class CoDto {

@JsonProperty("device")
private String device;
@JsonProperty("inItfs")
private List<String> inItfs = null;
@JsonProperty("outItfs")
private List<String> outItfs = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("device")
public String getDevice() {
return device;
}

@JsonProperty("device")
public void setDevice(String device) {
this.device = device;
}

@JsonProperty("inItfs")
public List<String> getInItfs() {
return inItfs;
}

@JsonProperty("inItfs")
public void setInItfs(List<String> inItfs) {
this.inItfs = inItfs;
}

@JsonProperty("outItfs")
public List<String> getOutItfs() {
return outItfs;
}

@JsonProperty("outItfs")
public void setOutItfs(List<String> outItfs) {
this.outItfs = outItfs;
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
return new ToStringBuilder(this).append("device", device).append("inItfs", inItfs).append("outItfs", outItfs).append("additionalProperties", additionalProperties).toString();
}

@Override
public int hashCode() {
return new HashCodeBuilder().append(inItfs).append(additionalProperties).append(device).append(outItfs).toHashCode();
}

@Override
public boolean equals(Object other) {
if (other == this) {
return true;
}
if ((other instanceof CoDto) == false) {
return false;
}
CoDto rhs = ((CoDto) other);
return new EqualsBuilder().append(inItfs, rhs.inItfs).append(additionalProperties, rhs.additionalProperties).append(device, rhs.device).append(outItfs, rhs.outItfs).isEquals();
}

}
