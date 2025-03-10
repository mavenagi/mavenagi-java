/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.commons.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = EntityIdBase.Builder.class)
public final class EntityIdBase implements IEntityIdBase {
    private final String referenceId;

    private final Map<String, Object> additionalProperties;

    private EntityIdBase(String referenceId, Map<String, Object> additionalProperties) {
        this.referenceId = referenceId;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Externally supplied ID to uniquely identify this object. Is globally unique when combined with all other entityId fields (type, appId, organizationId, agentId)
     */
    @JsonProperty("referenceId")
    @java.lang.Override
    public String getReferenceId() {
        return referenceId;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof EntityIdBase && equalTo((EntityIdBase) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(EntityIdBase other) {
        return referenceId.equals(other.referenceId);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.referenceId);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ReferenceIdStage builder() {
        return new Builder();
    }

    public interface ReferenceIdStage {
        _FinalStage referenceId(@NotNull String referenceId);

        Builder from(EntityIdBase other);
    }

    public interface _FinalStage {
        EntityIdBase build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements ReferenceIdStage, _FinalStage {
        private String referenceId;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(EntityIdBase other) {
            referenceId(other.getReferenceId());
            return this;
        }

        /**
         * <p>Externally supplied ID to uniquely identify this object. Is globally unique when combined with all other entityId fields (type, appId, organizationId, agentId)</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("referenceId")
        public _FinalStage referenceId(@NotNull String referenceId) {
            this.referenceId = Objects.requireNonNull(referenceId, "referenceId must not be null");
            return this;
        }

        @java.lang.Override
        public EntityIdBase build() {
            return new EntityIdBase(referenceId, additionalProperties);
        }
    }
}
