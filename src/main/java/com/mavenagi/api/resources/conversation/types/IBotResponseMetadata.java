/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.conversation.types;

import java.util.List;
import java.util.Optional;

public interface IBotResponseMetadata {
    List<String> getFollowupQuestions();

    List<Source> getSources();

    Optional<String> getLanguage();
}
