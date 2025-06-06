/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.analytics.types;

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

public final class FeedbackMetric {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private FeedbackMetric(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static FeedbackMetric count(FeedbackCount value) {
        return new FeedbackMetric(new CountValue(value));
    }

    public static FeedbackMetric distinctCount(FeedbackDistinctCount value) {
        return new FeedbackMetric(new DistinctCountValue(value));
    }

    public boolean isCount() {
        return value instanceof CountValue;
    }

    public boolean isDistinctCount() {
        return value instanceof DistinctCountValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<FeedbackCount> getCount() {
        if (isCount()) {
            return Optional.of(((CountValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<FeedbackDistinctCount> getDistinctCount() {
        if (isDistinctCount()) {
            return Optional.of(((DistinctCountValue) value).value);
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
        T visitCount(FeedbackCount count);

        T visitDistinctCount(FeedbackDistinctCount distinctCount);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({@JsonSubTypes.Type(CountValue.class), @JsonSubTypes.Type(DistinctCountValue.class)})
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("count")
    private static final class CountValue implements Value {
        @JsonUnwrapped
        private FeedbackCount value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private CountValue() {}

        private CountValue(FeedbackCount value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitCount(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof CountValue && equalTo((CountValue) other);
        }

        private boolean equalTo(CountValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "FeedbackMetric{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("distinctCount")
    private static final class DistinctCountValue implements Value {
        @JsonUnwrapped
        private FeedbackDistinctCount value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private DistinctCountValue() {}

        private DistinctCountValue(FeedbackDistinctCount value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitDistinctCount(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof DistinctCountValue && equalTo((DistinctCountValue) other);
        }

        private boolean equalTo(DistinctCountValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "FeedbackMetric{" + "value: " + value + "}";
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
            return "FeedbackMetric{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
