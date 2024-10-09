/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.conversation.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.Optional;

public final class ConversationMessageResponse {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private ConversationMessageResponse(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static ConversationMessageResponse user(UserMessage value) {
        return new ConversationMessageResponse(new UserValue(value));
    }

    public static ConversationMessageResponse bot(BotMessage value) {
        return new ConversationMessageResponse(new BotValue(value));
    }

    public boolean isUser() {
        return value instanceof UserValue;
    }

    public boolean isBot() {
        return value instanceof BotValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<UserMessage> getUser() {
        if (isUser()) {
            return Optional.of(((UserValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<BotMessage> getBot() {
        if (isBot()) {
            return Optional.of(((BotValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Object> _getUnknown() {
        if (_isUnknown()) {
            return Optional.of(((_UnknownValue) value).value);
        }
        return Optional.empty();
    }

    @JsonValue
    private Value getValue() {
        return this.value;
    }

    public interface Visitor<T> {
        T visitUser(UserMessage user);

        T visitBot(BotMessage bot);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({@JsonSubTypes.Type(UserValue.class), @JsonSubTypes.Type(BotValue.class)})
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("user")
    private static final class UserValue implements Value {
        @JsonUnwrapped
        private UserMessage value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private UserValue() {}

        private UserValue(UserMessage value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitUser(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof UserValue && equalTo((UserValue) other);
        }

        private boolean equalTo(UserValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "ConversationMessageResponse{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("bot")
    private static final class BotValue implements Value {
        @JsonUnwrapped
        private BotMessage value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private BotValue() {}

        private BotValue(BotMessage value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitBot(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof BotValue && equalTo((BotValue) other);
        }

        private boolean equalTo(BotValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "ConversationMessageResponse{" + "value: " + value + "}";
        }
    }

    private static final class _UnknownValue implements Value {
        private String type;

        @JsonValue
        private Object value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private _UnknownValue(@JsonProperty("value") Object value) {}

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor._visitUnknown(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof _UnknownValue && equalTo((_UnknownValue) other);
        }

        private boolean equalTo(_UnknownValue other) {
            return type.equals(other.type) && value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.type, this.value);
        }

        @java.lang.Override
        public String toString() {
            return "ConversationMessageResponse{" + "type: " + type + ", value: " + value + "}";
        }
    }
}