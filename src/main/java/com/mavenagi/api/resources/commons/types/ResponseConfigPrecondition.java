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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ResponseConfigPrecondition.Builder.class)
public final class ResponseConfigPrecondition implements IPreconditionBase {
    private final Optional<PreconditionOperator> operator;

    private final Optional<Boolean> useMarkdown;

    private final Optional<Boolean> useForms;

    private final Optional<Boolean> useImages;

    private final Optional<Boolean> isCopilot;

    private final Optional<ResponseLength> responseLength;

    private final Map<String, Object> additionalProperties;

    private ResponseConfigPrecondition(
            Optional<PreconditionOperator> operator,
            Optional<Boolean> useMarkdown,
            Optional<Boolean> useForms,
            Optional<Boolean> useImages,
            Optional<Boolean> isCopilot,
            Optional<ResponseLength> responseLength,
            Map<String, Object> additionalProperties) {
        this.operator = operator;
        this.useMarkdown = useMarkdown;
        this.useForms = useForms;
        this.useImages = useImages;
        this.isCopilot = isCopilot;
        this.responseLength = responseLength;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Operator to apply to this precondition
     */
    @JsonProperty("operator")
    @java.lang.Override
    public Optional<PreconditionOperator> getOperator() {
        return operator;
    }

    @JsonProperty("useMarkdown")
    public Optional<Boolean> getUseMarkdown() {
        return useMarkdown;
    }

    @JsonProperty("useForms")
    public Optional<Boolean> getUseForms() {
        return useForms;
    }

    @JsonProperty("useImages")
    public Optional<Boolean> getUseImages() {
        return useImages;
    }

    @JsonProperty("isCopilot")
    public Optional<Boolean> getIsCopilot() {
        return isCopilot;
    }

    @JsonProperty("responseLength")
    public Optional<ResponseLength> getResponseLength() {
        return responseLength;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ResponseConfigPrecondition && equalTo((ResponseConfigPrecondition) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ResponseConfigPrecondition other) {
        return operator.equals(other.operator)
                && useMarkdown.equals(other.useMarkdown)
                && useForms.equals(other.useForms)
                && useImages.equals(other.useImages)
                && isCopilot.equals(other.isCopilot)
                && responseLength.equals(other.responseLength);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.operator, this.useMarkdown, this.useForms, this.useImages, this.isCopilot, this.responseLength);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Optional<PreconditionOperator> operator = Optional.empty();

        private Optional<Boolean> useMarkdown = Optional.empty();

        private Optional<Boolean> useForms = Optional.empty();

        private Optional<Boolean> useImages = Optional.empty();

        private Optional<Boolean> isCopilot = Optional.empty();

        private Optional<ResponseLength> responseLength = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(ResponseConfigPrecondition other) {
            operator(other.getOperator());
            useMarkdown(other.getUseMarkdown());
            useForms(other.getUseForms());
            useImages(other.getUseImages());
            isCopilot(other.getIsCopilot());
            responseLength(other.getResponseLength());
            return this;
        }

        @JsonSetter(value = "operator", nulls = Nulls.SKIP)
        public Builder operator(Optional<PreconditionOperator> operator) {
            this.operator = operator;
            return this;
        }

        public Builder operator(PreconditionOperator operator) {
            this.operator = Optional.ofNullable(operator);
            return this;
        }

        @JsonSetter(value = "useMarkdown", nulls = Nulls.SKIP)
        public Builder useMarkdown(Optional<Boolean> useMarkdown) {
            this.useMarkdown = useMarkdown;
            return this;
        }

        public Builder useMarkdown(Boolean useMarkdown) {
            this.useMarkdown = Optional.ofNullable(useMarkdown);
            return this;
        }

        @JsonSetter(value = "useForms", nulls = Nulls.SKIP)
        public Builder useForms(Optional<Boolean> useForms) {
            this.useForms = useForms;
            return this;
        }

        public Builder useForms(Boolean useForms) {
            this.useForms = Optional.ofNullable(useForms);
            return this;
        }

        @JsonSetter(value = "useImages", nulls = Nulls.SKIP)
        public Builder useImages(Optional<Boolean> useImages) {
            this.useImages = useImages;
            return this;
        }

        public Builder useImages(Boolean useImages) {
            this.useImages = Optional.ofNullable(useImages);
            return this;
        }

        @JsonSetter(value = "isCopilot", nulls = Nulls.SKIP)
        public Builder isCopilot(Optional<Boolean> isCopilot) {
            this.isCopilot = isCopilot;
            return this;
        }

        public Builder isCopilot(Boolean isCopilot) {
            this.isCopilot = Optional.ofNullable(isCopilot);
            return this;
        }

        @JsonSetter(value = "responseLength", nulls = Nulls.SKIP)
        public Builder responseLength(Optional<ResponseLength> responseLength) {
            this.responseLength = responseLength;
            return this;
        }

        public Builder responseLength(ResponseLength responseLength) {
            this.responseLength = Optional.ofNullable(responseLength);
            return this;
        }

        public ResponseConfigPrecondition build() {
            return new ResponseConfigPrecondition(
                    operator, useMarkdown, useForms, useImages, isCopilot, responseLength, additionalProperties);
        }
    }
}
