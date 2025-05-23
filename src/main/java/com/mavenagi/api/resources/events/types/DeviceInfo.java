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
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = DeviceInfo.Builder.class)
public final class DeviceInfo {
    private final DeviceType type;

    private final Optional<String> name;

    private final Optional<String> version;

    private final Optional<OsInfo> osInfo;

    private final Map<String, Object> additionalProperties;

    private DeviceInfo(
            DeviceType type,
            Optional<String> name,
            Optional<String> version,
            Optional<OsInfo> osInfo,
            Map<String, Object> additionalProperties) {
        this.type = type;
        this.name = name;
        this.version = version;
        this.osInfo = osInfo;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("type")
    public DeviceType getType() {
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

    @JsonProperty("osInfo")
    public Optional<OsInfo> getOsInfo() {
        return osInfo;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof DeviceInfo && equalTo((DeviceInfo) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(DeviceInfo other) {
        return type.equals(other.type)
                && name.equals(other.name)
                && version.equals(other.version)
                && osInfo.equals(other.osInfo);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.type, this.name, this.version, this.osInfo);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static TypeStage builder() {
        return new Builder();
    }

    public interface TypeStage {
        _FinalStage type(@NotNull DeviceType type);

        Builder from(DeviceInfo other);
    }

    public interface _FinalStage {
        DeviceInfo build();

        _FinalStage name(Optional<String> name);

        _FinalStage name(String name);

        _FinalStage version(Optional<String> version);

        _FinalStage version(String version);

        _FinalStage osInfo(Optional<OsInfo> osInfo);

        _FinalStage osInfo(OsInfo osInfo);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements TypeStage, _FinalStage {
        private DeviceType type;

        private Optional<OsInfo> osInfo = Optional.empty();

        private Optional<String> version = Optional.empty();

        private Optional<String> name = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(DeviceInfo other) {
            type(other.getType());
            name(other.getName());
            version(other.getVersion());
            osInfo(other.getOsInfo());
            return this;
        }

        @java.lang.Override
        @JsonSetter("type")
        public _FinalStage type(@NotNull DeviceType type) {
            this.type = Objects.requireNonNull(type, "type must not be null");
            return this;
        }

        @java.lang.Override
        public _FinalStage osInfo(OsInfo osInfo) {
            this.osInfo = Optional.ofNullable(osInfo);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "osInfo", nulls = Nulls.SKIP)
        public _FinalStage osInfo(Optional<OsInfo> osInfo) {
            this.osInfo = osInfo;
            return this;
        }

        @java.lang.Override
        public _FinalStage version(String version) {
            this.version = Optional.ofNullable(version);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "version", nulls = Nulls.SKIP)
        public _FinalStage version(Optional<String> version) {
            this.version = version;
            return this;
        }

        @java.lang.Override
        public _FinalStage name(String name) {
            this.name = Optional.ofNullable(name);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "name", nulls = Nulls.SKIP)
        public _FinalStage name(Optional<String> name) {
            this.name = name;
            return this;
        }

        @java.lang.Override
        public DeviceInfo build() {
            return new DeviceInfo(type, name, version, osInfo, additionalProperties);
        }
    }
}
