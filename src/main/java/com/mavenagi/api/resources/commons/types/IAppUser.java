/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.commons.types;

import java.util.Map;
import java.util.Set;

public interface IAppUser {
    Set<AppUserIdentifier> getIdentifiers();

    Map<String, UserData> getData();
}
