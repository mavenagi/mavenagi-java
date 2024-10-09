/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.actions.types;

import java.util.List;
import java.util.Optional;

public interface IActionBase {
    String getName();

    String getDescription();

    boolean getUserInteractionRequired();

    Optional<String> getButtonName();

    Optional<Precondition> getPrecondition();

    List<ActionParameter> getUserFormParameters();
}