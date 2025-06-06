/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.events.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import com.mavenagi.api.resources.commons.types.EntityId;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = UserEvent.Builder.class)
public final class UserEvent implements IEventBaseNoId {
    private final Optional<OffsetDateTime> timestamp;

    private final Optional<Set<EntityId>> references;

    private final Optional<SourceInfo> sourceInfo;

    private final Optional<SessionInfo> sessionInfo;

    private final Optional<ContextInfo> contextInfo;

    private final EntityId id;

    private final UserEventName eventName;

    private final UserInfo userInfo;

    private final Optional<List<FeedbackInfo>> feedbackInfo;

    private final Optional<PageInfo> pageInfo;

    private final Map<String, Object> additionalProperties;

    private UserEvent(
            Optional<OffsetDateTime> timestamp,
            Optional<Set<EntityId>> references,
            Optional<SourceInfo> sourceInfo,
            Optional<SessionInfo> sessionInfo,
            Optional<ContextInfo> contextInfo,
            EntityId id,
            UserEventName eventName,
            UserInfo userInfo,
            Optional<List<FeedbackInfo>> feedbackInfo,
            Optional<PageInfo> pageInfo,
            Map<String, Object> additionalProperties) {
        this.timestamp = timestamp;
        this.references = references;
        this.sourceInfo = sourceInfo;
        this.sessionInfo = sessionInfo;
        this.contextInfo = contextInfo;
        this.id = id;
        this.eventName = eventName;
        this.userInfo = userInfo;
        this.feedbackInfo = feedbackInfo;
        this.pageInfo = pageInfo;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("timestamp")
    @java.lang.Override
    public Optional<OffsetDateTime> getTimestamp() {
        return timestamp;
    }

    @JsonProperty("references")
    @java.lang.Override
    public Optional<Set<EntityId>> getReferences() {
        return references;
    }

    @JsonProperty("sourceInfo")
    @java.lang.Override
    public Optional<SourceInfo> getSourceInfo() {
        return sourceInfo;
    }

    @JsonProperty("sessionInfo")
    @java.lang.Override
    public Optional<SessionInfo> getSessionInfo() {
        return sessionInfo;
    }

    @JsonProperty("contextInfo")
    @java.lang.Override
    public Optional<ContextInfo> getContextInfo() {
        return contextInfo;
    }

    /**
     * @return The unique ID of the event
     */
    @JsonProperty("id")
    public EntityId getId() {
        return id;
    }

    /**
     * @return The name of the event
     */
    @JsonProperty("eventName")
    public UserEventName getEventName() {
        return eventName;
    }

    /**
     * @return Information about the user who triggered the event
     */
    @JsonProperty("userInfo")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @return Information about any feedback associated with the event
     */
    @JsonProperty("feedbackInfo")
    public Optional<List<FeedbackInfo>> getFeedbackInfo() {
        return feedbackInfo;
    }

    /**
     * @return Information about the page on which the event occurred
     */
    @JsonProperty("pageInfo")
    public Optional<PageInfo> getPageInfo() {
        return pageInfo;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof UserEvent && equalTo((UserEvent) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(UserEvent other) {
        return timestamp.equals(other.timestamp)
                && references.equals(other.references)
                && sourceInfo.equals(other.sourceInfo)
                && sessionInfo.equals(other.sessionInfo)
                && contextInfo.equals(other.contextInfo)
                && id.equals(other.id)
                && eventName.equals(other.eventName)
                && userInfo.equals(other.userInfo)
                && feedbackInfo.equals(other.feedbackInfo)
                && pageInfo.equals(other.pageInfo);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.timestamp,
                this.references,
                this.sourceInfo,
                this.sessionInfo,
                this.contextInfo,
                this.id,
                this.eventName,
                this.userInfo,
                this.feedbackInfo,
                this.pageInfo);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static IdStage builder() {
        return new Builder();
    }

    public interface IdStage {
        EventNameStage id(@NotNull EntityId id);

        Builder from(UserEvent other);
    }

    public interface EventNameStage {
        UserInfoStage eventName(@NotNull UserEventName eventName);
    }

    public interface UserInfoStage {
        _FinalStage userInfo(@NotNull UserInfo userInfo);
    }

    public interface _FinalStage {
        UserEvent build();

        _FinalStage timestamp(Optional<OffsetDateTime> timestamp);

        _FinalStage timestamp(OffsetDateTime timestamp);

        _FinalStage references(Optional<Set<EntityId>> references);

        _FinalStage references(Set<EntityId> references);

        _FinalStage sourceInfo(Optional<SourceInfo> sourceInfo);

        _FinalStage sourceInfo(SourceInfo sourceInfo);

        _FinalStage sessionInfo(Optional<SessionInfo> sessionInfo);

        _FinalStage sessionInfo(SessionInfo sessionInfo);

        _FinalStage contextInfo(Optional<ContextInfo> contextInfo);

        _FinalStage contextInfo(ContextInfo contextInfo);

        _FinalStage feedbackInfo(Optional<List<FeedbackInfo>> feedbackInfo);

        _FinalStage feedbackInfo(List<FeedbackInfo> feedbackInfo);

        _FinalStage pageInfo(Optional<PageInfo> pageInfo);

        _FinalStage pageInfo(PageInfo pageInfo);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements IdStage, EventNameStage, UserInfoStage, _FinalStage {
        private EntityId id;

        private UserEventName eventName;

        private UserInfo userInfo;

        private Optional<PageInfo> pageInfo = Optional.empty();

        private Optional<List<FeedbackInfo>> feedbackInfo = Optional.empty();

        private Optional<ContextInfo> contextInfo = Optional.empty();

        private Optional<SessionInfo> sessionInfo = Optional.empty();

        private Optional<SourceInfo> sourceInfo = Optional.empty();

        private Optional<Set<EntityId>> references = Optional.empty();

        private Optional<OffsetDateTime> timestamp = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(UserEvent other) {
            timestamp(other.getTimestamp());
            references(other.getReferences());
            sourceInfo(other.getSourceInfo());
            sessionInfo(other.getSessionInfo());
            contextInfo(other.getContextInfo());
            id(other.getId());
            eventName(other.getEventName());
            userInfo(other.getUserInfo());
            feedbackInfo(other.getFeedbackInfo());
            pageInfo(other.getPageInfo());
            return this;
        }

        /**
         * <p>The unique ID of the event</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("id")
        public EventNameStage id(@NotNull EntityId id) {
            this.id = Objects.requireNonNull(id, "id must not be null");
            return this;
        }

        /**
         * <p>The name of the event</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("eventName")
        public UserInfoStage eventName(@NotNull UserEventName eventName) {
            this.eventName = Objects.requireNonNull(eventName, "eventName must not be null");
            return this;
        }

        /**
         * <p>Information about the user who triggered the event</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("userInfo")
        public _FinalStage userInfo(@NotNull UserInfo userInfo) {
            this.userInfo = Objects.requireNonNull(userInfo, "userInfo must not be null");
            return this;
        }

        /**
         * <p>Information about the page on which the event occurred</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage pageInfo(PageInfo pageInfo) {
            this.pageInfo = Optional.ofNullable(pageInfo);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "pageInfo", nulls = Nulls.SKIP)
        public _FinalStage pageInfo(Optional<PageInfo> pageInfo) {
            this.pageInfo = pageInfo;
            return this;
        }

        /**
         * <p>Information about any feedback associated with the event</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage feedbackInfo(List<FeedbackInfo> feedbackInfo) {
            this.feedbackInfo = Optional.ofNullable(feedbackInfo);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "feedbackInfo", nulls = Nulls.SKIP)
        public _FinalStage feedbackInfo(Optional<List<FeedbackInfo>> feedbackInfo) {
            this.feedbackInfo = feedbackInfo;
            return this;
        }

        @java.lang.Override
        public _FinalStage contextInfo(ContextInfo contextInfo) {
            this.contextInfo = Optional.ofNullable(contextInfo);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "contextInfo", nulls = Nulls.SKIP)
        public _FinalStage contextInfo(Optional<ContextInfo> contextInfo) {
            this.contextInfo = contextInfo;
            return this;
        }

        @java.lang.Override
        public _FinalStage sessionInfo(SessionInfo sessionInfo) {
            this.sessionInfo = Optional.ofNullable(sessionInfo);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "sessionInfo", nulls = Nulls.SKIP)
        public _FinalStage sessionInfo(Optional<SessionInfo> sessionInfo) {
            this.sessionInfo = sessionInfo;
            return this;
        }

        @java.lang.Override
        public _FinalStage sourceInfo(SourceInfo sourceInfo) {
            this.sourceInfo = Optional.ofNullable(sourceInfo);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "sourceInfo", nulls = Nulls.SKIP)
        public _FinalStage sourceInfo(Optional<SourceInfo> sourceInfo) {
            this.sourceInfo = sourceInfo;
            return this;
        }

        @java.lang.Override
        public _FinalStage references(Set<EntityId> references) {
            this.references = Optional.ofNullable(references);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "references", nulls = Nulls.SKIP)
        public _FinalStage references(Optional<Set<EntityId>> references) {
            this.references = references;
            return this;
        }

        @java.lang.Override
        public _FinalStage timestamp(OffsetDateTime timestamp) {
            this.timestamp = Optional.ofNullable(timestamp);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "timestamp", nulls = Nulls.SKIP)
        public _FinalStage timestamp(Optional<OffsetDateTime> timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        @java.lang.Override
        public UserEvent build() {
            return new UserEvent(
                    timestamp,
                    references,
                    sourceInfo,
                    sessionInfo,
                    contextInfo,
                    id,
                    eventName,
                    userInfo,
                    feedbackInfo,
                    pageInfo,
                    additionalProperties);
        }
    }
}
