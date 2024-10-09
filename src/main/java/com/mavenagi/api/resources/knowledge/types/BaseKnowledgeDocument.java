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
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = BaseKnowledgeDocument.Builder.class)
public final class BaseKnowledgeDocument implements IBaseKnowledgeDocument {
    private final String title;

    private final Optional<String> url;

    private final Optional<String> language;

    private final Optional<OffsetDateTime> createdAt;

    private final Optional<OffsetDateTime> updatedAt;

    private final Optional<String> author;

    private final Map<String, Object> additionalProperties;

    private BaseKnowledgeDocument(
            String title,
            Optional<String> url,
            Optional<String> language,
            Optional<OffsetDateTime> createdAt,
            Optional<OffsetDateTime> updatedAt,
            Optional<String> author,
            Map<String, Object> additionalProperties) {
        this.title = title;
        this.url = url;
        this.language = language;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The title of the document. Will be shown as part of answers.
     */
    @JsonProperty("title")
    @java.lang.Override
    public String getTitle() {
        return title;
    }

    /**
     * @return The URL of the document. Should be visible to end users. Will be shown as part of answers. Not used for crawling.
     */
    @JsonProperty("url")
    @java.lang.Override
    public Optional<String> getUrl() {
        return url;
    }

    /**
     * @return The document language. Must be a valid ISO 639-1 language code.
     */
    @JsonProperty("language")
    @java.lang.Override
    public Optional<String> getLanguage() {
        return language;
    }

    /**
     * @return The time at which this document was created.
     */
    @JsonProperty("createdAt")
    @java.lang.Override
    public Optional<OffsetDateTime> getCreatedAt() {
        return createdAt;
    }

    /**
     * @return The time at which this document was last modified.
     */
    @JsonProperty("updatedAt")
    @java.lang.Override
    public Optional<OffsetDateTime> getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return The name of the author who created this document.
     */
    @JsonProperty("author")
    @java.lang.Override
    public Optional<String> getAuthor() {
        return author;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof BaseKnowledgeDocument && equalTo((BaseKnowledgeDocument) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(BaseKnowledgeDocument other) {
        return title.equals(other.title)
                && url.equals(other.url)
                && language.equals(other.language)
                && createdAt.equals(other.createdAt)
                && updatedAt.equals(other.updatedAt)
                && author.equals(other.author);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(this.title, this.url, this.language, this.createdAt, this.updatedAt, this.author);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static TitleStage builder() {
        return new Builder();
    }

    public interface TitleStage {
        _FinalStage title(@NotNull String title);

        Builder from(BaseKnowledgeDocument other);
    }

    public interface _FinalStage {
        BaseKnowledgeDocument build();

        _FinalStage url(Optional<String> url);

        _FinalStage url(String url);

        _FinalStage language(Optional<String> language);

        _FinalStage language(String language);

        _FinalStage createdAt(Optional<OffsetDateTime> createdAt);

        _FinalStage createdAt(OffsetDateTime createdAt);

        _FinalStage updatedAt(Optional<OffsetDateTime> updatedAt);

        _FinalStage updatedAt(OffsetDateTime updatedAt);

        _FinalStage author(Optional<String> author);

        _FinalStage author(String author);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder implements TitleStage, _FinalStage {
        private String title;

        private Optional<String> author = Optional.empty();

        private Optional<OffsetDateTime> updatedAt = Optional.empty();

        private Optional<OffsetDateTime> createdAt = Optional.empty();

        private Optional<String> language = Optional.empty();

        private Optional<String> url = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(BaseKnowledgeDocument other) {
            title(other.getTitle());
            url(other.getUrl());
            language(other.getLanguage());
            createdAt(other.getCreatedAt());
            updatedAt(other.getUpdatedAt());
            author(other.getAuthor());
            return this;
        }

        /**
         * <p>The title of the document. Will be shown as part of answers.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("title")
        public _FinalStage title(@NotNull String title) {
            this.title = Objects.requireNonNull(title, "title must not be null");
            return this;
        }

        /**
         * <p>The name of the author who created this document.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage author(String author) {
            this.author = Optional.ofNullable(author);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "author", nulls = Nulls.SKIP)
        public _FinalStage author(Optional<String> author) {
            this.author = author;
            return this;
        }

        /**
         * <p>The time at which this document was last modified.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage updatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = Optional.ofNullable(updatedAt);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "updatedAt", nulls = Nulls.SKIP)
        public _FinalStage updatedAt(Optional<OffsetDateTime> updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * <p>The time at which this document was created.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage createdAt(OffsetDateTime createdAt) {
            this.createdAt = Optional.ofNullable(createdAt);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "createdAt", nulls = Nulls.SKIP)
        public _FinalStage createdAt(Optional<OffsetDateTime> createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * <p>The document language. Must be a valid ISO 639-1 language code.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage language(String language) {
            this.language = Optional.ofNullable(language);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "language", nulls = Nulls.SKIP)
        public _FinalStage language(Optional<String> language) {
            this.language = language;
            return this;
        }

        /**
         * <p>The URL of the document. Should be visible to end users. Will be shown as part of answers. Not used for crawling.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage url(String url) {
            this.url = Optional.ofNullable(url);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "url", nulls = Nulls.SKIP)
        public _FinalStage url(Optional<String> url) {
            this.url = url;
            return this;
        }

        @java.lang.Override
        public BaseKnowledgeDocument build() {
            return new BaseKnowledgeDocument(title, url, language, createdAt, updatedAt, author, additionalProperties);
        }
    }
}
