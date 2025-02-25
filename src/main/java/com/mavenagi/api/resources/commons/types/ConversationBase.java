/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.commons.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ConversationBase.Builder.class)
public final class ConversationBase implements IConversationBase {
    private final Optional<ResponseConfig> responseConfig;

    private final Optional<String> subject;

    private final Optional<String> url;

    private final Optional<OffsetDateTime> createdAt;

    private final Optional<OffsetDateTime> updatedAt;

    private final Optional<Set<String>> tags;

    private final Optional<Map<String, String>> metadata;

    private final Map<String, Object> additionalProperties;

    private ConversationBase(
            Optional<ResponseConfig> responseConfig,
            Optional<String> subject,
            Optional<String> url,
            Optional<OffsetDateTime> createdAt,
            Optional<OffsetDateTime> updatedAt,
            Optional<Set<String>> tags,
            Optional<Map<String, String>> metadata,
            Map<String, Object> additionalProperties) {
        this.responseConfig = responseConfig;
        this.subject = subject;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
        this.metadata = metadata;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Optional configurations for responses to this conversation
     */
    @JsonProperty("responseConfig")
    @java.lang.Override
    public Optional<ResponseConfig> getResponseConfig() {
        return responseConfig;
    }

    /**
     * @return The subject of the conversation
     */
    @JsonProperty("subject")
    @java.lang.Override
    public Optional<String> getSubject() {
        return subject;
    }

    /**
     * @return The url of the conversation
     */
    @JsonProperty("url")
    @java.lang.Override
    public Optional<String> getUrl() {
        return url;
    }

    /**
     * @return The date and time the conversation was created
     */
    @JsonProperty("createdAt")
    @java.lang.Override
    public Optional<OffsetDateTime> getCreatedAt() {
        return createdAt;
    }

    /**
     * @return The date and time the conversation was last updated
     */
    @JsonProperty("updatedAt")
    @java.lang.Override
    public Optional<OffsetDateTime> getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return The tags of the conversation. Used for filtering in Agent Designer.
     */
    @JsonProperty("tags")
    @java.lang.Override
    public Optional<Set<String>> getTags() {
        return tags;
    }

    /**
     * @return The metadata of the conversation.
     */
    @JsonProperty("metadata")
    @java.lang.Override
    public Optional<Map<String, String>> getMetadata() {
        return metadata;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ConversationBase && equalTo((ConversationBase) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ConversationBase other) {
        return responseConfig.equals(other.responseConfig)
                && subject.equals(other.subject)
                && url.equals(other.url)
                && createdAt.equals(other.createdAt)
                && updatedAt.equals(other.updatedAt)
                && tags.equals(other.tags)
                && metadata.equals(other.metadata);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.responseConfig, this.subject, this.url, this.createdAt, this.updatedAt, this.tags, this.metadata);
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
        private Optional<ResponseConfig> responseConfig = Optional.empty();

        private Optional<String> subject = Optional.empty();

        private Optional<String> url = Optional.empty();

        private Optional<OffsetDateTime> createdAt = Optional.empty();

        private Optional<OffsetDateTime> updatedAt = Optional.empty();

        private Optional<Set<String>> tags = Optional.empty();

        private Optional<Map<String, String>> metadata = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(ConversationBase other) {
            responseConfig(other.getResponseConfig());
            subject(other.getSubject());
            url(other.getUrl());
            createdAt(other.getCreatedAt());
            updatedAt(other.getUpdatedAt());
            tags(other.getTags());
            metadata(other.getMetadata());
            return this;
        }

        @JsonSetter(value = "responseConfig", nulls = Nulls.SKIP)
        public Builder responseConfig(Optional<ResponseConfig> responseConfig) {
            this.responseConfig = responseConfig;
            return this;
        }

        public Builder responseConfig(ResponseConfig responseConfig) {
            this.responseConfig = Optional.ofNullable(responseConfig);
            return this;
        }

        @JsonSetter(value = "subject", nulls = Nulls.SKIP)
        public Builder subject(Optional<String> subject) {
            this.subject = subject;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = Optional.ofNullable(subject);
            return this;
        }

        @JsonSetter(value = "url", nulls = Nulls.SKIP)
        public Builder url(Optional<String> url) {
            this.url = url;
            return this;
        }

        public Builder url(String url) {
            this.url = Optional.ofNullable(url);
            return this;
        }

        @JsonSetter(value = "createdAt", nulls = Nulls.SKIP)
        public Builder createdAt(Optional<OffsetDateTime> createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = Optional.ofNullable(createdAt);
            return this;
        }

        @JsonSetter(value = "updatedAt", nulls = Nulls.SKIP)
        public Builder updatedAt(Optional<OffsetDateTime> updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder updatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = Optional.ofNullable(updatedAt);
            return this;
        }

        @JsonSetter(value = "tags", nulls = Nulls.SKIP)
        public Builder tags(Optional<Set<String>> tags) {
            this.tags = tags;
            return this;
        }

        public Builder tags(Set<String> tags) {
            this.tags = Optional.ofNullable(tags);
            return this;
        }

        @JsonSetter(value = "metadata", nulls = Nulls.SKIP)
        public Builder metadata(Optional<Map<String, String>> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = Optional.ofNullable(metadata);
            return this;
        }

        public ConversationBase build() {
            return new ConversationBase(
                    responseConfig, subject, url, createdAt, updatedAt, tags, metadata, additionalProperties);
        }
    }
}
