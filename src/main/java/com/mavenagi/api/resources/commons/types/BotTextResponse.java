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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = BotTextResponse.Builder.class)
public final class BotTextResponse {
    private final String text;

    private final Map<String, Object> additionalProperties;

    private BotTextResponse(String text, Map<String, Object> additionalProperties) {
        this.text = text;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BotTextResponse && equalTo((BotTextResponse) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(BotTextResponse other) {
        return text.equals(other.text);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.text);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static TextStage builder() {
        return new Builder();
    }

    public interface TextStage {
        _FinalStage text(@NotNull String text);

        Builder from(BotTextResponse other);
    }

    public interface _FinalStage {
        BotTextResponse build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements TextStage, _FinalStage {
        private String text;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(BotTextResponse other) {
            text(other.getText());
            return this;
        }

        @java.lang.Override
        @JsonSetter("text")
        public _FinalStage text(@NotNull String text) {
            this.text = Objects.requireNonNull(text, "text must not be null");
            return this;
        }

        @java.lang.Override
        public BotTextResponse build() {
            return new BotTextResponse(text, additionalProperties);
        }
    }
}
