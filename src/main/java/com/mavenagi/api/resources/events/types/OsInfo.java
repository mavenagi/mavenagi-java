/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.events.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = OsInfo.Builder.class)
public final class OsInfo {
    private final Optional<OsType> type;

    private final Optional<String> name;

    private final Optional<String> version;

    private final Map<String, Object> additionalProperties;

    private OsInfo(
            Optional<OsType> type,
            Optional<String> name,
            Optional<String> version,
            Map<String, Object> additionalProperties) {
        this.type = type;
        this.name = name;
        this.version = version;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("type")
    public Optional<OsType> getType() {
        return type;
    }

    @JsonProperty("name")
    public Optional<String> getName() {
        return name;
    }

    @JsonProperty("version")
    public Optional<String> getVersion() {
        return version;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof OsInfo && equalTo((OsInfo) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(OsInfo other) {
        return type.equals(other.type) && name.equals(other.name) && version.equals(other.version);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.type, this.name, this.version);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Optional<OsType> type = Optional.empty();

        private Optional<String> name = Optional.empty();

        private Optional<String> version = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(OsInfo other) {
            type(other.getType());
            name(other.getName());
            version(other.getVersion());
            return this;
        }

        @JsonSetter(value = "type", nulls = Nulls.SKIP)
        public Builder type(Optional<OsType> type) {
            this.type = type;
            return this;
        }

        public Builder type(OsType type) {
            this.type = Optional.ofNullable(type);
            return this;
        }

        @JsonSetter(value = "name", nulls = Nulls.SKIP)
        public Builder name(Optional<String> name) {
            this.name = name;
            return this;
        }

        public Builder name(String name) {
            this.name = Optional.ofNullable(name);
            return this;
        }

        @JsonSetter(value = "version", nulls = Nulls.SKIP)
        public Builder version(Optional<String> version) {
            this.version = version;
            return this;
        }

        public Builder version(String version) {
            this.version = Optional.ofNullable(version);
            return this;
        }

        public OsInfo build() {
            return new OsInfo(type, name, version, additionalProperties);
        }
    }
}
