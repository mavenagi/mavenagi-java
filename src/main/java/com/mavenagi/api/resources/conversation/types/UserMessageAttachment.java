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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = UserMessageAttachment.Builder.class)
public final class UserMessageAttachment {
    private final String url;

    private final Map<String, Object> additionalProperties;

    private UserMessageAttachment(String url, Map<String, Object> additionalProperties) {
        this.url = url;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The URL to access the attachment, The URL will be valid for 20 minutes.
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof UserMessageAttachment && equalTo((UserMessageAttachment) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(UserMessageAttachment other) {
        return url.equals(other.url);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.url);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static UrlStage builder() {
        return new Builder();
    }

    public interface UrlStage {
        _FinalStage url(@NotNull String url);

        Builder from(UserMessageAttachment other);
    }

    public interface _FinalStage {
        UserMessageAttachment build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements UrlStage, _FinalStage {
        private String url;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(UserMessageAttachment other) {
            url(other.getUrl());
            return this;
        }

        /**
         * <p>The URL to access the attachment, The URL will be valid for 20 minutes.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("url")
        public _FinalStage url(@NotNull String url) {
            this.url = Objects.requireNonNull(url, "url must not be null");
            return this;
        }

        @java.lang.Override
        public UserMessageAttachment build() {
            return new UserMessageAttachment(url, additionalProperties);
        }
    }
}
