/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.users.requests;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = UserGetRequest.Builder.class)
public final class UserGetRequest {
    private final Optional<String> appId;

    private final Map<String, Object> additionalProperties;

    private UserGetRequest(Optional<String> appId, Map<String, Object> additionalProperties) {
        this.appId = appId;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The App ID of the user to get. If not provided the ID of the calling app will be used.
     */
    @JsonProperty("appId")
    public Optional<String> getAppId() {
        return appId;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof UserGetRequest && equalTo((UserGetRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(UserGetRequest other) {
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

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Optional<String> appId = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        public Builder from(UserGetRequest other) {
            appId(other.getAppId());
            return this;
        }

        @JsonSetter(value = "appId", nulls = Nulls.SKIP)
        public Builder appId(Optional<String> appId) {
            this.appId = appId;
            return this;
        }

        public Builder appId(String appId) {
            this.appId = Optional.ofNullable(appId);
            return this;
        }

        public UserGetRequest build() {
            return new UserGetRequest(appId, additionalProperties);
        }
    }
}
