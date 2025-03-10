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
import com.mavenagi.api.resources.commons.types.EntityIdBase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = GenerateMavenSuggestionsRequest.Builder.class)
public final class GenerateMavenSuggestionsRequest {
    private final List<EntityIdBase> conversationMessageIds;

    private final Map<String, Object> additionalProperties;

    private GenerateMavenSuggestionsRequest(
            List<EntityIdBase> conversationMessageIds, Map<String, Object> additionalProperties) {
        this.conversationMessageIds = conversationMessageIds;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The message ids to generate a suggested response for. One suggestion will be generated for each message id.
     */
    @JsonProperty("conversationMessageIds")
    public List<EntityIdBase> getConversationMessageIds() {
        return conversationMessageIds;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof GenerateMavenSuggestionsRequest && equalTo((GenerateMavenSuggestionsRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(GenerateMavenSuggestionsRequest other) {
        return conversationMessageIds.equals(other.conversationMessageIds);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.conversationMessageIds);
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
        private List<EntityIdBase> conversationMessageIds = new ArrayList<>();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(GenerateMavenSuggestionsRequest other) {
            conversationMessageIds(other.getConversationMessageIds());
            return this;
        }

        @JsonSetter(value = "conversationMessageIds", nulls = Nulls.SKIP)
        public Builder conversationMessageIds(List<EntityIdBase> conversationMessageIds) {
            this.conversationMessageIds.clear();
            this.conversationMessageIds.addAll(conversationMessageIds);
            return this;
        }

        public Builder addConversationMessageIds(EntityIdBase conversationMessageIds) {
            this.conversationMessageIds.add(conversationMessageIds);
            return this;
        }

        public Builder addAllConversationMessageIds(List<EntityIdBase> conversationMessageIds) {
            this.conversationMessageIds.addAll(conversationMessageIds);
            return this;
        }

        public GenerateMavenSuggestionsRequest build() {
            return new GenerateMavenSuggestionsRequest(conversationMessageIds, additionalProperties);
        }
    }
}
