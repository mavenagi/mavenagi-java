/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.commons.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActionParameterType {
    STRING("STRING"),

    BOOLEAN("BOOLEAN"),

    NUMBER("NUMBER"),

    SCHEMA("SCHEMA");

    private final String value;

    ActionParameterType(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}
