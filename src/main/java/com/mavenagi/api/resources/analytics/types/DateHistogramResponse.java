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
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = DateHistogramResponse.Builder.class)
public final class DateHistogramResponse {
    private final List<TimeSeries> timeSeries;

    private final Map<String, Object> additionalProperties;

    private DateHistogramResponse(List<TimeSeries> timeSeries, Map<String, Object> additionalProperties) {
        this.timeSeries = timeSeries;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The dataset for the date histogram.
     * Each series represents a separate plottable time series.
     * Series names reflect the grouping field values.
     */
    @JsonProperty("timeSeries")
    public List<TimeSeries> getTimeSeries() {
        return timeSeries;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof DateHistogramResponse && equalTo((DateHistogramResponse) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(DateHistogramResponse other) {
        return timeSeries.equals(other.timeSeries);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.timeSeries);
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
        private List<TimeSeries> timeSeries = new ArrayList<>();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(DateHistogramResponse other) {
            timeSeries(other.getTimeSeries());
            return this;
        }

        @JsonSetter(value = "timeSeries", nulls = Nulls.SKIP)
        public Builder timeSeries(List<TimeSeries> timeSeries) {
            this.timeSeries.clear();
            this.timeSeries.addAll(timeSeries);
            return this;
        }

        public Builder addTimeSeries(TimeSeries timeSeries) {
            this.timeSeries.add(timeSeries);
            return this;
        }

        public Builder addAllTimeSeries(List<TimeSeries> timeSeries) {
            this.timeSeries.addAll(timeSeries);
            return this;
        }

        public DateHistogramResponse build() {
            return new DateHistogramResponse(timeSeries, additionalProperties);
        }
    }
}
