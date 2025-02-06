/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.conversation.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ConversationField {
    CATEGORY("Category"),

    FIRST_RESPONSE_TIME("FirstResponseTime"),

    HANDLE_TIME("HandleTime"),

    HUMAN_AGENTS("HumanAgents"),

    APP("App"),

    SENTIMENT("Sentiment"),

    QUALITY_REASON("QualityReason"),

    RESOLUTION_STATUS("ResolutionStatus"),

    QUALITY("Quality"),

    USERS("Users"),

    RESPONSE_LENGTH("ResponseLength"),

    THUMBS_UP_COUNT("ThumbsUpCount"),

    THUMBS_DOWN_COUNT("ThumbsDownCount"),

    TAGS("Tags"),

    USER_MESSAGE_COUNT("UserMessageCount"),

    LANGUAGES("Languages"),

    ACTIONS("Actions"),

    INCOMPLETE_ACTIONS("IncompleteActions"),

    SOURCES("Sources"),

    CREATED_AT("CreatedAt");

    private final String value;

    ConversationField(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}
