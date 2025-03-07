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
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = PieChartResponse.Builder.class)
public final class PieChartResponse {
    private final Series series;

    private final Map<String, Object> additionalProperties;

    private PieChartResponse(Series series, Map<String, Object> additionalProperties) {
        this.series = series;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The dataset for the pie chart.
     * Each slice in the pie chart is represented as a data point with a name and a corresponding y-value.
     */
    @JsonProperty("series")
    public Series getSeries() {
        return series;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof PieChartResponse && equalTo((PieChartResponse) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(PieChartResponse other) {
        return series.equals(other.series);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.series);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static SeriesStage builder() {
        return new Builder();
    }

    public interface SeriesStage {
        _FinalStage series(@NotNull Series series);

        Builder from(PieChartResponse other);
    }

    public interface _FinalStage {
        PieChartResponse build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements SeriesStage, _FinalStage {
        private Series series;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(PieChartResponse other) {
            series(other.getSeries());
            return this;
        }

        /**
         * <p>The dataset for the pie chart.
         * Each slice in the pie chart is represented as a data point with a name and a corresponding y-value.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("series")
        public _FinalStage series(@NotNull Series series) {
            this.series = Objects.requireNonNull(series, "series must not be null");
            return this;
        }

        @java.lang.Override
        public PieChartResponse build() {
            return new PieChartResponse(series, additionalProperties);
        }
    }
}
