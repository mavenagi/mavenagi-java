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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = GenerateObjectRequest.Builder.class)
public final class GenerateObjectRequest implements IAskRequest {
    private final EntityIdBase conversationMessageId;

    private final EntityIdBase userId;

    private final String text;

    private final Optional<List<Attachment>> attachments;

    private final Optional<Map<String, String>> transientData;

    private final Optional<String> timezone;

    private final String schema;

    private final Map<String, Object> additionalProperties;

    private GenerateObjectRequest(
            EntityIdBase conversationMessageId,
            EntityIdBase userId,
            String text,
            Optional<List<Attachment>> attachments,
            Optional<Map<String, String>> transientData,
            Optional<String> timezone,
            String schema,
            Map<String, Object> additionalProperties) {
        this.conversationMessageId = conversationMessageId;
        this.userId = userId;
        this.text = text;
        this.attachments = attachments;
        this.transientData = transientData;
        this.timezone = timezone;
        this.schema = schema;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Externally supplied ID to uniquely identify this message within the conversation. If a message with this ID already exists it will be reused and will not be updated.
     */
    @JsonProperty("conversationMessageId")
    @java.lang.Override
    public EntityIdBase getConversationMessageId() {
        return conversationMessageId;
    }

    /**
     * @return Externally supplied ID to uniquely identify the user that created this message
     */
    @JsonProperty("userId")
    @java.lang.Override
    public EntityIdBase getUserId() {
        return userId;
    }

    /**
     * @return The text of the message
     */
    @JsonProperty("text")
    @java.lang.Override
    public String getText() {
        return text;
    }

    /**
     * @return The attachments to the message.
     */
    @JsonProperty("attachments")
    @java.lang.Override
    public Optional<List<Attachment>> getAttachments() {
        return attachments;
    }

    /**
     * @return Transient data which the Maven platform will not persist. This data will only be forwarded to actions taken by this ask request. For example, one may put in user tokens as transient data.
     */
    @JsonProperty("transientData")
    @java.lang.Override
    public Optional<Map<String, String>> getTransientData() {
        return transientData;
    }

    /**
     * @return IANA timezone identifier (e.g. &quot;America/New_York&quot;, &quot;Europe/London&quot;) to be used for time-based operations in the conversation.
     */
    @JsonProperty("timezone")
    @java.lang.Override
    public Optional<String> getTimezone() {
        return timezone;
    }

    /**
     * @return JSON schema string defining the expected object shape.
     */
    @JsonProperty("schema")
    public String getSchema() {
        return schema;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GenerateObjectRequest && equalTo((GenerateObjectRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(GenerateObjectRequest other) {
        return conversationMessageId.equals(other.conversationMessageId)
                && userId.equals(other.userId)
                && text.equals(other.text)
                && attachments.equals(other.attachments)
                && transientData.equals(other.transientData)
                && timezone.equals(other.timezone)
                && schema.equals(other.schema);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.conversationMessageId,
                this.userId,
                this.text,
                this.attachments,
                this.transientData,
                this.timezone,
                this.schema);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static ConversationMessageIdStage builder() {
        return new Builder();
    }

    public interface ConversationMessageIdStage {
        UserIdStage conversationMessageId(@NotNull EntityIdBase conversationMessageId);

        Builder from(GenerateObjectRequest other);
    }

    public interface UserIdStage {
        TextStage userId(@NotNull EntityIdBase userId);
    }

    public interface TextStage {
        SchemaStage text(@NotNull String text);
    }

    public interface SchemaStage {
        _FinalStage schema(@NotNull String schema);
    }

    public interface _FinalStage {
        GenerateObjectRequest build();

        _FinalStage attachments(Optional<List<Attachment>> attachments);

        _FinalStage attachments(List<Attachment> attachments);

        _FinalStage transientData(Optional<Map<String, String>> transientData);

        _FinalStage transientData(Map<String, String> transientData);

        _FinalStage timezone(Optional<String> timezone);

        _FinalStage timezone(String timezone);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements ConversationMessageIdStage, UserIdStage, TextStage, SchemaStage, _FinalStage {
        private EntityIdBase conversationMessageId;

        private EntityIdBase userId;

        private String text;

        private String schema;

        private Optional<String> timezone = Optional.empty();

        private Optional<Map<String, String>> transientData = Optional.empty();

        private Optional<List<Attachment>> attachments = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(GenerateObjectRequest other) {
            conversationMessageId(other.getConversationMessageId());
            userId(other.getUserId());
            text(other.getText());
            attachments(other.getAttachments());
            transientData(other.getTransientData());
            timezone(other.getTimezone());
            schema(other.getSchema());
            return this;
        }

        /**
         * <p>Externally supplied ID to uniquely identify this message within the conversation. If a message with this ID already exists it will be reused and will not be updated.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("conversationMessageId")
        public UserIdStage conversationMessageId(@NotNull EntityIdBase conversationMessageId) {
            this.conversationMessageId =
                    Objects.requireNonNull(conversationMessageId, "conversationMessageId must not be null");
            return this;
        }

        /**
         * <p>Externally supplied ID to uniquely identify the user that created this message</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("userId")
        public TextStage userId(@NotNull EntityIdBase userId) {
            this.userId = Objects.requireNonNull(userId, "userId must not be null");
            return this;
        }

        /**
         * <p>The text of the message</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("text")
        public SchemaStage text(@NotNull String text) {
            this.text = Objects.requireNonNull(text, "text must not be null");
            return this;
        }

        /**
         * <p>JSON schema string defining the expected object shape.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("schema")
        public _FinalStage schema(@NotNull String schema) {
            this.schema = Objects.requireNonNull(schema, "schema must not be null");
            return this;
        }

        /**
         * <p>IANA timezone identifier (e.g. &quot;America/New_York&quot;, &quot;Europe/London&quot;) to be used for time-based operations in the conversation.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage timezone(String timezone) {
            this.timezone = Optional.ofNullable(timezone);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "timezone", nulls = Nulls.SKIP)
        public _FinalStage timezone(Optional<String> timezone) {
            this.timezone = timezone;
            return this;
        }

        /**
         * <p>Transient data which the Maven platform will not persist. This data will only be forwarded to actions taken by this ask request. For example, one may put in user tokens as transient data.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage transientData(Map<String, String> transientData) {
            this.transientData = Optional.ofNullable(transientData);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "transientData", nulls = Nulls.SKIP)
        public _FinalStage transientData(Optional<Map<String, String>> transientData) {
            this.transientData = transientData;
            return this;
        }

        /**
         * <p>The attachments to the message.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage attachments(List<Attachment> attachments) {
            this.attachments = Optional.ofNullable(attachments);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "attachments", nulls = Nulls.SKIP)
        public _FinalStage attachments(Optional<List<Attachment>> attachments) {
            this.attachments = attachments;
            return this;
        }

        @java.lang.Override
        public GenerateObjectRequest build() {
            return new GenerateObjectRequest(
                    conversationMessageId,
                    userId,
                    text,
                    attachments,
                    transientData,
                    timezone,
                    schema,
                    additionalProperties);
        }
    }
}
