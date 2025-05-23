/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.triggers.types;

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
@JsonDeserialize(builder = EventTriggersSearchRequest.Builder.class)
public final class EventTriggersSearchRequest implements IBasePaginatedRequest {
    private final Optional<Integer> page;

    private final Optional<Integer> size;

    private final Optional<Boolean> sortDesc;

    private final Optional<TriggerField> sort;

    private final Map<String, Object> additionalProperties;

    private EventTriggersSearchRequest(
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<Boolean> sortDesc,
            Optional<TriggerField> sort,
            Map<String, Object> additionalProperties) {
        this.page = page;
        this.size = size;
        this.sortDesc = sortDesc;
        this.sort = sort;
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

    /**
     * @return The field to sort by, defaults to created timestamp
     */
    @JsonProperty("sort")
    public Optional<TriggerField> getSort() {
        return sort;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof EventTriggersSearchRequest && equalTo((EventTriggersSearchRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(EventTriggersSearchRequest other) {
        return page.equals(other.page)
                && size.equals(other.size)
                && sortDesc.equals(other.sortDesc)
                && sort.equals(other.sort);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.page, this.size, this.sortDesc, this.sort);
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

        private Optional<TriggerField> sort = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(EventTriggersSearchRequest other) {
            page(other.getPage());
            size(other.getSize());
            sortDesc(other.getSortDesc());
            sort(other.getSort());
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
        public Builder sort(Optional<TriggerField> sort) {
            this.sort = sort;
            return this;
        }

        public Builder sort(TriggerField sort) {
            this.sort = Optional.ofNullable(sort);
            return this;
        }

        public EventTriggersSearchRequest build() {
            return new EventTriggersSearchRequest(page, size, sortDesc, sort, additionalProperties);
        }
    }
}
