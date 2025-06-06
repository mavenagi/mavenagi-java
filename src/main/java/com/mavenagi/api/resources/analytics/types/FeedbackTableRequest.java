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
import com.mavenagi.api.resources.conversation.types.FeedbackFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = FeedbackTableRequest.Builder.class)
public final class FeedbackTableRequest implements IFeedbackAnalyticsRequest {
    private final Optional<FeedbackFilter> feedbackFilter;

    private final Optional<TimeInterval> timeGrouping;

    private final List<FeedbackGroupBy> fieldGroupings;

    private final List<FeedbackColumnDefinition> columnDefinitions;

    private final Map<String, Object> additionalProperties;

    private FeedbackTableRequest(
            Optional<FeedbackFilter> feedbackFilter,
            Optional<TimeInterval> timeGrouping,
            List<FeedbackGroupBy> fieldGroupings,
            List<FeedbackColumnDefinition> columnDefinitions,
            Map<String, Object> additionalProperties) {
        this.feedbackFilter = feedbackFilter;
        this.timeGrouping = timeGrouping;
        this.fieldGroupings = fieldGroupings;
        this.columnDefinitions = columnDefinitions;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Optional filter applied to refine the feedback data before processing.
     */
    @JsonProperty("feedbackFilter")
    @java.lang.Override
    public Optional<FeedbackFilter> getFeedbackFilter() {
        return feedbackFilter;
    }

    /**
     * @return Defines the time interval for grouping data. If specified, data is grouped accordingly based on the time they were created.
     * Example: If set to &quot;DAY,&quot; data will be aggregated by day.
     */
    @JsonProperty("timeGrouping")
    public Optional<TimeInterval> getTimeGrouping() {
        return timeGrouping;
    }

    /**
     * @return Specifies the fields by which data should be grouped. Each unique combination forms a row.
     * If multiple fields are provided, the result is grouped by their unique value combinations.
     * If empty, all data is aggregated into a single row.
     * Note: The field CreatedAt should not be used here, all the time-based grouping should be done using the timeGrouping field.
     */
    @JsonProperty("fieldGroupings")
    public List<FeedbackGroupBy> getFieldGroupings() {
        return fieldGroupings;
    }

    /**
     * @return Specifies the metrics to be displayed as columns.
     * Column headers act as keys, with computed metric values as their mapped values.
     * There needs to be at least one column definition in the table request.
     */
    @JsonProperty("columnDefinitions")
    public List<FeedbackColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof FeedbackTableRequest && equalTo((FeedbackTableRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(FeedbackTableRequest other) {
        return feedbackFilter.equals(other.feedbackFilter)
                && timeGrouping.equals(other.timeGrouping)
                && fieldGroupings.equals(other.fieldGroupings)
                && columnDefinitions.equals(other.columnDefinitions);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.feedbackFilter, this.timeGrouping, this.fieldGroupings, this.columnDefinitions);
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
        private Optional<FeedbackFilter> feedbackFilter = Optional.empty();

        private Optional<TimeInterval> timeGrouping = Optional.empty();

        private List<FeedbackGroupBy> fieldGroupings = new ArrayList<>();

        private List<FeedbackColumnDefinition> columnDefinitions = new ArrayList<>();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(FeedbackTableRequest other) {
            feedbackFilter(other.getFeedbackFilter());
            timeGrouping(other.getTimeGrouping());
            fieldGroupings(other.getFieldGroupings());
            columnDefinitions(other.getColumnDefinitions());
            return this;
        }

        @JsonSetter(value = "feedbackFilter", nulls = Nulls.SKIP)
        public Builder feedbackFilter(Optional<FeedbackFilter> feedbackFilter) {
            this.feedbackFilter = feedbackFilter;
            return this;
        }

        public Builder feedbackFilter(FeedbackFilter feedbackFilter) {
            this.feedbackFilter = Optional.ofNullable(feedbackFilter);
            return this;
        }

        @JsonSetter(value = "timeGrouping", nulls = Nulls.SKIP)
        public Builder timeGrouping(Optional<TimeInterval> timeGrouping) {
            this.timeGrouping = timeGrouping;
            return this;
        }

        public Builder timeGrouping(TimeInterval timeGrouping) {
            this.timeGrouping = Optional.ofNullable(timeGrouping);
            return this;
        }

        @JsonSetter(value = "fieldGroupings", nulls = Nulls.SKIP)
        public Builder fieldGroupings(List<FeedbackGroupBy> fieldGroupings) {
            this.fieldGroupings.clear();
            this.fieldGroupings.addAll(fieldGroupings);
            return this;
        }

        public Builder addFieldGroupings(FeedbackGroupBy fieldGroupings) {
            this.fieldGroupings.add(fieldGroupings);
            return this;
        }

        public Builder addAllFieldGroupings(List<FeedbackGroupBy> fieldGroupings) {
            this.fieldGroupings.addAll(fieldGroupings);
            return this;
        }

        @JsonSetter(value = "columnDefinitions", nulls = Nulls.SKIP)
        public Builder columnDefinitions(List<FeedbackColumnDefinition> columnDefinitions) {
            this.columnDefinitions.clear();
            this.columnDefinitions.addAll(columnDefinitions);
            return this;
        }

        public Builder addColumnDefinitions(FeedbackColumnDefinition columnDefinitions) {
            this.columnDefinitions.add(columnDefinitions);
            return this;
        }

        public Builder addAllColumnDefinitions(List<FeedbackColumnDefinition> columnDefinitions) {
            this.columnDefinitions.addAll(columnDefinitions);
            return this;
        }

        public FeedbackTableRequest build() {
            return new FeedbackTableRequest(
                    feedbackFilter, timeGrouping, fieldGroupings, columnDefinitions, additionalProperties);
        }
    }
}
