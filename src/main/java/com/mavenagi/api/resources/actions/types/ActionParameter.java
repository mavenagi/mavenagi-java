/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.actions.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import com.mavenagi.api.resources.commons.types.ActionParameterType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ActionParameter.Builder.class)
public final class ActionParameter {
    private final String id;

    private final String label;

    private final String description;

    private final boolean required;

    private final Optional<ActionParameterType> type;

    private final Map<String, Object> additionalProperties;

    private ActionParameter(
            String id,
            String label,
            String description,
            boolean required,
            Optional<ActionParameterType> type,
            Map<String, Object> additionalProperties) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.required = required;
        this.type = type;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("required")
    public boolean getRequired() {
        return required;
    }

    /**
     * @return The parameter type. Values provided to executeAction will conform to this type. Defaults to STRING.
     */
    @JsonProperty("type")
    public Optional<ActionParameterType> getType() {
        return type;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ActionParameter && equalTo((ActionParameter) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ActionParameter other) {
        return id.equals(other.id)
                && label.equals(other.label)
                && description.equals(other.description)
                && required == other.required
                && type.equals(other.type);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.id, this.label, this.description, this.required, this.type);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static IdStage builder() {
        return new Builder();
    }

    public interface IdStage {
        LabelStage id(@NotNull String id);

        Builder from(ActionParameter other);
    }

    public interface LabelStage {
        DescriptionStage label(@NotNull String label);
    }

    public interface DescriptionStage {
        RequiredStage description(@NotNull String description);
    }

    public interface RequiredStage {
        _FinalStage required(boolean required);
    }

    public interface _FinalStage {
        ActionParameter build();

        _FinalStage type(Optional<ActionParameterType> type);

        _FinalStage type(ActionParameterType type);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements IdStage, LabelStage, DescriptionStage, RequiredStage, _FinalStage {
        private String id;

        private String label;

        private String description;

        private boolean required;

        private Optional<ActionParameterType> type = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(ActionParameter other) {
            id(other.getId());
            label(other.getLabel());
            description(other.getDescription());
            required(other.getRequired());
            type(other.getType());
            return this;
        }

        @java.lang.Override
        @JsonSetter("id")
        public LabelStage id(@NotNull String id) {
            this.id = Objects.requireNonNull(id, "id must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("label")
        public DescriptionStage label(@NotNull String label) {
            this.label = Objects.requireNonNull(label, "label must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("description")
        public RequiredStage description(@NotNull String description) {
            this.description = Objects.requireNonNull(description, "description must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("required")
        public _FinalStage required(boolean required) {
            this.required = required;
            return this;
        }

        /**
         * <p>The parameter type. Values provided to executeAction will conform to this type. Defaults to STRING.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage type(ActionParameterType type) {
            this.type = Optional.ofNullable(type);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "type", nulls = Nulls.SKIP)
        public _FinalStage type(Optional<ActionParameterType> type) {
            this.type = type;
            return this;
        }

        @java.lang.Override
        public ActionParameter build() {
            return new ActionParameter(id, label, description, required, type, additionalProperties);
        }
    }
}
