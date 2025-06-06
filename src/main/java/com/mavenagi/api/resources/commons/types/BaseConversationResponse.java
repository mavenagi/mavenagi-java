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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = BaseConversationResponse.Builder.class)
public final class BaseConversationResponse implements IBaseConversationResponse {
    private final Optional<ResponseConfig> responseConfig;

    private final Optional<String> subject;

    private final Optional<String> url;

    private final Optional<OffsetDateTime> createdAt;

    private final Optional<OffsetDateTime> updatedAt;

    private final Optional<Set<String>> tags;

    private final Optional<Map<String, String>> metadata;

    private final Map<String, Map<String, String>> allMetadata;

    private final EntityId conversationId;

    private final ConversationAnalysis analysis;

    private final ConversationSummary summary;

    private final boolean deleted;

    private final Map<String, Object> additionalProperties;

    private BaseConversationResponse(
            Optional<ResponseConfig> responseConfig,
            Optional<String> subject,
            Optional<String> url,
            Optional<OffsetDateTime> createdAt,
            Optional<OffsetDateTime> updatedAt,
            Optional<Set<String>> tags,
            Optional<Map<String, String>> metadata,
            Map<String, Map<String, String>> allMetadata,
            EntityId conversationId,
            ConversationAnalysis analysis,
            ConversationSummary summary,
            boolean deleted,
            Map<String, Object> additionalProperties) {
        this.responseConfig = responseConfig;
        this.subject = subject;
        this.url = url;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
        this.metadata = metadata;
        this.allMetadata = allMetadata;
        this.conversationId = conversationId;
        this.analysis = analysis;
        this.summary = summary;
        this.deleted = deleted;
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
     * @return The metadata of the conversation supplied by the app which created the conversation.
     */
    @JsonProperty("metadata")
    @java.lang.Override
    public Optional<Map<String, String>> getMetadata() {
        return metadata;
    }

    /**
     * @return All metadata for the conversation. Keyed by appId.
     */
    @JsonProperty("allMetadata")
    @java.lang.Override
    public Map<String, Map<String, String>> getAllMetadata() {
        return allMetadata;
    }

    /**
     * @return The ID that uniquely identifies this conversation
     */
    @JsonProperty("conversationId")
    @java.lang.Override
    public EntityId getConversationId() {
        return conversationId;
    }

    /**
     * @return An analysis of the conversation. Fields are generated by Maven via an analysis of user messages. This object is calculated on a delay. Fields will not be up to date on ask requests.
     */
    @JsonProperty("analysis")
    @java.lang.Override
    public ConversationAnalysis getAnalysis() {
        return analysis;
    }

    /**
     * @return A summary of the conversation. Fields are calculated from conversation data. Unlike analysis, all fields can be derived from other data available in the API. This object is provided as a convenience and is calculated on a delay. Fields will not be up to date on ask requests.
     */
    @JsonProperty("summary")
    @java.lang.Override
    public ConversationSummary getSummary() {
        return summary;
    }

    /**
     * @return Whether the conversation user-specific data has been deleted. See <code>deleteConversation</code> for details.
     */
    @JsonProperty("deleted")
    @java.lang.Override
    public boolean getDeleted() {
        return deleted;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BaseConversationResponse && equalTo((BaseConversationResponse) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(BaseConversationResponse other) {
        return responseConfig.equals(other.responseConfig)
                && subject.equals(other.subject)
                && url.equals(other.url)
                && createdAt.equals(other.createdAt)
                && updatedAt.equals(other.updatedAt)
                && tags.equals(other.tags)
                && metadata.equals(other.metadata)
                && allMetadata.equals(other.allMetadata)
                && conversationId.equals(other.conversationId)
                && analysis.equals(other.analysis)
                && summary.equals(other.summary)
                && deleted == other.deleted;
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.responseConfig,
                this.subject,
                this.url,
                this.createdAt,
                this.updatedAt,
                this.tags,
                this.metadata,
                this.allMetadata,
                this.conversationId,
                this.analysis,
                this.summary,
                this.deleted);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ConversationIdStage builder() {
        return new Builder();
    }

    public interface ConversationIdStage {
        AnalysisStage conversationId(@NotNull EntityId conversationId);

        Builder from(BaseConversationResponse other);
    }

    public interface AnalysisStage {
        SummaryStage analysis(@NotNull ConversationAnalysis analysis);
    }

    public interface SummaryStage {
        DeletedStage summary(@NotNull ConversationSummary summary);
    }

    public interface DeletedStage {
        _FinalStage deleted(boolean deleted);
    }

    public interface _FinalStage {
        BaseConversationResponse build();

        _FinalStage responseConfig(Optional<ResponseConfig> responseConfig);

        _FinalStage responseConfig(ResponseConfig responseConfig);

        _FinalStage subject(Optional<String> subject);

        _FinalStage subject(String subject);

        _FinalStage url(Optional<String> url);

        _FinalStage url(String url);

        _FinalStage createdAt(Optional<OffsetDateTime> createdAt);

        _FinalStage createdAt(OffsetDateTime createdAt);

        _FinalStage updatedAt(Optional<OffsetDateTime> updatedAt);

        _FinalStage updatedAt(OffsetDateTime updatedAt);

        _FinalStage tags(Optional<Set<String>> tags);

        _FinalStage tags(Set<String> tags);

        _FinalStage metadata(Optional<Map<String, String>> metadata);

        _FinalStage metadata(Map<String, String> metadata);

        _FinalStage allMetadata(Map<String, Map<String, String>> allMetadata);

        _FinalStage putAllAllMetadata(Map<String, Map<String, String>> allMetadata);

        _FinalStage allMetadata(String key, Map<String, String> value);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements ConversationIdStage, AnalysisStage, SummaryStage, DeletedStage, _FinalStage {
        private EntityId conversationId;

        private ConversationAnalysis analysis;

        private ConversationSummary summary;

        private boolean deleted;

        private Map<String, Map<String, String>> allMetadata = new LinkedHashMap<>();

        private Optional<Map<String, String>> metadata = Optional.empty();

        private Optional<Set<String>> tags = Optional.empty();

        private Optional<OffsetDateTime> updatedAt = Optional.empty();

        private Optional<OffsetDateTime> createdAt = Optional.empty();

        private Optional<String> url = Optional.empty();

        private Optional<String> subject = Optional.empty();

        private Optional<ResponseConfig> responseConfig = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(BaseConversationResponse other) {
            responseConfig(other.getResponseConfig());
            subject(other.getSubject());
            url(other.getUrl());
            createdAt(other.getCreatedAt());
            updatedAt(other.getUpdatedAt());
            tags(other.getTags());
            metadata(other.getMetadata());
            allMetadata(other.getAllMetadata());
            conversationId(other.getConversationId());
            analysis(other.getAnalysis());
            summary(other.getSummary());
            deleted(other.getDeleted());
            return this;
        }

        /**
         * <p>The ID that uniquely identifies this conversation</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("conversationId")
        public AnalysisStage conversationId(@NotNull EntityId conversationId) {
            this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
            return this;
        }

        /**
         * <p>An analysis of the conversation. Fields are generated by Maven via an analysis of user messages. This object is calculated on a delay. Fields will not be up to date on ask requests.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("analysis")
        public SummaryStage analysis(@NotNull ConversationAnalysis analysis) {
            this.analysis = Objects.requireNonNull(analysis, "analysis must not be null");
            return this;
        }

        /**
         * <p>A summary of the conversation. Fields are calculated from conversation data. Unlike analysis, all fields can be derived from other data available in the API. This object is provided as a convenience and is calculated on a delay. Fields will not be up to date on ask requests.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("summary")
        public DeletedStage summary(@NotNull ConversationSummary summary) {
            this.summary = Objects.requireNonNull(summary, "summary must not be null");
            return this;
        }

        /**
         * <p>Whether the conversation user-specific data has been deleted. See <code>deleteConversation</code> for details.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("deleted")
        public _FinalStage deleted(boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        /**
         * <p>All metadata for the conversation. Keyed by appId.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage allMetadata(String key, Map<String, String> value) {
            this.allMetadata.put(key, value);
            return this;
        }

        /**
         * <p>All metadata for the conversation. Keyed by appId.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage putAllAllMetadata(Map<String, Map<String, String>> allMetadata) {
            this.allMetadata.putAll(allMetadata);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "allMetadata", nulls = Nulls.SKIP)
        public _FinalStage allMetadata(Map<String, Map<String, String>> allMetadata) {
            this.allMetadata.clear();
            this.allMetadata.putAll(allMetadata);
            return this;
        }

        /**
         * <p>The metadata of the conversation supplied by the app which created the conversation.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage metadata(Map<String, String> metadata) {
            this.metadata = Optional.ofNullable(metadata);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "metadata", nulls = Nulls.SKIP)
        public _FinalStage metadata(Optional<Map<String, String>> metadata) {
            this.metadata = metadata;
            return this;
        }

        /**
         * <p>The tags of the conversation. Used for filtering in Agent Designer.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage tags(Set<String> tags) {
            this.tags = Optional.ofNullable(tags);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "tags", nulls = Nulls.SKIP)
        public _FinalStage tags(Optional<Set<String>> tags) {
            this.tags = tags;
            return this;
        }

        /**
         * <p>The date and time the conversation was last updated</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage updatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = Optional.ofNullable(updatedAt);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "updatedAt", nulls = Nulls.SKIP)
        public _FinalStage updatedAt(Optional<OffsetDateTime> updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * <p>The date and time the conversation was created</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage createdAt(OffsetDateTime createdAt) {
            this.createdAt = Optional.ofNullable(createdAt);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "createdAt", nulls = Nulls.SKIP)
        public _FinalStage createdAt(Optional<OffsetDateTime> createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * <p>The url of the conversation</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage url(String url) {
            this.url = Optional.ofNullable(url);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "url", nulls = Nulls.SKIP)
        public _FinalStage url(Optional<String> url) {
            this.url = url;
            return this;
        }

        /**
         * <p>The subject of the conversation</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage subject(String subject) {
            this.subject = Optional.ofNullable(subject);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "subject", nulls = Nulls.SKIP)
        public _FinalStage subject(Optional<String> subject) {
            this.subject = subject;
            return this;
        }

        /**
         * <p>Optional configurations for responses to this conversation</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage responseConfig(ResponseConfig responseConfig) {
            this.responseConfig = Optional.ofNullable(responseConfig);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "responseConfig", nulls = Nulls.SKIP)
        public _FinalStage responseConfig(Optional<ResponseConfig> responseConfig) {
            this.responseConfig = responseConfig;
            return this;
        }

        @java.lang.Override
        public BaseConversationResponse build() {
            return new BaseConversationResponse(
                    responseConfig,
                    subject,
                    url,
                    createdAt,
                    updatedAt,
                    tags,
                    metadata,
                    allMetadata,
                    conversationId,
                    analysis,
                    summary,
                    deleted,
                    additionalProperties);
        }
    }
}
