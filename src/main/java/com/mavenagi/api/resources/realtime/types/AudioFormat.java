/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.realtime.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AudioFormat {
    PCM_16("PCM16"),

    G_711_ULAW("G711_ULAW");

    private final String value;

    AudioFormat(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}
