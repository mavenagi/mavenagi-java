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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ConversationSummary.Builder.class)
public final class ConversationSummary {
    private final List<EntityIdWithoutAgent> actionIds;

    private final List<EntityIdWithoutAgent> incompleteActionIds;

    private final Map<String, Object> additionalProperties;

    private ConversationSummary(
            List<EntityIdWithoutAgent> actionIds,
            List<EntityIdWithoutAgent> incompleteActionIds,
            Map<String, Object> additionalProperties) {
        this.actionIds = actionIds;
        this.incompleteActionIds = incompleteActionIds;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The IDs of the actions that were taken by Maven in the conversation
     */
    @JsonProperty("actionIds")
    public List<EntityIdWithoutAgent> getActionIds() {
        return actionIds;
    }

    /**
     * @return The IDs of the actions that were taken by Maven but not completed in the conversation. Occurs when the user is shown an action form but does not submit it.
     */
    @JsonProperty("incompleteActionIds")
    public List<EntityIdWithoutAgent> getIncompleteActionIds() {
        return incompleteActionIds;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ConversationSummary && equalTo((ConversationSummary) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ConversationSummary other) {
        return actionIds.equals(other.actionIds) && incompleteActionIds.equals(other.incompleteActionIds);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.actionIds, this.incompleteActionIds);
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
        private List<EntityIdWithoutAgent> actionIds = new ArrayList<>();

        private List<EntityIdWithoutAgent> incompleteActionIds = new ArrayList<>();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(ConversationSummary other) {
            actionIds(other.getActionIds());
            incompleteActionIds(other.getIncompleteActionIds());
            return this;
        }

        @JsonSetter(value = "actionIds", nulls = Nulls.SKIP)
        public Builder actionIds(List<EntityIdWithoutAgent> actionIds) {
            this.actionIds.clear();
            this.actionIds.addAll(actionIds);
            return this;
        }

        public Builder addActionIds(EntityIdWithoutAgent actionIds) {
            this.actionIds.add(actionIds);
            return this;
        }

        public Builder addAllActionIds(List<EntityIdWithoutAgent> actionIds) {
            this.actionIds.addAll(actionIds);
            return this;
        }

        @JsonSetter(value = "incompleteActionIds", nulls = Nulls.SKIP)
        public Builder incompleteActionIds(List<EntityIdWithoutAgent> incompleteActionIds) {
            this.incompleteActionIds.clear();
            this.incompleteActionIds.addAll(incompleteActionIds);
            return this;
        }

        public Builder addIncompleteActionIds(EntityIdWithoutAgent incompleteActionIds) {
            this.incompleteActionIds.add(incompleteActionIds);
            return this;
        }

        public Builder addAllIncompleteActionIds(List<EntityIdWithoutAgent> incompleteActionIds) {
            this.incompleteActionIds.addAll(incompleteActionIds);
            return this;
        }

        public ConversationSummary build() {
            return new ConversationSummary(actionIds, incompleteActionIds, additionalProperties);
        }
    }
}
