/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.analyticscommons.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class CellData {
    private final Value value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    private CellData(Value value) {
        this.value = value;
    }

    public <T> T visit(Visitor<T> visitor) {
        return value.visit(visitor);
    }

    public static CellData double_(double value) {
        return new CellData(new DoubleValue(value));
    }

    public static CellData long_(long value) {
        return new CellData(new LongValue(value));
    }

    public static CellData millisecond(double value) {
        return new CellData(new MillisecondValue(value));
    }

    public static CellData string(String value) {
        return new CellData(new StringValue(value));
    }

    public static CellData percentileMap(Map<String, Double> value) {
        return new CellData(new PercentileMapValue(value));
    }

    public boolean isDouble() {
        return value instanceof DoubleValue;
    }

    public boolean isLong() {
        return value instanceof LongValue;
    }

    public boolean isMillisecond() {
        return value instanceof MillisecondValue;
    }

    public boolean isString() {
        return value instanceof StringValue;
    }

    public boolean isPercentileMap() {
        return value instanceof PercentileMapValue;
    }

    public boolean _isUnknown() {
        return value instanceof _UnknownValue;
    }

    public Optional<Double> getDouble() {
        if (isDouble()) {
            return Optional.of(((DoubleValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Long> getLong() {
        if (isLong()) {
            return Optional.of(((LongValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Double> getMillisecond() {
        if (isMillisecond()) {
            return Optional.of(((MillisecondValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<String> getString() {
        if (isString()) {
            return Optional.of(((StringValue) value).value);
        }
        return Optional.empty();
    }

    public Optional<Map<String, Double>> getPercentileMap() {
        if (isPercentileMap()) {
            return Optional.of(((PercentileMapValue) value).value);
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
        T visitDouble(double double_);

        T visitLong(long long_);

        T visitMillisecond(double millisecond);

        T visitString(String string);

        T visitPercentileMap(Map<String, Double> percentileMap);

        T _visitUnknown(Object unknownType);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, defaultImpl = _UnknownValue.class)
    @JsonSubTypes({
        @JsonSubTypes.Type(DoubleValue.class),
        @JsonSubTypes.Type(LongValue.class),
        @JsonSubTypes.Type(MillisecondValue.class),
        @JsonSubTypes.Type(StringValue.class),
        @JsonSubTypes.Type(PercentileMapValue.class)
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    private interface Value {
        <T> T visit(Visitor<T> visitor);
    }

    @JsonTypeName("double")
    private static final class DoubleValue implements Value {
        @JsonProperty("value")
        private double value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private DoubleValue(@JsonProperty("value") double value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitDouble(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof DoubleValue && equalTo((DoubleValue) other);
        }

        private boolean equalTo(DoubleValue other) {
            return value == other.value;
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "CellData{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("long")
    private static final class LongValue implements Value {
        @JsonProperty("value")
        private long value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private LongValue(@JsonProperty("value") long value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitLong(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof LongValue && equalTo((LongValue) other);
        }

        private boolean equalTo(LongValue other) {
            return value == other.value;
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "CellData{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("millisecond")
    private static final class MillisecondValue implements Value {
        @JsonProperty("value")
        private double value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private MillisecondValue(@JsonProperty("value") double value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitMillisecond(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof MillisecondValue && equalTo((MillisecondValue) other);
        }

        private boolean equalTo(MillisecondValue other) {
            return value == other.value;
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "CellData{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("string")
    private static final class StringValue implements Value {
        @JsonProperty("value")
        private String value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private StringValue(@JsonProperty("value") String value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitString(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof StringValue && equalTo((StringValue) other);
        }

        private boolean equalTo(StringValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "CellData{" + "value: " + value + "}";
        }
    }

    @JsonTypeName("percentileMap")
    private static final class PercentileMapValue implements Value {
        @JsonProperty("value")
        private Map<String, Double> value;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        private PercentileMapValue(@JsonProperty("value") Map<String, Double> value) {
            this.value = value;
        }

        @java.lang.Override
        public <T> T visit(Visitor<T> visitor) {
            return visitor.visitPercentileMap(value);
        }

        @java.lang.Override
        public boolean equals(Object other) {
            if (this == other) return true;
            return other instanceof PercentileMapValue && equalTo((PercentileMapValue) other);
        }

        private boolean equalTo(PercentileMapValue other) {
            return value.equals(other.value);
        }

        @java.lang.Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @java.lang.Override
        public String toString() {
            return "CellData{" + "value: " + value + "}";
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
            return "CellData{" + "type: " + type + ", value: " + value + "}";
        }
    }
}
