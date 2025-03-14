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
@JsonDeserialize(builder = InboxItemFixDeactivateKnowledgeBase.Builder.class)
public final class InboxItemFixDeactivateKnowledgeBase implements IInboxItemFixBase {
    private final EntityId id;

    private final KnowledgeBaseInformation knowledgeBase;

    private final Map<String, Object> additionalProperties;

    private InboxItemFixDeactivateKnowledgeBase(
            EntityId id, KnowledgeBaseInformation knowledgeBase, Map<String, Object> additionalProperties) {
        this.id = id;
        this.knowledgeBase = knowledgeBase;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Unique identifier for the inbox item fix.
     */
    @JsonProperty("id")
    @java.lang.Override
    public EntityId getId() {
        return id;
    }

    /**
     * @return The knowledge base associated with this fix.
     */
    @JsonProperty("knowledgeBase")
    public KnowledgeBaseInformation getKnowledgeBase() {
        return knowledgeBase;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof InboxItemFixDeactivateKnowledgeBase
                && equalTo((InboxItemFixDeactivateKnowledgeBase) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(InboxItemFixDeactivateKnowledgeBase other) {
        return id.equals(other.id) && knowledgeBase.equals(other.knowledgeBase);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.id, this.knowledgeBase);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static IdStage builder() {
        return new Builder();
    }

    public interface IdStage {
        KnowledgeBaseStage id(@NotNull EntityId id);

        Builder from(InboxItemFixDeactivateKnowledgeBase other);
    }

    public interface KnowledgeBaseStage {
        _FinalStage knowledgeBase(@NotNull KnowledgeBaseInformation knowledgeBase);
    }

    public interface _FinalStage {
        InboxItemFixDeactivateKnowledgeBase build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements IdStage, KnowledgeBaseStage, _FinalStage {
        private EntityId id;

        private KnowledgeBaseInformation knowledgeBase;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(InboxItemFixDeactivateKnowledgeBase other) {
            id(other.getId());
            knowledgeBase(other.getKnowledgeBase());
            return this;
        }

        /**
         * <p>Unique identifier for the inbox item fix.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("id")
        public KnowledgeBaseStage id(@NotNull EntityId id) {
            this.id = Objects.requireNonNull(id, "id must not be null");
            return this;
        }

        /**
         * <p>The knowledge base associated with this fix.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("knowledgeBase")
        public _FinalStage knowledgeBase(@NotNull KnowledgeBaseInformation knowledgeBase) {
            this.knowledgeBase = Objects.requireNonNull(knowledgeBase, "knowledgeBase must not be null");
            return this;
        }

        @java.lang.Override
        public InboxItemFixDeactivateKnowledgeBase build() {
            return new InboxItemFixDeactivateKnowledgeBase(id, knowledgeBase, additionalProperties);
        }
    }
}
