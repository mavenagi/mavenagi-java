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
@JsonDeserialize(builder = FeedbackColumnDefinition.Builder.class)
public final class FeedbackColumnDefinition implements IColumnDefinitionBase {
    private final String header;

    private final FeedbackMetric metric;

    private final Map<String, Object> additionalProperties;

    private FeedbackColumnDefinition(String header, FeedbackMetric metric, Map<String, Object> additionalProperties) {
        this.header = header;
        this.metric = metric;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Unique column header, serving as the key for corresponding metric values.
     */
    @JsonProperty("header")
    @java.lang.Override
    public String getHeader() {
        return header;
    }

    /**
     * @return The metric calculated for this column, stored in the row data under the specified header.
     */
    @JsonProperty("metric")
    public FeedbackMetric getMetric() {
        return metric;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof FeedbackColumnDefinition && equalTo((FeedbackColumnDefinition) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(FeedbackColumnDefinition other) {
        return header.equals(other.header) && metric.equals(other.metric);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.header, this.metric);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static HeaderStage builder() {
        return new Builder();
    }

    public interface HeaderStage {
        MetricStage header(@NotNull String header);

        Builder from(FeedbackColumnDefinition other);
    }

    public interface MetricStage {
        _FinalStage metric(@NotNull FeedbackMetric metric);
    }

    public interface _FinalStage {
        FeedbackColumnDefinition build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements HeaderStage, MetricStage, _FinalStage {
        private String header;

        private FeedbackMetric metric;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(FeedbackColumnDefinition other) {
            header(other.getHeader());
            metric(other.getMetric());
            return this;
        }

        /**
         * <p>Unique column header, serving as the key for corresponding metric values.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("header")
        public MetricStage header(@NotNull String header) {
            this.header = Objects.requireNonNull(header, "header must not be null");
            return this;
        }

        /**
         * <p>The metric calculated for this column, stored in the row data under the specified header.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("metric")
        public _FinalStage metric(@NotNull FeedbackMetric metric) {
            this.metric = Objects.requireNonNull(metric, "metric must not be null");
            return this;
        }

        @java.lang.Override
        public FeedbackColumnDefinition build() {
            return new FeedbackColumnDefinition(header, metric, additionalProperties);
        }
    }
}
