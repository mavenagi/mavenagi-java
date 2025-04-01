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
import com.mavenagi.api.resources.commons.types.IBasePaginatedRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ConversationsSearchRequest.Builder.class)
public final class ConversationsSearchRequest implements IBasePaginatedRequest {
    private final Optional<Integer> page;

    private final Optional<Integer> size;

    private final Optional<Boolean> sortDesc;

    private final Optional<ConversationField> sort;

    private final Optional<ConversationFilter> filter;

    private final Map<String, Object> additionalProperties;

    private ConversationsSearchRequest(
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<Boolean> sortDesc,
            Optional<ConversationField> sort,
            Optional<ConversationFilter> filter,
            Map<String, Object> additionalProperties) {
        this.page = page;
        this.size = size;
        this.sortDesc = sortDesc;
        this.sort = sort;
        this.filter = filter;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return Page number to return, defaults to 0
     */
    @JsonProperty("page")
    @java.lang.Override
    public Optional<Integer> getPage() {
        return page;
    }

    /**
     * @return The size of the page to return, defaults to 20
     */
    @JsonProperty("size")
    @java.lang.Override
    public Optional<Integer> getSize() {
        return size;
    }

    /**
     * @return Whether to sort descending, defaults to true
     */
    @JsonProperty("sortDesc")
    @java.lang.Override
    public Optional<Boolean> getSortDesc() {
        return sortDesc;
    }

    @JsonProperty("sort")
    public Optional<ConversationField> getSort() {
        return sort;
    }

    @JsonProperty("filter")
    public Optional<ConversationFilter> getFilter() {
        return filter;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ConversationsSearchRequest && equalTo((ConversationsSearchRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ConversationsSearchRequest other) {
        return page.equals(other.page)
                && size.equals(other.size)
                && sortDesc.equals(other.sortDesc)
                && sort.equals(other.sort)
                && filter.equals(other.filter);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.page, this.size, this.sortDesc, this.sort, this.filter);
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
        private Optional<Integer> page = Optional.empty();

        private Optional<Integer> size = Optional.empty();

        private Optional<Boolean> sortDesc = Optional.empty();

        private Optional<ConversationField> sort = Optional.empty();

        private Optional<ConversationFilter> filter = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(ConversationsSearchRequest other) {
            page(other.getPage());
            size(other.getSize());
            sortDesc(other.getSortDesc());
            sort(other.getSort());
            filter(other.getFilter());
            return this;
        }

        @JsonSetter(value = "page", nulls = Nulls.SKIP)
        public Builder page(Optional<Integer> page) {
            this.page = page;
            return this;
        }

        public Builder page(Integer page) {
            this.page = Optional.ofNullable(page);
            return this;
        }

        @JsonSetter(value = "size", nulls = Nulls.SKIP)
        public Builder size(Optional<Integer> size) {
            this.size = size;
            return this;
        }

        public Builder size(Integer size) {
            this.size = Optional.ofNullable(size);
            return this;
        }

        @JsonSetter(value = "sortDesc", nulls = Nulls.SKIP)
        public Builder sortDesc(Optional<Boolean> sortDesc) {
            this.sortDesc = sortDesc;
            return this;
        }

        public Builder sortDesc(Boolean sortDesc) {
            this.sortDesc = Optional.ofNullable(sortDesc);
            return this;
        }

        @JsonSetter(value = "sort", nulls = Nulls.SKIP)
        public Builder sort(Optional<ConversationField> sort) {
            this.sort = sort;
            return this;
        }

        public Builder sort(ConversationField sort) {
            this.sort = Optional.ofNullable(sort);
            return this;
        }

        @JsonSetter(value = "filter", nulls = Nulls.SKIP)
        public Builder filter(Optional<ConversationFilter> filter) {
            this.filter = filter;
            return this;
        }

        public Builder filter(ConversationFilter filter) {
            this.filter = Optional.ofNullable(filter);
            return this;
        }

        public ConversationsSearchRequest build() {
            return new ConversationsSearchRequest(page, size, sortDesc, sort, filter, additionalProperties);
        }
    }
}
