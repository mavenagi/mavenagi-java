/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.translations.types;

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
@JsonDeserialize(builder = TranslationRequest.Builder.class)
public final class TranslationRequest {
    private final String text;

    private final String targetLanguage;

    private final Map<String, Object> additionalProperties;

    private TranslationRequest(String text, String targetLanguage, Map<String, Object> additionalProperties) {
        this.text = text;
        this.targetLanguage = targetLanguage;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The text to translate
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * @return The target language to translate to, in ISO 639-1 code format.
     */
    @JsonProperty("targetLanguage")
    public String getTargetLanguage() {
        return targetLanguage;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof TranslationRequest && equalTo((TranslationRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(TranslationRequest other) {
        return text.equals(other.text) && targetLanguage.equals(other.targetLanguage);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.text, this.targetLanguage);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static TextStage builder() {
        return new Builder();
    }

    public interface TextStage {
        TargetLanguageStage text(@NotNull String text);

        Builder from(TranslationRequest other);
    }

    public interface TargetLanguageStage {
        _FinalStage targetLanguage(@NotNull String targetLanguage);
    }

    public interface _FinalStage {
        TranslationRequest build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements TextStage, TargetLanguageStage, _FinalStage {
        private String text;

        private String targetLanguage;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(TranslationRequest other) {
            text(other.getText());
            targetLanguage(other.getTargetLanguage());
            return this;
        }

        /**
         * <p>The text to translate</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("text")
        public TargetLanguageStage text(@NotNull String text) {
            this.text = Objects.requireNonNull(text, "text must not be null");
            return this;
        }

        /**
         * <p>The target language to translate to, in ISO 639-1 code format.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("targetLanguage")
        public _FinalStage targetLanguage(@NotNull String targetLanguage) {
            this.targetLanguage = Objects.requireNonNull(targetLanguage, "targetLanguage must not be null");
            return this;
        }

        @java.lang.Override
        public TranslationRequest build() {
            return new TranslationRequest(text, targetLanguage, additionalProperties);
        }
    }
}