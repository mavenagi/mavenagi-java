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
import com.mavenagi.api.resources.commons.types.EntityIdBase;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ConversationMessageRequest.Builder.class)
public final class ConversationMessageRequest implements IUserMessageBase {
    private final EntityIdBase userId;

    private final String text;

    private final UserConversationMessageType userMessageType;

    private final Optional<OffsetDateTime> createdAt;

    private final Optional<OffsetDateTime> updatedAt;

    private final EntityIdBase conversationMessageId;

    private final Map<String, Object> additionalProperties;

    private ConversationMessageRequest(
            EntityIdBase userId,
            String text,
            UserConversationMessageType userMessageType,
            Optional<OffsetDateTime> createdAt,
            Optional<OffsetDateTime> updatedAt,
            EntityIdBase conversationMessageId,
            Map<String, Object> additionalProperties) {
        this.userId = userId;
        this.text = text;
        this.userMessageType = userMessageType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.conversationMessageId = conversationMessageId;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return ID that uniquely identifies the user that created this message
     */
    @JsonProperty("userId")
    @java.lang.Override
    public EntityIdBase getUserId() {
        return userId;
    }

    /**
     * @return The text of the message. Cannot be empty
     */
    @JsonProperty("text")
    @java.lang.Override
    public String getText() {
        return text;
    }

    @JsonProperty("userMessageType")
    @java.lang.Override
    public UserConversationMessageType getUserMessageType() {
        return userMessageType;
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
     * @return The ID that uniquely identifies this message within the conversation
     */
    @JsonProperty("conversationMessageId")
    public EntityIdBase getConversationMessageId() {
        return conversationMessageId;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ConversationMessageRequest && equalTo((ConversationMessageRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ConversationMessageRequest other) {
        return userId.equals(other.userId)
                && text.equals(other.text)
                && userMessageType.equals(other.userMessageType)
                && createdAt.equals(other.createdAt)
                && updatedAt.equals(other.updatedAt)
                && conversationMessageId.equals(other.conversationMessageId);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.userId,
                this.text,
                this.userMessageType,
                this.createdAt,
                this.updatedAt,
                this.conversationMessageId);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static UserIdStage builder() {
        return new Builder();
    }

    public interface UserIdStage {
        TextStage userId(@NotNull EntityIdBase userId);

        Builder from(ConversationMessageRequest other);
    }

    public interface TextStage {
        UserMessageTypeStage text(@NotNull String text);
    }

    public interface UserMessageTypeStage {
        ConversationMessageIdStage userMessageType(@NotNull UserConversationMessageType userMessageType);
    }

    public interface ConversationMessageIdStage {
        _FinalStage conversationMessageId(@NotNull EntityIdBase conversationMessageId);
    }

    public interface _FinalStage {
        ConversationMessageRequest build();

        _FinalStage createdAt(Optional<OffsetDateTime> createdAt);

        _FinalStage createdAt(OffsetDateTime createdAt);

        _FinalStage updatedAt(Optional<OffsetDateTime> updatedAt);

        _FinalStage updatedAt(OffsetDateTime updatedAt);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements UserIdStage, TextStage, UserMessageTypeStage, ConversationMessageIdStage, _FinalStage {
        private EntityIdBase userId;

        private String text;

        private UserConversationMessageType userMessageType;

        private EntityIdBase conversationMessageId;

        private Optional<OffsetDateTime> updatedAt = Optional.empty();

        private Optional<OffsetDateTime> createdAt = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(ConversationMessageRequest other) {
            userId(other.getUserId());
            text(other.getText());
            userMessageType(other.getUserMessageType());
            createdAt(other.getCreatedAt());
            updatedAt(other.getUpdatedAt());
            conversationMessageId(other.getConversationMessageId());
            return this;
        }

        /**
         * <p>ID that uniquely identifies the user that created this message</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("userId")
        public TextStage userId(@NotNull EntityIdBase userId) {
            this.userId = Objects.requireNonNull(userId, "userId must not be null");
            return this;
        }

        /**
         * <p>The text of the message. Cannot be empty</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("text")
        public UserMessageTypeStage text(@NotNull String text) {
            this.text = Objects.requireNonNull(text, "text must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("userMessageType")
        public ConversationMessageIdStage userMessageType(@NotNull UserConversationMessageType userMessageType) {
            this.userMessageType = Objects.requireNonNull(userMessageType, "userMessageType must not be null");
            return this;
        }

        /**
         * <p>The ID that uniquely identifies this message within the conversation</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("conversationMessageId")
        public _FinalStage conversationMessageId(@NotNull EntityIdBase conversationMessageId) {
            this.conversationMessageId =
                    Objects.requireNonNull(conversationMessageId, "conversationMessageId must not be null");
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

        @java.lang.Override
        public ConversationMessageRequest build() {
            return new ConversationMessageRequest(
                    userId, text, userMessageType, createdAt, updatedAt, conversationMessageId, additionalProperties);
        }
    }
}
