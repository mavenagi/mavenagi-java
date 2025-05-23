/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.realtime.types;

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
@JsonDeserialize(builder = AudioSubscribeEvent.Builder.class)
public final class AudioSubscribeEvent {
    private final byte[] audioContent;

    private final Map<String, Object> additionalProperties;

    private AudioSubscribeEvent(byte[] audioContent, Map<String, Object> additionalProperties) {
        this.audioContent = audioContent;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("audioContent")
    public byte[] getAudioContent() {
        return audioContent;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof AudioSubscribeEvent && equalTo((AudioSubscribeEvent) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(AudioSubscribeEvent other) {
        return audioContent.equals(other.audioContent);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.audioContent);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static AudioContentStage builder() {
        return new Builder();
    }

    public interface AudioContentStage {
        _FinalStage audioContent(@NotNull byte[] audioContent);

        Builder from(AudioSubscribeEvent other);
    }

    public interface _FinalStage {
        AudioSubscribeEvent build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements AudioContentStage, _FinalStage {
        private byte[] audioContent;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(AudioSubscribeEvent other) {
            audioContent(other.getAudioContent());
            return this;
        }

        @java.lang.Override
        @JsonSetter("audioContent")
        public _FinalStage audioContent(@NotNull byte[] audioContent) {
            this.audioContent = Objects.requireNonNull(audioContent, "audioContent must not be null");
            return this;
        }

        @java.lang.Override
        public AudioSubscribeEvent build() {
            return new AudioSubscribeEvent(audioContent, additionalProperties);
        }
    }
}
