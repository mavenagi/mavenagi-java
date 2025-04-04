/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.core;

/**
 * This class serves as the base exception for all errors in the SDK.
 */
public class MavenAGIException extends RuntimeException {
    public MavenAGIException(String message) {
        super(message);
    }

    public MavenAGIException(String message, Exception e) {
        super(message, e);
    }
}
