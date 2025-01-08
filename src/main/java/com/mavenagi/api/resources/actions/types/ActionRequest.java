/**
 * This file was auto-generated by Fern from our API Definition.
 */
package com.mavenagi.api.resources.actions.types;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mavenagi.api.core.ObjectMappers;
import com.mavenagi.api.resources.commons.types.ActionParameter;
import com.mavenagi.api.resources.commons.types.EntityIdBase;
import com.mavenagi.api.resources.commons.types.IActionBase;
import com.mavenagi.api.resources.commons.types.Precondition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ActionRequest.Builder.class)
public final class ActionRequest implements IActionBase {
    private final String name;

    private final String description;

    private final boolean userInteractionRequired;

    private final Optional<String> buttonName;

    private final Optional<Precondition> precondition;

    private final List<ActionParameter> userFormParameters;

    private final EntityIdBase actionId;

    private final Map<String, Object> additionalProperties;

    private ActionRequest(
            String name,
            String description,
            boolean userInteractionRequired,
            Optional<String> buttonName,
            Optional<Precondition> precondition,
            List<ActionParameter> userFormParameters,
            EntityIdBase actionId,
            Map<String, Object> additionalProperties) {
        this.name = name;
        this.description = description;
        this.userInteractionRequired = userInteractionRequired;
        this.buttonName = buttonName;
        this.precondition = precondition;
        this.userFormParameters = userFormParameters;
        this.actionId = actionId;
        this.additionalProperties = additionalProperties;
    }

    /**
     * @return The name of the action. This is displayed to the end user as part of forms when user interaction is required. It is also used to help Maven decide if the action is relevant to a conversation.
     */
    @JsonProperty("name")
    @java.lang.Override
    public String getName() {
        return name;
    }

    /**
     * @return The description of the action. Must be less than 1024 characters. This helps Maven decide if the action is relevant to a conversation and is not displayed directly to the end user. Descriptions are used by the LLM.
     */
    @JsonProperty("description")
    @java.lang.Override
    public String getDescription() {
        return description;
    }

    /**
     * @return Whether the action requires user interaction to execute. If false, and all of the required action parameters are known, the LLM may call the action automatically. If true, an conversations ask call will return a BotActionFormResponse which must be submitted by an API caller. API callers must display a button with the buttonName label to confirm the user's intent.
     */
    @JsonProperty("userInteractionRequired")
    @java.lang.Override
    public boolean getUserInteractionRequired() {
        return userInteractionRequired;
    }

    /**
     * @return When user interaction is required, the name of the button that is shown to the end user to confirm execution of the action
     */
    @JsonProperty("buttonName")
    @java.lang.Override
    public Optional<String> getButtonName() {
        return buttonName;
    }

    /**
     * @return The preconditions that must be met for an action to be relevant to a conversation. Can be used to restrict actions to certain types of users.
     */
    @JsonProperty("precondition")
    @java.lang.Override
    public Optional<Precondition> getPrecondition() {
        return precondition;
    }

    /**
     * @return The parameters that the action uses as input. An action will only be executed when all of the required parameters are provided. During execution, actions all have access to the full Conversation and User objects. Parameter values may be inferred from the user's conversation by the LLM.
     */
    @JsonProperty("userFormParameters")
    @java.lang.Override
    public List<ActionParameter> getUserFormParameters() {
        return userFormParameters;
    }

    /**
     * @return ID that uniquely identifies this action
     */
    @JsonProperty("actionId")
    public EntityIdBase getActionId() {
        return actionId;
    }

    @java.lang.Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof ActionRequest && equalTo((ActionRequest) other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    private boolean equalTo(ActionRequest other) {
        return name.equals(other.name)
                && description.equals(other.description)
                && userInteractionRequired == other.userInteractionRequired
                && buttonName.equals(other.buttonName)
                && precondition.equals(other.precondition)
                && userFormParameters.equals(other.userFormParameters)
                && actionId.equals(other.actionId);
    }

    @java.lang.Override
    public int hashCode() {
        return Objects.hash(
                this.name,
                this.description,
                this.userInteractionRequired,
                this.buttonName,
                this.precondition,
                this.userFormParameters,
                this.actionId);
    }

    @java.lang.Override
    public String toString() {
        return ObjectMappers.stringify(this);
    }

    public static NameStage builder() {
        return new Builder();
    }

    public interface NameStage {
        DescriptionStage name(@NotNull String name);

        Builder from(ActionRequest other);
    }

    public interface DescriptionStage {
        UserInteractionRequiredStage description(@NotNull String description);
    }

    public interface UserInteractionRequiredStage {
        ActionIdStage userInteractionRequired(boolean userInteractionRequired);
    }

    public interface ActionIdStage {
        _FinalStage actionId(@NotNull EntityIdBase actionId);
    }

    public interface _FinalStage {
        ActionRequest build();

        _FinalStage buttonName(Optional<String> buttonName);

        _FinalStage buttonName(String buttonName);

        _FinalStage precondition(Optional<Precondition> precondition);

        _FinalStage precondition(Precondition precondition);

        _FinalStage userFormParameters(List<ActionParameter> userFormParameters);

        _FinalStage addUserFormParameters(ActionParameter userFormParameters);

        _FinalStage addAllUserFormParameters(List<ActionParameter> userFormParameters);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder
            implements NameStage, DescriptionStage, UserInteractionRequiredStage, ActionIdStage, _FinalStage {
        private String name;

        private String description;

        private boolean userInteractionRequired;

        private EntityIdBase actionId;

        private List<ActionParameter> userFormParameters = new ArrayList<>();

        private Optional<Precondition> precondition = Optional.empty();

        private Optional<String> buttonName = Optional.empty();

        @JsonAnySetter
        private Map<String, Object> additionalProperties = new HashMap<>();

        private Builder() {}

        @java.lang.Override
        public Builder from(ActionRequest other) {
            name(other.getName());
            description(other.getDescription());
            userInteractionRequired(other.getUserInteractionRequired());
            buttonName(other.getButtonName());
            precondition(other.getPrecondition());
            userFormParameters(other.getUserFormParameters());
            actionId(other.getActionId());
            return this;
        }

        /**
         * <p>The name of the action. This is displayed to the end user as part of forms when user interaction is required. It is also used to help Maven decide if the action is relevant to a conversation.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("name")
        public DescriptionStage name(@NotNull String name) {
            this.name = Objects.requireNonNull(name, "name must not be null");
            return this;
        }

        /**
         * <p>The description of the action. Must be less than 1024 characters. This helps Maven decide if the action is relevant to a conversation and is not displayed directly to the end user. Descriptions are used by the LLM.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("description")
        public UserInteractionRequiredStage description(@NotNull String description) {
            this.description = Objects.requireNonNull(description, "description must not be null");
            return this;
        }

        /**
         * <p>Whether the action requires user interaction to execute. If false, and all of the required action parameters are known, the LLM may call the action automatically. If true, an conversations ask call will return a BotActionFormResponse which must be submitted by an API caller. API callers must display a button with the buttonName label to confirm the user's intent.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("userInteractionRequired")
        public ActionIdStage userInteractionRequired(boolean userInteractionRequired) {
            this.userInteractionRequired = userInteractionRequired;
            return this;
        }

        /**
         * <p>ID that uniquely identifies this action</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        @JsonSetter("actionId")
        public _FinalStage actionId(@NotNull EntityIdBase actionId) {
            this.actionId = Objects.requireNonNull(actionId, "actionId must not be null");
            return this;
        }

        /**
         * <p>The parameters that the action uses as input. An action will only be executed when all of the required parameters are provided. During execution, actions all have access to the full Conversation and User objects. Parameter values may be inferred from the user's conversation by the LLM.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage addAllUserFormParameters(List<ActionParameter> userFormParameters) {
            this.userFormParameters.addAll(userFormParameters);
            return this;
        }

        /**
         * <p>The parameters that the action uses as input. An action will only be executed when all of the required parameters are provided. During execution, actions all have access to the full Conversation and User objects. Parameter values may be inferred from the user's conversation by the LLM.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage addUserFormParameters(ActionParameter userFormParameters) {
            this.userFormParameters.add(userFormParameters);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "userFormParameters", nulls = Nulls.SKIP)
        public _FinalStage userFormParameters(List<ActionParameter> userFormParameters) {
            this.userFormParameters.clear();
            this.userFormParameters.addAll(userFormParameters);
            return this;
        }

        /**
         * <p>The preconditions that must be met for an action to be relevant to a conversation. Can be used to restrict actions to certain types of users.</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage precondition(Precondition precondition) {
            this.precondition = Optional.ofNullable(precondition);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "precondition", nulls = Nulls.SKIP)
        public _FinalStage precondition(Optional<Precondition> precondition) {
            this.precondition = precondition;
            return this;
        }

        /**
         * <p>When user interaction is required, the name of the button that is shown to the end user to confirm execution of the action</p>
         * @return Reference to {@code this} so that method calls can be chained together.
         */
        @java.lang.Override
        public _FinalStage buttonName(String buttonName) {
            this.buttonName = Optional.ofNullable(buttonName);
            return this;
        }

        @java.lang.Override
        @JsonSetter(value = "buttonName", nulls = Nulls.SKIP)
        public _FinalStage buttonName(Optional<String> buttonName) {
            this.buttonName = buttonName;
            return this;
        }

        @java.lang.Override
        public ActionRequest build() {
            return new ActionRequest(
                    name,
                    description,
                    userInteractionRequired,
                    buttonName,
                    precondition,
                    userFormParameters,
                    actionId,
                    additionalProperties);
        }
    }
}
