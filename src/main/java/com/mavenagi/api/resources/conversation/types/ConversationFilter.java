/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.conversation.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import com.mavenagi.api.resources.commons.types.EntityIdFilter;
import com.mavenagi.api.resources.commons.types.FeedbackType;
import com.mavenagi.api.resources.commons.types.ResponseLength;
import com.mavenagi.api.resources.commons.types.Sentiment;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ConversationFilter.Builder.class)
public final class ConversationFilter {
    private final Optional<String> search;

    private final Optional<OffsetDateTime> createdAfter;

    private final Optional<OffsetDateTime> createdBefore;

    private final Optional<List<String>> apps;

    private final Optional<List<String>> categories;

    private final Optional<List<EntityIdFilter>> actions;

    private final Optional<List<EntityIdFilter>> incompleteActions;

    private final Optional<List<FeedbackType>> feedback;

    private final Optional<List<String>> humanAgents;

    private final Optional<List<String>> languages;

    private final Optional<List<Quality>> quality;

    private final Optional<List<QualityReason>> qualityReason;

    private final Optional<List<ResponseLength>> responseLength;

    private final Optional<List<Sentiment>> sentiment;

    private final Optional<List<String>> tags;

    private final Optional<List<ResolutionStatus>> resolutionStatus;

    private final Optional<Boolean> resolvedByMaven;

    private final Map<String, Object> additionalProperties;

    private ConversationFilter(
            Optional<String> search,
            Optional<OffsetDateTime> createdAfter,
            Optional<OffsetDateTime> createdBefore,
            Optional<List<String>> apps,
            Optional<List<String>> categories,
            Optional<List<EntityIdFilter>> actions,
            Optional<List<EntityIdFilter>> incompleteActions,
            Optional<List<FeedbackType>> feedback,
            Optional<List<String>> humanAgents,
            Optional<List<String>> languages,
            Optional<List<Quality>> quality,
            Optional<List<QualityReason>> qualityReason,
            Optional<List<ResponseLength>> responseLength,
            Optional<List<Sentiment>> sentiment,
            Optional<List<String>> tags,
            Optional<List<ResolutionStatus>> resolutionStatus,
            Optional<Boolean> resolvedByMaven,
            Map<String, Object> additionalProperties) {
        this.search = search;
        this.createdAfter = createdAfter;
        this.createdBefore = createdBefore;
        this.apps = apps;
        this.categories = categories;
        this.actions = actions;
        this.incompleteActions = incompleteActions;
        this.feedback = feedback;
        this.humanAgents = humanAgents;
        this.languages = languages;
        this.quality = quality;
        this.qualityReason = qualityReason;
        this.responseLength = responseLength;
        this.sentiment = sentiment;
        this.tags = tags;
        this.resolutionStatus = resolutionStatus;
        this.resolvedByMaven = resolvedByMaven;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("search")
    public Optional<String> getSearch() {
        return search;
    }

    @JsonProperty("createdAfter")
    public Optional<OffsetDateTime> getCreatedAfter() {
        return createdAfter;
    }

    @JsonProperty("createdBefore")
    public Optional<OffsetDateTime> getCreatedBefore() {
        return createdBefore;
    }

    @JsonProperty("apps")
    public Optional<List<String>> getApps() {
        return apps;
    }

    @JsonProperty("categories")
    public Optional<List<String>> getCategories() {
        return categories;
    }

    @JsonProperty("actions")
    public Optional<List<EntityIdFilter>> getActions() {
        return actions;
    }

    @JsonProperty("incompleteActions")
    public Optional<List<EntityIdFilter>> getIncompleteActions() {
        return incompleteActions;
    }

    @JsonProperty("feedback")
    public Optional<List<FeedbackType>> getFeedback() {
        return feedback;
    }

    @JsonProperty("humanAgents")
    public Optional<List<String>> getHumanAgents() {
        return humanAgents;
    }

    @JsonProperty("languages")
    public Optional<List<String>> getLanguages() {
        return languages;
    }

    @JsonProperty("quality")
    public Optional<List<Quality>> getQuality() {
        return quality;
    }

    @JsonProperty("qualityReason")
    public Optional<List<QualityReason>> getQualityReason() {
        return qualityReason;
    }

    @JsonProperty("responseLength")
    public Optional<List<ResponseLength>> getResponseLength() {
        return responseLength;
    }

    @JsonProperty("sentiment")
    public Optional<List<Sentiment>> getSentiment() {
        return sentiment;
    }

    @JsonProperty("tags")
    public Optional<List<String>> getTags() {
        return tags;
    }

    @JsonProperty("resolutionStatus")
    public Optional<List<ResolutionStatus>> getResolutionStatus() {
        return resolutionStatus;
    }

    @JsonProperty("resolvedByMaven")
    public Optional<Boolean> getResolvedByMaven() {
        return resolvedByMaven;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ConversationFilter && equalTo((ConversationFilter) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ConversationFilter other) {
        return search.equals(other.search)
                && createdAfter.equals(other.createdAfter)
                && createdBefore.equals(other.createdBefore)
                && apps.equals(other.apps)
                && categories.equals(other.categories)
                && actions.equals(other.actions)
                && incompleteActions.equals(other.incompleteActions)
                && feedback.equals(other.feedback)
                && humanAgents.equals(other.humanAgents)
                && languages.equals(other.languages)
                && quality.equals(other.quality)
                && qualityReason.equals(other.qualityReason)
                && responseLength.equals(other.responseLength)
                && sentiment.equals(other.sentiment)
                && tags.equals(other.tags)
                && resolutionStatus.equals(other.resolutionStatus)
                && resolvedByMaven.equals(other.resolvedByMaven);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.search,
                this.createdAfter,
                this.createdBefore,
                this.apps,
                this.categories,
                this.actions,
                this.incompleteActions,
                this.feedback,
                this.humanAgents,
                this.languages,
                this.quality,
                this.qualityReason,
                this.responseLength,
                this.sentiment,
                this.tags,
                this.resolutionStatus,
                this.resolvedByMaven);
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
        private Optional<String> search = Optional.empty();

        private Optional<OffsetDateTime> createdAfter = Optional.empty();

        private Optional<OffsetDateTime> createdBefore = Optional.empty();

        private Optional<List<String>> apps = Optional.empty();

        private Optional<List<String>> categories = Optional.empty();

        private Optional<List<EntityIdFilter>> actions = Optional.empty();

        private Optional<List<EntityIdFilter>> incompleteActions = Optional.empty();

        private Optional<List<FeedbackType>> feedback = Optional.empty();

        private Optional<List<String>> humanAgents = Optional.empty();

        private Optional<List<String>> languages = Optional.empty();

        private Optional<List<Quality>> quality = Optional.empty();

        private Optional<List<QualityReason>> qualityReason = Optional.empty();

        private Optional<List<ResponseLength>> responseLength = Optional.empty();

        private Optional<List<Sentiment>> sentiment = Optional.empty();

        private Optional<List<String>> tags = Optional.empty();

        private Optional<List<ResolutionStatus>> resolutionStatus = Optional.empty();

        private Optional<Boolean> resolvedByMaven = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(ConversationFilter other) {
            search(other.getSearch());
            createdAfter(other.getCreatedAfter());
            createdBefore(other.getCreatedBefore());
            apps(other.getApps());
            categories(other.getCategories());
            actions(other.getActions());
            incompleteActions(other.getIncompleteActions());
            feedback(other.getFeedback());
            humanAgents(other.getHumanAgents());
            languages(other.getLanguages());
            quality(other.getQuality());
            qualityReason(other.getQualityReason());
            responseLength(other.getResponseLength());
            sentiment(other.getSentiment());
            tags(other.getTags());
            resolutionStatus(other.getResolutionStatus());
            resolvedByMaven(other.getResolvedByMaven());
            return this;
        }

        @JsonSetter(value = "search", nulls = Nulls.SKIP)
        public Builder search(Optional<String> search) {
            this.search = search;
            return this;
        }

        public Builder search(String search) {
            this.search = Optional.ofNullable(search);
            return this;
        }

        @JsonSetter(value = "createdAfter", nulls = Nulls.SKIP)
        public Builder createdAfter(Optional<OffsetDateTime> createdAfter) {
            this.createdAfter = createdAfter;
            return this;
        }

        public Builder createdAfter(OffsetDateTime createdAfter) {
            this.createdAfter = Optional.ofNullable(createdAfter);
            return this;
        }

        @JsonSetter(value = "createdBefore", nulls = Nulls.SKIP)
        public Builder createdBefore(Optional<OffsetDateTime> createdBefore) {
            this.createdBefore = createdBefore;
            return this;
        }

        public Builder createdBefore(OffsetDateTime createdBefore) {
            this.createdBefore = Optional.ofNullable(createdBefore);
            return this;
        }

        @JsonSetter(value = "apps", nulls = Nulls.SKIP)
        public Builder apps(Optional<List<String>> apps) {
            this.apps = apps;
            return this;
        }

        public Builder apps(List<String> apps) {
            this.apps = Optional.ofNullable(apps);
            return this;
        }

        @JsonSetter(value = "categories", nulls = Nulls.SKIP)
        public Builder categories(Optional<List<String>> categories) {
            this.categories = categories;
            return this;
        }

        public Builder categories(List<String> categories) {
            this.categories = Optional.ofNullable(categories);
            return this;
        }

        @JsonSetter(value = "actions", nulls = Nulls.SKIP)
        public Builder actions(Optional<List<EntityIdFilter>> actions) {
            this.actions = actions;
            return this;
        }

        public Builder actions(List<EntityIdFilter> actions) {
            this.actions = Optional.ofNullable(actions);
            return this;
        }

        @JsonSetter(value = "incompleteActions", nulls = Nulls.SKIP)
        public Builder incompleteActions(Optional<List<EntityIdFilter>> incompleteActions) {
            this.incompleteActions = incompleteActions;
            return this;
        }

        public Builder incompleteActions(List<EntityIdFilter> incompleteActions) {
            this.incompleteActions = Optional.ofNullable(incompleteActions);
            return this;
        }

        @JsonSetter(value = "feedback", nulls = Nulls.SKIP)
        public Builder feedback(Optional<List<FeedbackType>> feedback) {
            this.feedback = feedback;
            return this;
        }

        public Builder feedback(List<FeedbackType> feedback) {
            this.feedback = Optional.ofNullable(feedback);
            return this;
        }

        @JsonSetter(value = "humanAgents", nulls = Nulls.SKIP)
        public Builder humanAgents(Optional<List<String>> humanAgents) {
            this.humanAgents = humanAgents;
            return this;
        }

        public Builder humanAgents(List<String> humanAgents) {
            this.humanAgents = Optional.ofNullable(humanAgents);
            return this;
        }

        @JsonSetter(value = "languages", nulls = Nulls.SKIP)
        public Builder languages(Optional<List<String>> languages) {
            this.languages = languages;
            return this;
        }

        public Builder languages(List<String> languages) {
            this.languages = Optional.ofNullable(languages);
            return this;
        }

        @JsonSetter(value = "quality", nulls = Nulls.SKIP)
        public Builder quality(Optional<List<Quality>> quality) {
            this.quality = quality;
            return this;
        }

        public Builder quality(List<Quality> quality) {
            this.quality = Optional.ofNullable(quality);
            return this;
        }

        @JsonSetter(value = "qualityReason", nulls = Nulls.SKIP)
        public Builder qualityReason(Optional<List<QualityReason>> qualityReason) {
            this.qualityReason = qualityReason;
            return this;
        }

        public Builder qualityReason(List<QualityReason> qualityReason) {
            this.qualityReason = Optional.ofNullable(qualityReason);
            return this;
        }

        @JsonSetter(value = "responseLength", nulls = Nulls.SKIP)
        public Builder responseLength(Optional<List<ResponseLength>> responseLength) {
            this.responseLength = responseLength;
            return this;
        }

        public Builder responseLength(List<ResponseLength> responseLength) {
            this.responseLength = Optional.ofNullable(responseLength);
            return this;
        }

        @JsonSetter(value = "sentiment", nulls = Nulls.SKIP)
        public Builder sentiment(Optional<List<Sentiment>> sentiment) {
            this.sentiment = sentiment;
            return this;
        }

        public Builder sentiment(List<Sentiment> sentiment) {
            this.sentiment = Optional.ofNullable(sentiment);
            return this;
        }

        @JsonSetter(value = "tags", nulls = Nulls.SKIP)
        public Builder tags(Optional<List<String>> tags) {
            this.tags = tags;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = Optional.ofNullable(tags);
            return this;
        }

        @JsonSetter(value = "resolutionStatus", nulls = Nulls.SKIP)
        public Builder resolutionStatus(Optional<List<ResolutionStatus>> resolutionStatus) {
            this.resolutionStatus = resolutionStatus;
            return this;
        }

        public Builder resolutionStatus(List<ResolutionStatus> resolutionStatus) {
            this.resolutionStatus = Optional.ofNullable(resolutionStatus);
            return this;
        }

        @JsonSetter(value = "resolvedByMaven", nulls = Nulls.SKIP)
        public Builder resolvedByMaven(Optional<Boolean> resolvedByMaven) {
            this.resolvedByMaven = resolvedByMaven;
            return this;
        }

        public Builder resolvedByMaven(Boolean resolvedByMaven) {
            this.resolvedByMaven = Optional.ofNullable(resolvedByMaven);
            return this;
        }

        public ConversationFilter build() {
            return new ConversationFilter(
                    search,
                    createdAfter,
                    createdBefore,
                    apps,
                    categories,
                    actions,
                    incompleteActions,
                    feedback,
                    humanAgents,
                    languages,
                    quality,
                    qualityReason,
                    responseLength,
                    sentiment,
                    tags,
                    resolutionStatus,
                    resolvedByMaven,
                    additionalProperties);
        }
    }
}
