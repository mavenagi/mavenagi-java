/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.analyticscommons.types;

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
@JsonDeserialize(builder = Range.Builder.class)
public final class Range {
    private final Optional<Double> from;

    private final Optional<Double> to;

    private final Map<String, Object> additionalProperties;

    private Range(Optional<Double> from, Optional<Double> to, Map<String, Object> additionalProperties) {
        this.from = from;
        this.to = to;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Lower bound of the range (inclusive).
     */
    @JsonProperty("from")
    public Optional<Double> getFrom() {
        return from;
    }

    /**
     * @return Upper bound of the range (exclusive).
     */
    @JsonProperty("to")
    public Optional<Double> getTo() {
        return to;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof Range && equalTo((Range) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(Range other) {
        return from.equals(other.from) && to.equals(other.to);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.from, this.to);
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
        private Optional<Double> from = Optional.empty();

        private Optional<Double> to = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(Range other) {
            from(other.getFrom());
            to(other.getTo());
            return this;
        }

        @JsonSetter(value = "from", nulls = Nulls.SKIP)
        public Builder from(Optional<Double> from) {
            this.from = from;
            return this;
        }

        public Builder from(Double from) {
            this.from = Optional.ofNullable(from);
            return this;
        }

        @JsonSetter(value = "to", nulls = Nulls.SKIP)
        public Builder to(Optional<Double> to) {
            this.to = to;
            return this;
        }

        public Builder to(Double to) {
            this.to = Optional.ofNullable(to);
            return this;
        }

        public Range build() {
            return new Range(from, to, additionalProperties);
        }
    }
}
