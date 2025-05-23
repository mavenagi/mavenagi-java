/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.analytics.types;

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
@JsonDeserialize(builder = TableResponseBase.Builder.class)
public final class TableResponseBase implements ITableResponseBase {
    private final List<String> headers;

    private final Map<String, Object> additionalProperties;

    private TableResponseBase(List<String> headers, Map<String, Object> additionalProperties) {
        this.headers = headers;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Column headers in the table, aligning with the column definitions specified in the request.
     */
    @JsonProperty("headers")
    @java.lang.Override
    public List<String> getHeaders() {
        return headers;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof TableResponseBase && equalTo((TableResponseBase) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(TableResponseBase other) {
        return headers.equals(other.headers);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.headers);
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
        private List<String> headers = new ArrayList<>();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(TableResponseBase other) {
            headers(other.getHeaders());
            return this;
        }

        @JsonSetter(value = "headers", nulls = Nulls.SKIP)
        public Builder headers(List<String> headers) {
            this.headers.clear();
            this.headers.addAll(headers);
            return this;
        }

        public Builder addHeaders(String headers) {
            this.headers.add(headers);
            return this;
        }

        public Builder addAllHeaders(List<String> headers) {
            this.headers.addAll(headers);
            return this;
        }

        public TableResponseBase build() {
            return new TableResponseBase(headers, additionalProperties);
        }
    }
}
