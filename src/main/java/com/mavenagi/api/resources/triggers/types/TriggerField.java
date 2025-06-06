/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.triggers.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TriggerField {
    CREATED_AT("CreatedAt"),

    ENABLED("Enabled");

    private final String value;

    TriggerField(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}
