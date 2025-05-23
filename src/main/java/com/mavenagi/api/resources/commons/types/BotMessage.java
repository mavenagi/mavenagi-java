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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = BotMessage.Builder.class)
public final class BotMessage implements IConversationMessageBase {
    private final Optional<OffsetDateTime> createdAt;

    private final Optional<OffsetDateTime> updatedAt;

    private final EntityId conversationMessageId;

    private final BotConversationMessageType botMessageType;

    private final List<BotResponse> responses;

    private final BotResponseMetadata metadata;

    private final BotMessageStatus status;

    private final Map<String, Object> additionalProperties;

    private BotMessage(
            Optional<OffsetDateTime> createdAt,
            Optional<OffsetDateTime> updatedAt,
            EntityId conversationMessageId,
            BotConversationMessageType botMessageType,
            List<BotResponse> responses,
            BotResponseMetadata metadata,
            BotMessageStatus status,
            Map<String, Object> additionalProperties) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.conversationMessageId = conversationMessageId;
        this.botMessageType = botMessageType;
        this.responses = responses;
        this.metadata = metadata;
        this.status = status;
        this.additionalProperties = additionalProperties;
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
    public EntityId getConversationMessageId() {
        return conversationMessageId;
    }

    @JsonProperty("botMessageType")
    public BotConversationMessageType getBotMessageType() {
        return botMessageType;
    }

    @JsonProperty("responses")
    public List<BotResponse> getResponses() {
        return responses;
    }

    @JsonProperty("metadata")
    public BotResponseMetadata getMetadata() {
        return metadata;
    }

    @JsonProperty("status")
    public BotMessageStatus getStatus() {
        return status;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BotMessage && equalTo((BotMessage) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(BotMessage other) {
        return createdAt.equals(other.createdAt)
                && updatedAt.equals(other.updatedAt)
                && conversationMessageId.equals(other.conversationMessageId)
                && botMessageType.equals(other.botMessageType)
                && responses.equals(other.responses)
                && metadata.equals(other.metadata)
                && status.equals(other.status);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.createdAt,
                this.updatedAt,
                this.conversationMessageId,
                this.botMessageType,
                this.responses,
                this.metadata,
                this.status);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ConversationMessageIdStage builder() {
        return new Builder();
    }

    public interface ConversationMessageIdStage {
        BotMessageTypeStage conversationMessageId(@NotNull EntityId conversationMessageId);

        Builder from(BotMessage other);
    }

    public interface BotMessageTypeStage {
        MetadataStage botMessageType(@NotNull BotConversationMessageType botMessageType);
    }

    public interface MetadataStage {
        StatusStage metadata(@NotNull BotResponseMetadata metadata);
    }

    public interface StatusStage {
        _FinalStage status(@NotNull BotMessageStatus status);
    }

    public interface _FinalStage {
        BotMessage build();

        _FinalStage createdAt(Optional<OffsetDateTime> createdAt);

        _FinalStage createdAt(OffsetDateTime createdAt);

        _FinalStage updatedAt(Optional<OffsetDateTime> updatedAt);

        _FinalStage updatedAt(OffsetDateTime updatedAt);

        _FinalStage responses(List<BotResponse> responses);

        _FinalStage addResponses(BotResponse responses);

        _FinalStage addAllResponses(List<BotResponse> responses);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements ConversationMessageIdStage, BotMessageTypeStage, MetadataStage, StatusStage, _FinalStage {
        private EntityId conversationMessageId;

        private BotConversationMessageType botMessageType;

        private BotResponseMetadata metadata;

        private BotMessageStatus status;

        private List<BotResponse> responses = new ArrayList<>();

        private Optional<OffsetDateTime> updatedAt = Optional.empty();

        private Optional<OffsetDateTime> createdAt = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(BotMessage other) {
            createdAt(other.getCreatedAt());
            updatedAt(other.getUpdatedAt());
            conversationMessageId(other.getConversationMessageId());
            botMessageType(other.getBotMessageType());
            responses(other.getResponses());
            metadata(other.getMetadata());
            status(other.getStatus());
            return this;
        }

        /**
         * <p>The ID that uniquely identifies this message within the conversation</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("conversationMessageId")
        public BotMessageTypeStage conversationMessageId(@NotNull EntityId conversationMessageId) {
            this.conversationMessageId =
                    Objects.requireNonNull(conversationMessageId, "conversationMessageId must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("botMessageType")
        public MetadataStage botMessageType(@NotNull BotConversationMessageType botMessageType) {
            this.botMessageType = Objects.requireNonNull(botMessageType, "botMessageType must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("metadata")
        public StatusStage metadata(@NotNull BotResponseMetadata metadata) {
            this.metadata = Objects.requireNonNull(metadata, "metadata must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("status")
        public _FinalStage status(@NotNull BotMessageStatus status) {
            this.status = Objects.requireNonNull(status, "status must not be null");
            return this;
        }

        @java.lang.Override
        public _FinalStage addAllResponses(List<BotResponse> responses) {
            this.responses.addAll(responses);
            return this;
        }

        @java.lang.Override
        public _FinalStage addResponses(BotResponse responses) {
            this.responses.add(responses);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "responses", nulls = Nulls.SKIP)
        public _FinalStage responses(List<BotResponse> responses) {
            this.responses.clear();
            this.responses.addAll(responses);
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
        public BotMessage build() {
            return new BotMessage(
                    createdAt,
                    updatedAt,
                    conversationMessageId,
                    botMessageType,
                    responses,
                    metadata,
                    status,
                    additionalProperties);
        }
    }
}
