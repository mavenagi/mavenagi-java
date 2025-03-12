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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ConversationMetadata.Builder.class)
public final class ConversationMetadata {
    private final Map<String, Map<String, String>> metadata;

    private final Map<String, Object> additionalProperties;

    private ConversationMetadata(Map<String, Map<String, String>> metadata, Map<String, Object> additionalProperties) {
        this.metadata = metadata;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return All metadata for the conversation. Keyed by appId.
     */
    @JsonProperty("metadata")
    public Map<String, Map<String, String>> getMetadata() {
        return metadata;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ConversationMetadata && equalTo((ConversationMetadata) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ConversationMetadata other) {
        return metadata.equals(other.metadata);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.metadata);
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
        private Map<String, Map<String, String>> metadata = new LinkedHashMap<>();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(ConversationMetadata other) {
            metadata(other.getMetadata());
            return this;
        }

        @JsonSetter(value = "metadata", nulls = Nulls.SKIP)
        public Builder metadata(Map<String, Map<String, String>> metadata) {
            this.metadata.clear();
            this.metadata.putAll(metadata);
            return this;
        }

        public Builder putAllMetadata(Map<String, Map<String, String>> metadata) {
            this.metadata.putAll(metadata);
            return this;
        }

        public Builder metadata(String key, Map<String, String> value) {
            this.metadata.put(key, value);
            return this;
        }

        public ConversationMetadata build() {
            return new ConversationMetadata(metadata, additionalProperties);
        }
    }
}
