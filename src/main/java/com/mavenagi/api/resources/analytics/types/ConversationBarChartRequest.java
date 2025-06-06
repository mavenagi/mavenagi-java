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
import com.mavenagi.api.resources.conversation.types.ConversationFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ConversationBarChartRequest.Builder.class)
public final class ConversationBarChartRequest implements IConversationAnalyticsRequest {
    private final Optional<ConversationFilter> conversationFilter;

    private final ConversationGroupBy barDefinition;

    private final ConversationMetric metric;

    private final Optional<ConversationGroupBy> verticalGrouping;

    private final Map<String, Object> additionalProperties;

    private ConversationBarChartRequest(
            Optional<ConversationFilter> conversationFilter,
            ConversationGroupBy barDefinition,
            ConversationMetric metric,
            Optional<ConversationGroupBy> verticalGrouping,
            Map<String, Object> additionalProperties) {
        this.conversationFilter = conversationFilter;
        this.barDefinition = barDefinition;
        this.metric = metric;
        this.verticalGrouping = verticalGrouping;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Optional filter applied to refine the conversation data before processing.
     */
    @JsonProperty("conversationFilter")
    @java.lang.Override
    public Optional<ConversationFilter> getConversationFilter() {
        return conversationFilter;
    }

    /**
     * @return Determines how data is grouped along the x-axis. Each unique value forms a separate bar.
     * The name of the bar is derived from the grouping field's value or range.
     */
    @JsonProperty("barDefinition")
    public ConversationGroupBy getBarDefinition() {
        return barDefinition;
    }

    /**
     * @return Metric defining the y-axis values for the bar chart.
     */
    @JsonProperty("metric")
    public ConversationMetric getMetric() {
        return metric;
    }

    /**
     * @return Optionally defines vertical grouping within each bar, producing multiple series.
     * If omitted, a single series is generated.
     */
    @JsonProperty("verticalGrouping")
    public Optional<ConversationGroupBy> getVerticalGrouping() {
        return verticalGrouping;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ConversationBarChartRequest && equalTo((ConversationBarChartRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ConversationBarChartRequest other) {
        return conversationFilter.equals(other.conversationFilter)
                && barDefinition.equals(other.barDefinition)
                && metric.equals(other.metric)
                && verticalGrouping.equals(other.verticalGrouping);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.conversationFilter, this.barDefinition, this.metric, this.verticalGrouping);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static BarDefinitionStage builder() {
        return new Builder();
    }

    public interface BarDefinitionStage {
        MetricStage barDefinition(@NotNull ConversationGroupBy barDefinition);

        Builder from(ConversationBarChartRequest other);
    }

    public interface MetricStage {
        _FinalStage metric(@NotNull ConversationMetric metric);
    }

    public interface _FinalStage {
        ConversationBarChartRequest build();

        _FinalStage conversationFilter(Optional<ConversationFilter> conversationFilter);

        _FinalStage conversationFilter(ConversationFilter conversationFilter);

        _FinalStage verticalGrouping(Optional<ConversationGroupBy> verticalGrouping);

        _FinalStage verticalGrouping(ConversationGroupBy verticalGrouping);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements BarDefinitionStage, MetricStage, _FinalStage {
        private ConversationGroupBy barDefinition;

        private ConversationMetric metric;

        private Optional<ConversationGroupBy> verticalGrouping = Optional.empty();

        private Optional<ConversationFilter> conversationFilter = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(ConversationBarChartRequest other) {
            conversationFilter(other.getConversationFilter());
            barDefinition(other.getBarDefinition());
            metric(other.getMetric());
            verticalGrouping(other.getVerticalGrouping());
            return this;
        }

        /**
         * <p>Determines how data is grouped along the x-axis. Each unique value forms a separate bar.
         * The name of the bar is derived from the grouping field's value or range.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("barDefinition")
        public MetricStage barDefinition(@NotNull ConversationGroupBy barDefinition) {
            this.barDefinition = Objects.requireNonNull(barDefinition, "barDefinition must not be null");
            return this;
        }

        /**
         * <p>Metric defining the y-axis values for the bar chart.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("metric")
        public _FinalStage metric(@NotNull ConversationMetric metric) {
            this.metric = Objects.requireNonNull(metric, "metric must not be null");
            return this;
        }

        /**
         * <p>Optionally defines vertical grouping within each bar, producing multiple series.
         * If omitted, a single series is generated.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage verticalGrouping(ConversationGroupBy verticalGrouping) {
            this.verticalGrouping = Optional.ofNullable(verticalGrouping);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "verticalGrouping", nulls = Nulls.SKIP)
        public _FinalStage verticalGrouping(Optional<ConversationGroupBy> verticalGrouping) {
            this.verticalGrouping = verticalGrouping;
            return this;
        }

        /**
         * <p>Optional filter applied to refine the conversation data before processing.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage conversationFilter(ConversationFilter conversationFilter) {
            this.conversationFilter = Optional.ofNullable(conversationFilter);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "conversationFilter", nulls = Nulls.SKIP)
        public _FinalStage conversationFilter(Optional<ConversationFilter> conversationFilter) {
            this.conversationFilter = conversationFilter;
            return this;
        }

        @java.lang.Override
        public ConversationBarChartRequest build() {
            return new ConversationBarChartRequest(
                    conversationFilter, barDefinition, metric, verticalGrouping, additionalProperties);
        }
    }
}
