/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.commons.types;

import java.time.OffsetDateTime;

public interface IInboxItemBase {
    EntityId getId();

    OffsetDateTime getCreatedAt();

    OffsetDateTime getUpdatedAt();

    InboxItemStatus getStatus();
}
