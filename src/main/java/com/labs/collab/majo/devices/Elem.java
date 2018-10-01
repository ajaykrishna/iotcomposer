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
    "owner",
    "factory",
    "macAddress",
    "capabilities@Set@String",
    "kind",
    "definedRules@Set@DefinedRule",
    "name",
    "icon",
    "id",
    "version",
    "brand",
    "devicetype@DeviceTypeDescriptor",
    "space"
})
public class Elem {

    @JsonProperty("owner")
    private String owner;
    @JsonProperty("factory")
    private String factory;
    @JsonProperty("macAddress")
    private String macAddress;
    @JsonProperty("capabilities@Set@String")
    private CapabilitiesSetString capabilitiesSetString;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("definedRules@Set@DefinedRule")
    private DefinedRulesSetDefinedRule definedRulesSetDefinedRule;
    @JsonProperty("name")
    private String name;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("id")
    private String id;
    @JsonProperty("version")
    private String version;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("devicetype@DeviceTypeDescriptor")
    private DevicetypeDeviceTypeDescriptor devicetypeDeviceTypeDescriptor;
    @JsonProperty("space")
    private String space;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @JsonProperty("factory")
    public String getFactory() {
        return factory;
    }

    @JsonProperty("factory")
    public void setFactory(String factory) {
        this.factory = factory;
    }

    @JsonProperty("macAddress")
    public String getMacAddress() {
        return macAddress;
    }

    @JsonProperty("macAddress")
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @JsonProperty("capabilities@Set@String")
    public CapabilitiesSetString getCapabilitiesSetString() {
        return capabilitiesSetString;
    }

    @JsonProperty("capabilities@Set@String")
    public void setCapabilitiesSetString(CapabilitiesSetString capabilitiesSetString) {
        this.capabilitiesSetString = capabilitiesSetString;
    }

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("definedRules@Set@DefinedRule")
    public DefinedRulesSetDefinedRule getDefinedRulesSetDefinedRule() {
        return definedRulesSetDefinedRule;
    }

    @JsonProperty("definedRules@Set@DefinedRule")
    public void setDefinedRulesSetDefinedRule(DefinedRulesSetDefinedRule definedRulesSetDefinedRule) {
        this.definedRulesSetDefinedRule = definedRulesSetDefinedRule;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("devicetype@DeviceTypeDescriptor")
    public DevicetypeDeviceTypeDescriptor getDevicetypeDeviceTypeDescriptor() {
        return devicetypeDeviceTypeDescriptor;
    }

    @JsonProperty("devicetype@DeviceTypeDescriptor")
    public void setDevicetypeDeviceTypeDescriptor(DevicetypeDeviceTypeDescriptor devicetypeDeviceTypeDescriptor) {
        this.devicetypeDeviceTypeDescriptor = devicetypeDeviceTypeDescriptor;
    }

    @JsonProperty("space")
    public String getSpace() {
        return space;
    }

    @JsonProperty("space")
    public void setSpace(String space) {
        this.space = space;
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
        return new ToStringBuilder(this).append("owner", owner).append("factory", factory).append("macAddress", macAddress).append("capabilitiesSetString", capabilitiesSetString).append("kind", kind).append("definedRulesSetDefinedRule", definedRulesSetDefinedRule).append("name", name).append("icon", icon).append("id", id).append("version", version).append("brand", brand).append("devicetypeDeviceTypeDescriptor", devicetypeDeviceTypeDescriptor).append("space", space).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(icon).append(macAddress).append(space).append(kind).append(version).append(id).append(devicetypeDeviceTypeDescriptor).append(additionalProperties).append(name).append(capabilitiesSetString).append(owner).append(brand).append(factory).append(definedRulesSetDefinedRule).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Elem) == false) {
            return false;
        }
        Elem rhs = ((Elem) other);
        return new EqualsBuilder().append(icon, rhs.icon).append(macAddress, rhs.macAddress).append(space, rhs.space).append(kind, rhs.kind).append(version, rhs.version).append(id, rhs.id).append(devicetypeDeviceTypeDescriptor, rhs.devicetypeDeviceTypeDescriptor).append(additionalProperties, rhs.additionalProperties).append(name, rhs.name).append(capabilitiesSetString, rhs.capabilitiesSetString).append(owner, rhs.owner).append(brand, rhs.brand).append(factory, rhs.factory).append(definedRulesSetDefinedRule, rhs.definedRulesSetDefinedRule).isEquals();
    }

}
