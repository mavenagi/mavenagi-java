/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.knowledge.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import com.mavenagi.api.resources.commons.types.EntityId;
import com.mavenagi.api.resources.commons.types.Precondition;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = KnowledgeBaseResponse.Builder.class)
public final class KnowledgeBaseResponse implements IKnowledgeBaseProperties {
    private final String name;

    private final Optional<Precondition> precondition;

    private final EntityId knowledgeBaseId;

    private final Map<String, Object> additionalProperties;

    private KnowledgeBaseResponse(
            String name,
            Optional<Precondition> precondition,
            EntityId knowledgeBaseId,
            Map<String, Object> additionalProperties) {
        this.name = name;
        this.precondition = precondition;
        this.knowledgeBaseId = knowledgeBaseId;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The name of the knowledge base
     */
    @JsonProperty("name")
    @java.lang.Override
    public String getName() {
        return name;
    }

    /**
     * @return (Beta) The preconditions that must be met for knowledge base be relevant to a conversation. Can be used to limit knowledge to certain types of users.
     */
    @JsonProperty("precondition")
    @java.lang.Override
    public Optional<Precondition> getPrecondition() {
        return precondition;
    }

    /**
     * @return ID that uniquely identifies this knowledge base
     */
    @JsonProperty("knowledgeBaseId")
    public EntityId getKnowledgeBaseId() {
        return knowledgeBaseId;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof KnowledgeBaseResponse && equalTo((KnowledgeBaseResponse) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(KnowledgeBaseResponse other) {
        return name.equals(other.name)
                && precondition.equals(other.precondition)
                && knowledgeBaseId.equals(other.knowledgeBaseId);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.name, this.precondition, this.knowledgeBaseId);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static NameStage builder() {
        return new Builder();
    }

    public interface NameStage {
        KnowledgeBaseIdStage name(@NotNull String name);

        Builder from(KnowledgeBaseResponse other);
    }

    public interface KnowledgeBaseIdStage {
        _FinalStage knowledgeBaseId(@NotNull EntityId knowledgeBaseId);
    }

    public interface _FinalStage {
        KnowledgeBaseResponse build();

        _FinalStage precondition(Optional<Precondition> precondition);

        _FinalStage precondition(Precondition precondition);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements NameStage, KnowledgeBaseIdStage, _FinalStage {
        private String name;

        private EntityId knowledgeBaseId;

        private Optional<Precondition> precondition = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(KnowledgeBaseResponse other) {
            name(other.getName());
            precondition(other.getPrecondition());
            knowledgeBaseId(other.getKnowledgeBaseId());
            return this;
        }

        /**
         * <p>The name of the knowledge base</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("name")
        public KnowledgeBaseIdStage name(@NotNull String name) {
            this.name = Objects.requireNonNull(name, "name must not be null");
            return this;
        }

        /**
         * <p>ID that uniquely identifies this knowledge base</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("knowledgeBaseId")
        public _FinalStage knowledgeBaseId(@NotNull EntityId knowledgeBaseId) {
            this.knowledgeBaseId = Objects.requireNonNull(knowledgeBaseId, "knowledgeBaseId must not be null");
            return this;
        }

        /**
         * <p>(Beta) The preconditions that must be met for knowledge base be relevant to a conversation. Can be used to limit knowledge to certain types of users.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage precondition(Precondition precondition) {
            this.precondition = Optional.ofNullable(precondition);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "precondition", nulls = Nulls.SKIP)
        public _FinalStage precondition(Optional<Precondition> precondition) {
            this.precondition = precondition;
            return this;
        }

        @java.lang.Override
        public KnowledgeBaseResponse build() {
            return new KnowledgeBaseResponse(name, precondition, knowledgeBaseId, additionalProperties);
        }
    }
}
