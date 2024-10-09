/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.knowledge.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum KnowledgeBaseType {
    API("API"),

    URL("URL"),

    RSS("RSS");

    private final String value;

    KnowledgeBaseType(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}