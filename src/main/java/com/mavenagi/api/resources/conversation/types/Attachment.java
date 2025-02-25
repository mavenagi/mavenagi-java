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
@JsonDeserialize(builder = Attachment.Builder.class)
public final class Attachment {
    private final String type;

    private final byte[] content;

    private final Map<String, Object> additionalProperties;

    private Attachment(String type, byte[] content, Map<String, Object> additionalProperties) {
        this.type = type;
        this.content = content;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The mime-type of the attachment. Supported types are {image/jpeg, image/jpg, image/png, image/gif, image/webp}.
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @return The attachment data, up to 5MB.
     */
    @JsonProperty("content")
    public byte[] getContent() {
        return content;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof Attachment && equalTo((Attachment) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(Attachment other) {
        return type.equals(other.type) && content.equals(other.content);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.type, this.content);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static TypeStage builder() {
        return new Builder();
    }

    public interface TypeStage {
        ContentStage type(@NotNull String type);

        Builder from(Attachment other);
    }

    public interface ContentStage {
        _FinalStage content(@NotNull byte[] content);
    }

    public interface _FinalStage {
        Attachment build();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements TypeStage, ContentStage, _FinalStage {
        private String type;

        private byte[] content;

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(Attachment other) {
            type(other.getType());
            content(other.getContent());
            return this;
        }

        /**
         * <p>The mime-type of the attachment. Supported types are {image/jpeg, image/jpg, image/png, image/gif, image/webp}.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("type")
        public ContentStage type(@NotNull String type) {
            this.type = Objects.requireNonNull(type, "type must not be null");
            return this;
        }

        /**
         * <p>The attachment data, up to 5MB.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("content")
        public _FinalStage content(@NotNull byte[] content) {
            this.content = Objects.requireNonNull(content, "content must not be null");
            return this;
        }

        @java.lang.Override
        public Attachment build() {
            return new Attachment(type, content, additionalProperties);
        }
    }
}
