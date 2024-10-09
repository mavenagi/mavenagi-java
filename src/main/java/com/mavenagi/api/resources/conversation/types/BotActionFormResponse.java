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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = BotActionFormResponse.Builder.class)
public final class BotActionFormResponse implements IBotActionFormResponse {
    private final String id;

    private final String formLabel;

    private final List<ActionFormField> fields;

    private final String submitLabel;

    private final Map<String, Object> additionalProperties;

    private BotActionFormResponse(
            String id,
            String formLabel,
            List<ActionFormField> fields,
            String submitLabel,
            Map<String, Object> additionalProperties) {
        this.id = id;
        this.formLabel = formLabel;
        this.fields = fields;
        this.submitLabel = submitLabel;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("id")
    @java.lang.Override
    public String getId() {
        return id;
    }

    @JsonProperty("formLabel")
    @java.lang.Override
    public String getFormLabel() {
        return formLabel;
    }

    @JsonProperty("fields")
    @java.lang.Override
    public List<ActionFormField> getFields() {
        return fields;
    }

    @JsonProperty("submitLabel")
    @java.lang.Override
    public String getSubmitLabel() {
        return submitLabel;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BotActionFormResponse && equalTo((BotActionFormResponse) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(BotActionFormResponse other) {
        return id.equals(other.id)
                && formLabel.equals(other.formLabel)
                && fields.equals(other.fields)
                && submitLabel.equals(other.submitLabel);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.id, this.formLabel, this.fields, this.submitLabel);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static IdStage builder() {
        return new Builder();
    }

    public interface IdStage {
        FormLabelStage id(@NotNull String id);

        Builder from(BotActionFormResponse other);
    }

    public interface FormLabelStage {
        SubmitLabelStage formLabel(@NotNull String formLabel);
    }

    public interface SubmitLabelStage {
        _FinalStage submitLabel(@NotNull String submitLabel);
    }

    public interface _FinalStage {
        BotActionFormResponse build();

        _FinalStage fields(List<ActionFormField> fields);

        _FinalStage addFields(ActionFormField fields);

        _FinalStage addAllFields(List<ActionFormField> fields);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements IdStage, FormLabelStage, SubmitLabelStage, _FinalStage {
        private String id;

        private String formLabel;

        private String submitLabel;

        private List<ActionFormField> fields = new ArrayList<>();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(BotActionFormResponse other) {
            id(other.getId());
            formLabel(other.getFormLabel());
            fields(other.getFields());
            submitLabel(other.getSubmitLabel());
            return this;
        }

        @java.lang.Override
        @JsonSetter("id")
        public FormLabelStage id(@NotNull String id) {
            this.id = Objects.requireNonNull(id, "id must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("formLabel")
        public SubmitLabelStage formLabel(@NotNull String formLabel) {
            this.formLabel = Objects.requireNonNull(formLabel, "formLabel must not be null");
            return this;
        }

        @java.lang.Override
        @JsonSetter("submitLabel")
        public _FinalStage submitLabel(@NotNull String submitLabel) {
            this.submitLabel = Objects.requireNonNull(submitLabel, "submitLabel must not be null");
            return this;
        }

        @java.lang.Override
        public _FinalStage addAllFields(List<ActionFormField> fields) {
            this.fields.addAll(fields);
            return this;
        }

        @java.lang.Override
        public _FinalStage addFields(ActionFormField fields) {
            this.fields.add(fields);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "fields", nulls = Nulls.SKIP)
        public _FinalStage fields(List<ActionFormField> fields) {
            this.fields.clear();
            this.fields.addAll(fields);
            return this;
        }

        @java.lang.Override
        public BotActionFormResponse build() {
            return new BotActionFormResponse(id, formLabel, fields, submitLabel, additionalProperties);
        }
    }
}
