/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.analytics.types;

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

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = TimeDataPoint.Builder.class)
public final class TimeDataPoint {
    private final long x;

    private final double y;

    private final Map<String, Object> additionalProperties;

    private TimeDataPoint(long x, double y, Map<String, Object> additionalProperties) {
        this.x = x;
        this.y = y;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return X-axis value representing time in epoch milliseconds.
     */
    @JsonProperty("x")
    public long getX() {
        return x;
    }

    /**
     * @return Y-axis value representing the measured data.
     */
    @JsonProperty("y")
    public double getY() {
        return y;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof TimeDataPoint && equalTo((TimeDataPoint) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(TimeDataPoint other) {
        return x == other.x && y == other.y;
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static XStage builder() {
        return new Builder();
    }

    public interface XStage {
        YStage x(long x);

        Builder from(TimeDataPoint other);
    }

    public interface YStage {
        _FinalStage y(double y);
    }

    public interface _FinalStage {
        TimeDataPoint build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements XStage, YStage, _FinalStage {
        private long x;

        private double y;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(TimeDataPoint other) {
            x(other.getX());
            y(other.getY());
            return this;
        }

        /**
         * <p>X-axis value representing time in epoch milliseconds.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("x")
        public YStage x(long x) {
            this.x = x;
            return this;
        }

        /**
         * <p>Y-axis value representing the measured data.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("y")
        public _FinalStage y(double y) {
            this.y = y;
            return this;
        }

        @java.lang.Override
        public TimeDataPoint build() {
            return new TimeDataPoint(x, y, additionalProperties);
        }
    }
}
