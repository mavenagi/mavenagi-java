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
@JsonDeserialize(builder = EntityIdFilter.Builder.class)
public final class EntityIdFilter {
    private final String referenceId;

    private final String appId;

    private final Map<String, Object> additionalProperties;

    private EntityIdFilter(String referenceId, String appId, Map<String, Object> additionalProperties) {
        this.referenceId = referenceId;
        this.appId = appId;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("referenceId")
    public String getReferenceId() {
        return referenceId;
    }

    @JsonProperty("appId")
    public String getAppId() {
        return appId;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof EntityIdFilter && equalTo((EntityIdFilter) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(EntityIdFilter other) {
        return referenceId.equals(other.referenceId) && appId.equals(other.appId);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.referenceId, this.appId);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ReferenceIdStage builder() {
        return new Builder();
    }

    public interface ReferenceIdStage {
        AppIdStage referenceId(@NotNull String referenceId);

        Builder from(EntityIdFilter other);
    }

    public interface AppIdStage {
        _FinalStage appId(@NotNull String appId);
    }

    public interface _FinalStage {
        EntityIdFilter build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements ReferenceIdStage, AppIdStage, _FinalStage {
        private String referenceId;

        private String appId;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(EntityIdFilter other) {
            referenceId(other.getReferenceId());
            appId(other.getAppId());
            return this;
        }

        @java.lang.Override
        @JsonSetter("referenceId")
        public AppIdStage referenceId(@NotNull String referenceId) {
            this.referenceId = Objects.requireNonNull(referenceId, "referenceId must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("appId")
        public _FinalStage appId(@NotNull String appId) {
            this.appId = Objects.requireNonNull(appId, "appId must not be null");
            return this;
        }

        @java.lang.Override
        public EntityIdFilter build() {
            return new EntityIdFilter(referenceId, appId, additionalProperties);
        }
    }
}
