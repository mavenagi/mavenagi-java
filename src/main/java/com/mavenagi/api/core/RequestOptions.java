/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public final class RequestOptions {
    private final String organizationId;

    private final String agentId;

    private final Optional<Integer> timeout;

    private final TimeUnit timeoutTimeUnit;

    private RequestOptions(String organizationId, String agentId, Optional<Integer> timeout, TimeUnit timeoutTimeUnit) {
        this.organizationId = organizationId;
        this.agentId = agentId;
        this.timeout = timeout;
        this.timeoutTimeUnit = timeoutTimeUnit;
    }

    public Optional<Integer> getTimeout() {
        return timeout;
    }

    public TimeUnit getTimeoutTimeUnit() {
        return timeoutTimeUnit;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        if (this.organizationId != null) {
            headers.put("X-Organization-Id", this.organizationId);
        }
        if (this.agentId != null) {
            headers.put("X-Agent-Id", this.agentId);
        }
        return headers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String organizationId = null;

        private String agentId = null;

        private Optional<Integer> timeout = Optional.empty();

        private TimeUnit timeoutTimeUnit = TimeUnit.SECONDS;

        public Builder organizationId(String organizationId) {
            this.organizationId = organizationId;
            return this;
        }

        public Builder agentId(String agentId) {
            this.agentId = agentId;
            return this;
        }

        public Builder timeout(Integer timeout) {
            this.timeout = Optional.of(timeout);
            return this;
        }

        public Builder timeout(Integer timeout, TimeUnit timeoutTimeUnit) {
            this.timeout = Optional.of(timeout);
            this.timeoutTimeUnit = timeoutTimeUnit;
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(organizationId, agentId, timeout, timeoutTimeUnit);
        }
    }
}
