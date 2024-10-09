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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = CategorizationResponse.Builder.class)
public final class CategorizationResponse {
    private final Optional<String> category;

    private final Map<String, Object> additionalProperties;

    private CategorizationResponse(Optional<String> category, Map<String, Object> additionalProperties) {
        this.category = category;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("category")
    public Optional<String> getCategory() {
        return category;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof CategorizationResponse && equalTo((CategorizationResponse) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(CategorizationResponse other) {
        return category.equals(other.category);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.category);
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
        private Optional<String> category = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(CategorizationResponse other) {
            category(other.getCategory());
            return this;
        }

        @JsonSetter(value = "category", nulls = Nulls.SKIP)
        public Builder category(Optional<String> category) {
            this.category = category;
            return this;
        }

        public Builder category(String category) {
            this.category = Optional.ofNullable(category);
            return this;
        }

        public CategorizationResponse build() {
            return new CategorizationResponse(category, additionalProperties);
        }
    }
}