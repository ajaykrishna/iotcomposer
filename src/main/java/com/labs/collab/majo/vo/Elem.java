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
    "remoteparts@Set@CocoId",
    "predicate@IdPredicate",
    "kind",
    "startingmode",
    "definedRules@Set@DefinedRule",
    "name",
    "id"
})
public class Elem {

    @JsonProperty("remoteparts@Set@CocoId")
    private RemotepartsSetCocoId remotepartsSetCocoId;
    @JsonProperty("predicate@IdPredicate")
    private PredicateIdPredicate predicateIdPredicate;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("startingmode")
    private String startingmode;
    @JsonProperty("definedRules@Set@DefinedRule")
    private DefinedRulesSetDefinedRule definedRulesSetDefinedRule;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("remoteparts@Set@CocoId")
    public RemotepartsSetCocoId getRemotepartsSetCocoId() {
        return remotepartsSetCocoId;
    }

    @JsonProperty("remoteparts@Set@CocoId")
    public void setRemotepartsSetCocoId(RemotepartsSetCocoId remotepartsSetCocoId) {
        this.remotepartsSetCocoId = remotepartsSetCocoId;
    }

    @JsonProperty("predicate@IdPredicate")
    public PredicateIdPredicate getPredicateIdPredicate() {
        return predicateIdPredicate;
    }

    @JsonProperty("predicate@IdPredicate")
    public void setPredicateIdPredicate(PredicateIdPredicate predicateIdPredicate) {
        this.predicateIdPredicate = predicateIdPredicate;
    }

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("startingmode")
    public String getStartingmode() {
        return startingmode;
    }

    @JsonProperty("startingmode")
    public void setStartingmode(String startingmode) {
        this.startingmode = startingmode;
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

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
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
        return new ToStringBuilder(this).append("remotepartsSetCocoId", remotepartsSetCocoId).append("predicateIdPredicate", predicateIdPredicate).append("kind", kind).append("startingmode", startingmode).append("definedRulesSetDefinedRule", definedRulesSetDefinedRule).append("name", name).append("id", id).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(predicateIdPredicate).append(additionalProperties).append(name).append(startingmode).append(remotepartsSetCocoId).append(kind).append(definedRulesSetDefinedRule).toHashCode();
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
        return new EqualsBuilder().append(id, rhs.id).append(predicateIdPredicate, rhs.predicateIdPredicate).append(additionalProperties, rhs.additionalProperties).append(name, rhs.name).append(startingmode, rhs.startingmode).append(remotepartsSetCocoId, rhs.remotepartsSetCocoId).append(kind, rhs.kind).append(definedRulesSetDefinedRule, rhs.definedRulesSetDefinedRule).isEquals();
    }

}
