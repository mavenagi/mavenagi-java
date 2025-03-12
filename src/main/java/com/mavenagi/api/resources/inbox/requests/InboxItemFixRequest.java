/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.inbox.requests;

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
@JsonDeserialize(builder = InboxItemFixRequest.Builder.class)
public final class InboxItemFixRequest {
    private final String appId;

    private final Map<String, Object> additionalProperties;

    private InboxItemFixRequest(String appId, Map<String, Object> additionalProperties) {
        this.appId = appId;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The App ID of the inbox item fix to retrieve
     */
    @JsonProperty("appId")
    public String getAppId() {
        return appId;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof InboxItemFixRequest && equalTo((InboxItemFixRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(InboxItemFixRequest other) {
        return appId.equals(other.appId);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.appId);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static AppIdStage builder() {
        return new Builder();
    }

    public interface AppIdStage {
        _FinalStage appId(@NotNull String appId);

        Builder from(InboxItemFixRequest other);
    }

    public interface _FinalStage {
        InboxItemFixRequest build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements AppIdStage, _FinalStage {
        private String appId;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(InboxItemFixRequest other) {
            appId(other.getAppId());
            return this;
        }

        /**
         * <p>The App ID of the inbox item fix to retrieve</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("appId")
        public _FinalStage appId(@NotNull String appId) {
            this.appId = Objects.requireNonNull(appId, "appId must not be null");
            return this;
        }

        @java.lang.Override
        public InboxItemFixRequest build() {
            return new InboxItemFixRequest(appId, additionalProperties);
        }
    }
}
