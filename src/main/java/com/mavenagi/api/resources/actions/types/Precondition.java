/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.actions.types;

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

public final class Precondition {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private Precondition(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static Precondition user(MetadataPrecondition value) {
        return new Precondition(new UserValue(value));
    }

    public static Precondition conversation(ConversationPrecondition value) {
        return new Precondition(new ConversationValue(value));
    }

    public static Precondition group(PreconditionGroup value) {
        return new Precondition(new GroupValue(value));
    }

    public boolean isUser() {
        return value instanceof UserValue;
    }

    public boolean isConversation() {
        return value instanceof ConversationValue;
    }

    public boolean isGroup() {
        return value instanceof GroupValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<MetadataPrecondition> getUser() {
        if (isUser()) {
            return Optional.of(((UserValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<ConversationPrecondition> getConversation() {
        if (isConversation()) {
            return Optional.of(((ConversationValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<PreconditionGroup> getGroup() {
        if (isGroup()) {
            return Optional.of(((GroupValue) value).value);
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
        T visitUser(MetadataPrecondition user);

        T visitConversation(ConversationPrecondition conversation);

        T visitGroup(PreconditionGroup group);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            property = "preconditionType",
            visible = true,
            defaultImpl = _UnknownValue.class)
    @JsonSubTypes({
        @JsonSubTypes.Type(UserValue.class),
        @JsonSubTypes.Type(ConversationValue.class),
        @JsonSubTypes.Type(GroupValue.class)
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("user")
    private static final class UserValue implements Value {
        @JsonUnwrapped
        private MetadataPrecondition value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private UserValue() {}

        private UserValue(MetadataPrecondition value) {
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
            return "Precondition{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("conversation")
    private static final class ConversationValue implements Value {
        @JsonProperty("value")
        private ConversationPrecondition value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private ConversationValue(@JsonProperty("value") ConversationPrecondition value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitConversation(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof ConversationValue && equalTo((ConversationValue) other);
        }

        private boolean equalTo(ConversationValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "Precondition{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("group")
    private static final class GroupValue implements Value {
        @JsonUnwrapped
        private PreconditionGroup value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private GroupValue() {}

        private GroupValue(PreconditionGroup value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitGroup(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof GroupValue && equalTo((GroupValue) other);
        }

        private boolean equalTo(GroupValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "Precondition{" + "value: " + value + "}";
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
            return "Precondition{" + "type: " + type + ", value: " + value + "}";
        }
    }
}