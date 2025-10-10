# Reference
## Actions
<details><summary><code>client.actions.search(request) -> ActionsResponse</code></summary>
<dl>
<dd>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.actions().search(
    ActionsSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ActionsSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.actions.createOrUpdate(request) -> ActionResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update an action or create it if it doesn't exist
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.actions().createOrUpdate(
    ActionRequest
        .builder()
        .actionId(
            EntityIdBase
                .builder()
                .referenceId("get-balance")
                .build()
        )
        .name("Get the user's balance")
        .description("This action calls an API to get the user's current balance.")
        .userInteractionRequired(false)
        .userFormParameters(
            new ArrayList<ActionParameter>()
        )
        .precondition(
            Precondition.group(
                PreconditionGroup
                    .builder()
                    .operator(PreconditionGroupOperator.AND)
                    .preconditions(
                        Arrays.asList(
                            Precondition.user(
                                MetadataPrecondition
                                    .builder()
                                    .key("userKey")
                                    .build()
                            ),
                            Precondition.user(
                                MetadataPrecondition
                                    .builder()
                                    .key("userKey2")
                                    .build()
                            )
                        )
                    )
                    .build()
            )
        )
        .language("en")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ActionRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.actions.get(actionReferenceId) -> ActionResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an action by its supplied ID
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.actions().get(
    "get-balance",
    ActionGetRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**actionReferenceId:** `String` — The reference ID of the action to get. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the action to get. If not provided the ID of the calling app will be used.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.actions.patch(actionReferenceId, request) -> ActionResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update mutable action fields

The `appId` field can be provided to update an action owned by a different app. 
All other fields will overwrite the existing value on the action only if provided.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.actions().patch(
    "actionReferenceId",
    ActionPatchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**actionReferenceId:** `String` — The reference ID of the action to patch.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the action to patch. If not provided the ID of the calling app will be used.
    
</dd>
</dl>

<dl>
<dd>

**instructions:** `Optional<String>` — The instructions given to the LLM when determining whether to execute the action.
    
</dd>
</dl>

<dl>
<dd>

**llmInclusionStatus:** `Optional<LlmInclusionStatus>` — Determines whether the action is sent to the LLM as part of a conversation.
    
</dd>
</dl>

<dl>
<dd>

**segmentId:** `Optional<EntityId>` 

The ID of the segment that must be matched for the action to be relevant to a conversation. 
A null value will remove the segment from the action, it will be available on all conversations.

Segments are replacing inline preconditions - an action may not have both an inline precondition and a segment.
Inline precondition support will be removed in a future release.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.actions.delete(actionReferenceId)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Delete an action
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.actions().delete("get-balance");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**actionReferenceId:** `String` — The reference ID of the action to unregister. All other entity ID fields are inferred from the request.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## AgentCapabilities
<details><summary><code>client.agentCapabilities.list(request) -> ListAgentCapabilitiesResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

List all capabilities for an agent.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agentCapabilities().list(
    AgentCapabilityListRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `AgentCapabilityListRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agentCapabilities.get(integrationId, capabilityId) -> AgentCapability</code></summary>
<dl>
<dd>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agentCapabilities().get("integrationId", "capabilityId");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**integrationId:** `String` 
    
</dd>
</dl>

<dl>
<dd>

**capabilityId:** `String` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agentCapabilities.patch(integrationId, capabilityId, request) -> AgentCapability</code></summary>
<dl>
<dd>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agentCapabilities().patch(
    "integrationId",
    "capabilityId",
    PatchAgentCapabilityRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**integrationId:** `String` 
    
</dd>
</dl>

<dl>
<dd>

**capabilityId:** `String` 
    
</dd>
</dl>

<dl>
<dd>

**request:** `PatchAgentCapabilityRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agentCapabilities.execute(integrationId, capabilityId, request) -> ExecuteCapabilityResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Execute an action capability.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agentCapabilities().execute(
    "integrationId",
    "capabilityId",
    ExecuteCapabilityRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**integrationId:** `String` 
    
</dd>
</dl>

<dl>
<dd>

**capabilityId:** `String` 
    
</dd>
</dl>

<dl>
<dd>

**request:** `ExecuteCapabilityRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Agents
<details><summary><code>client.agents.search(request) -> AgentsSearchResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Search for agents across all organizations.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agents().search(
    AgentsSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `AgentsSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agents.list(organizationReferenceId) -> List&lt;Agent&gt;</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Lists all agents for an organization
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agents().list("organizationReferenceId");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The ID of the organization.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agents.create(organizationReferenceId, agentReferenceId, request) -> Agent</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Create a new agent

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agents().create(
    "organizationReferenceId",
    "agentReferenceId",
    CreateAgentRequest
        .builder()
        .name("name")
        .environment(AgentEnvironment.DEMO)
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The ID of the organization.
    
</dd>
</dl>

<dl>
<dd>

**agentReferenceId:** `String` — The ID of the agent.
    
</dd>
</dl>

<dl>
<dd>

**request:** `CreateAgentRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agents.get(organizationReferenceId, agentReferenceId) -> Agent</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an agent
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agents().get("organizationReferenceId", "agentReferenceId");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The ID of the organization.
    
</dd>
</dl>

<dl>
<dd>

**agentReferenceId:** `String` — The ID of the agent.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agents.patch(organizationReferenceId, agentReferenceId, request) -> Agent</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update mutable agent fields 
All fields will overwrite the existing value on the agent only if provided.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agents().patch(
    "organizationReferenceId",
    "agentReferenceId",
    AgentPatchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The ID of the organization.
    
</dd>
</dl>

<dl>
<dd>

**agentReferenceId:** `String` — The ID of the agent.
    
</dd>
</dl>

<dl>
<dd>

**request:** `AgentPatchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.agents.delete(organizationReferenceId, agentReferenceId)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Delete an agent.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.agents().delete("organizationReferenceId", "agentReferenceId");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The ID of the organization.
    
</dd>
</dl>

<dl>
<dd>

**agentReferenceId:** `String` — The ID of the agent.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Analytics
<details><summary><code>client.analytics.getConversationTable(request) -> ConversationTableResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieves structured conversation data formatted as a table, allowing users to group, filter, and define specific metrics to display as columns.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.analytics().getConversationTable(
    ConversationTableRequest
        .builder()
        .fieldGroupings(
            Arrays.asList(
                ConversationGroupBy
                    .builder()
                    .field(ConversationField.CATEGORY)
                    .build()
            )
        )
        .columnDefinitions(
            Arrays.asList(
                ConversationColumnDefinition
                    .builder()
                    .header("count")
                    .metric(
                        ConversationMetric.count(
                            ConversationCount
                                .builder()
                                .build()
                        )
                    )
                    .build(),
                ConversationColumnDefinition
                    .builder()
                    .header("avg_first_response_time")
                    .metric(
                        ConversationMetric.average(
                            ConversationAverage
                                .builder()
                                .targetField(NumericConversationField.FIRST_RESPONSE_TIME)
                                .build()
                        )
                    )
                    .build(),
                ConversationColumnDefinition
                    .builder()
                    .header("percentile_handle_time")
                    .metric(
                        ConversationMetric.percentile(
                            ConversationPercentile
                                .builder()
                                .targetField(NumericConversationField.HANDLE_TIME)
                                .percentile(25.0)
                                .build()
                        )
                    )
                    .build()
            )
        )
        .conversationFilter(
            ConversationFilter
                .builder()
                .languages(
                    Optional.of(
                        Arrays.asList("en", "es")
                    )
                )
                .build()
        )
        .timeGrouping(TimeInterval.DAY)
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ConversationTableRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.analytics.getConversationChart(request) -> ChartResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Fetches conversation data visualized in a chart format. Supported chart types include pie chart, date histogram, and stacked bar charts.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.analytics().getConversationChart(
    ConversationChartRequest.pieChart(
        ConversationPieChartRequest
            .builder()
            .groupBy(
                ConversationGroupBy
                    .builder()
                    .field(ConversationField.CATEGORY)
                    .build()
            )
            .metric(
                ConversationMetric.count(
                    ConversationCount
                        .builder()
                        .build()
                )
            )
            .conversationFilter(
                ConversationFilter
                    .builder()
                    .languages(
                        Optional.of(
                            Arrays.asList("en", "es")
                        )
                    )
                    .build()
            )
            .build()
    )
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ConversationChartRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.analytics.getFeedbackTable(request) -> FeedbackTableResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieves structured feedback data formatted as a table, allowing users to group, filter,  and define specific metrics to display as columns.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.analytics().getFeedbackTable(
    FeedbackTableRequest
        .builder()
        .fieldGroupings(
            Arrays.asList(
                FeedbackGroupBy
                    .builder()
                    .field(FeedbackField.CREATED_BY)
                    .build()
            )
        )
        .columnDefinitions(
            Arrays.asList(
                FeedbackColumnDefinition
                    .builder()
                    .header("feedback_count")
                    .metric(
                        FeedbackMetric.count(
                            FeedbackCount
                                .builder()
                                .build()
                        )
                    )
                    .build()
            )
        )
        .feedbackFilter(
            FeedbackFilter
                .builder()
                .types(
                    Optional.of(
                        Arrays.asList(FeedbackType.THUMBS_UP, FeedbackType.INSERT)
                    )
                )
                .build()
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `FeedbackTableRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.analytics.getAgentUserTable(request) -> AgentUserTableResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieves structured agent user data formatted as a table, allowing users to group, filter,  and define specific metrics to display as columns.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.analytics().getAgentUserTable(
    AgentUserTableRequest
        .builder()
        .columnDefinitions(
            Arrays.asList(
                AgentUserColumnDefinition
                    .builder()
                    .header("user_count")
                    .metric(
                        AgentUserMetric.count(
                            AgentUserCount
                                .builder()
                                .build()
                        )
                    )
                    .build()
            )
        )
        .agentUserFilter(
            AgentUserFilter
                .builder()
                .search("john")
                .build()
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `AgentUserTableRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## AppSettings
<details><summary><code>client.appSettings.search() -> SearchAppSettingsResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Search for app settings which have the `$index` key set to the provided value.

You can set the `$index` key using the Update app settings API.

<Warning>This API currently requires an organization ID and agent ID for any agent which is installed on the app. This requirement will be removed in a future update.</Warning>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.appSettings().search(
    SearchAppSettingsRequest
        .builder()
        .index("index")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**index:** `String` — Will return all settings which have the `$index` key set to the provided value.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.appSettings.get() -> Map&lt;String, Object&gt;</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get app settings set during installation
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.appSettings().get();
```
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.appSettings.update(request) -> Map&lt;String, Object&gt;</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update app settings. Performs a merge of the provided settings with the existing app settings.

- If a new key is provided, it will be added to the app settings.
- If an existing key is provided, it will be updated.
- No keys will be removed.

Note that if an array value is provided it will fully replace an existing value as arrays cannot be merged.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.appSettings().update(
    new HashMap<String, Object>() {{
        put("string", new 
        HashMap<String, Object>() {{put("key", "value");
        }});
    }}
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `Map<String, Object>` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Assets
<details><summary><code>client.assets.initiateUpload(request) -> InitiateAssetUploadResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Initiate an upload. 
Returns a pre-signed URL for direct file upload and an asset ID for subsequent operations.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.assets().initiateUpload(
    InitiateAssetUploadRequest
        .builder()
        .type("type")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `InitiateAssetUploadRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.assets.commitUpload(assetReferenceId, request)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Commit an upload after successful file transfer.
Updates the asset status and makes it available for use.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.assets().commitUpload(
    "assetReferenceId",
    CommitAssetUploadRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**assetReferenceId:** `String` — The reference ID of the asset to commit (provided by the initiate call). All other entity ID fields are inferred from the API request.
    
</dd>
</dl>

<dl>
<dd>

**request:** `CommitAssetUploadRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Conversation
<details><summary><code>client.conversation.initialize(request) -> ConversationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Initialize a new conversation. 
Only required if the ask request wishes to supply conversation level data or when syncing to external systems.

Conversations can not be modified using this API. If the conversation already exists then the existing conversation will be returned.

After initialization,
- metadata can be changed using the `updateConversationMetadata` API.
- messages can be added to the conversation with the `appendNewMessages` or `ask` APIs.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().initialize(
    ConversationRequest
        .builder()
        .conversationId(
            EntityIdBase
                .builder()
                .referenceId("referenceId")
                .build()
        )
        .messages(
            Arrays.asList(
                ConversationMessageRequest
                    .builder()
                    .conversationMessageId(
                        EntityIdBase
                            .builder()
                            .referenceId("referenceId")
                            .build()
                    )
                    .userId(
                        EntityIdBase
                            .builder()
                            .referenceId("referenceId")
                            .build()
                    )
                    .text("text")
                    .userMessageType(UserConversationMessageType.USER)
                    .build(),
                ConversationMessageRequest
                    .builder()
                    .conversationMessageId(
                        EntityIdBase
                            .builder()
                            .referenceId("referenceId")
                            .build()
                    )
                    .userId(
                        EntityIdBase
                            .builder()
                            .referenceId("referenceId")
                            .build()
                    )
                    .text("text")
                    .userMessageType(UserConversationMessageType.USER)
                    .build()
            )
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ConversationRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.patch(conversationId, request) -> ConversationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update mutable conversation fields. 

The `appId` field can be provided to update a conversation owned by a different app. 
All other fields will overwrite the existing value on the conversation only if provided.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().patch(
    "conversation-0",
    ConversationPatchRequest
        .builder()
        .llmEnabled(true)
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of the conversation to patch
    
</dd>
</dl>

<dl>
<dd>

**request:** `ConversationPatchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.get(conversationId) -> ConversationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get a conversation
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().get(
    "conversationId",
    ConversationGetRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of the conversation to get
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the conversation to get. If not provided the ID of the calling app will be used.
    
</dd>
</dl>

<dl>
<dd>

**translationLanguage:** `Optional<String>` — The language to translate the conversation analysis into
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.delete(conversationId)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Wipes a conversation of all user data. 
The conversation ID will still exist and non-user specific data will still be retained. 
Attempts to modify or add messages to the conversation will throw an error. 

Simulation conversations will no longer be visible in search results nor metrics. 
Non-simulation conversations will remain visible - they can not be fully removed from the system.

<Warning>This is a destructive operation and cannot be undone. <br/><br/>
The exact fields cleared include: the conversation subject, userRequest, agentResponse. 
As well as the text response, followup questions, and backend LLM prompt of all messages.</Warning>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().delete(
    "conversation-0",
    ConversationDeleteRequest
        .builder()
        .reason("GDPR deletion request 1234.")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of the conversation to delete
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the conversation to delete. If not provided the ID of the calling app will be used.
    
</dd>
</dl>

<dl>
<dd>

**reason:** `String` — The reason for deleting the conversation. This message will replace all user messages in the conversation.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.appendNewMessages(conversationId, request) -> ConversationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Append messages to an existing conversation. The conversation must be initialized first. If a message with the same ID already exists, it will be ignored. Messages do not allow modification.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().appendNewMessages(
    "conversationId",
    Arrays.asList(
        ConversationMessageRequest
            .builder()
            .conversationMessageId(
                EntityIdBase
                    .builder()
                    .referenceId("referenceId")
                    .build()
            )
            .userId(
                EntityIdBase
                    .builder()
                    .referenceId("referenceId")
                    .build()
            )
            .text("text")
            .userMessageType(UserConversationMessageType.USER)
            .build(),
        ConversationMessageRequest
            .builder()
            .conversationMessageId(
                EntityIdBase
                    .builder()
                    .referenceId("referenceId")
                    .build()
            )
            .userId(
                EntityIdBase
                    .builder()
                    .referenceId("referenceId")
                    .build()
            )
            .text("text")
            .userMessageType(UserConversationMessageType.USER)
            .build()
    )
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of the conversation to append messages to
    
</dd>
</dl>

<dl>
<dd>

**request:** `List<ConversationMessageRequest>` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.ask(conversationId, request) -> ConversationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an answer from Maven for a given user question. If the user question or its answer already exists, 
they will be reused and will not be updated. Messages do not allow modification once generated. 

Concurrency Behavior:
- If another API call is made for the same user question while a response is mid-stream, partial answers may be returned.
- The second caller will receive a truncated or partial response depending on where the first stream is in its processing. The first caller's stream will remain unaffected and continue delivering the full response.

Known Limitation:
- The API does not currently expose metadata indicating whether a response or message is incomplete. This will be addressed in a future update.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().ask(
    "conversation-0",
    AskRequest
        .builder()
        .conversationMessageId(
            EntityIdBase
                .builder()
                .referenceId("message-0")
                .build()
        )
        .userId(
            EntityIdBase
                .builder()
                .referenceId("user-0")
                .build()
        )
        .text("How do I reset my password?")
        .attachments(
            Optional.of(
                Arrays.asList(
                    AttachmentRequest
                        .builder()
                        .type("image/png")
                        .content("iVBORw0KGgo...".getBytes())
                        .build()
                )
            )
        )
        .transientData(
            new HashMap<String, String>() {{
                put("userToken", "abcdef123");
                put("queryApiKey", "foobar456");
            }}
        )
        .timezone("America/New_York")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of a new or existing conversation to use as context for the question
    
</dd>
</dl>

<dl>
<dd>

**request:** `AskRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.askStream(conversationId, request) -> Optional&lt;StreamResponse&gt;</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an answer from Maven for a given user question with a streaming response. The response will be sent as a stream of events. 
The text portions of stream responses should be concatenated to form the full response text. 
Action and metadata events should overwrite past data and do not need concatenation.

If the user question or its answer already exists, they will be reused and will not be updated. 
Messages do not allow modification once generated.
        
Concurrency Behavior:
- If another API call is made for the same user question while a response is mid-stream, partial answers may be returned.
- The second caller will receive a truncated or partial response depending on where the first stream is in its processing. The first caller's stream will remain unaffected and continue delivering the full response.

Known Limitation:
- The API does not currently expose metadata indicating whether a response or message is incomplete. This will be addressed in a future update.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().askStream(
    "conversation-0",
    AskRequest
        .builder()
        .conversationMessageId(
            EntityIdBase
                .builder()
                .referenceId("message-0")
                .build()
        )
        .userId(
            EntityIdBase
                .builder()
                .referenceId("user-0")
                .build()
        )
        .text("How do I reset my password?")
        .attachments(
            Optional.of(
                Arrays.asList(
                    AttachmentRequest
                        .builder()
                        .type("image/png")
                        .content("iVBORw0KGgo...".getBytes())
                        .build()
                )
            )
        )
        .transientData(
            new HashMap<String, String>() {{
                put("userToken", "abcdef123");
                put("queryApiKey", "foobar456");
            }}
        )
        .timezone("America/New_York")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of a new or existing conversation to use as context for the question
    
</dd>
</dl>

<dl>
<dd>

**request:** `AskRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.askObjectStream(conversationId, request) -> Optional&lt;ObjectStreamResponse&gt;</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Generate a structured object response based on a provided schema and user prompt with a streaming response. 
The response will be sent as a stream of events containing text, start, and end events.
The text portions of stream responses should be concatenated to form the full response text.

If the user question and object response already exist, they will be reused and not updated.

Concurrency Behavior:
- If another API call is made for the same user question while a response is mid-stream, partial answers may be returned.
- The second caller will receive a truncated or partial response depending on where the first stream is in its processing. The first caller's stream will remain unaffected and continue delivering the full response.

Known Limitations:
- Schema enforcement is best-effort and may not guarantee exact conformity.
- The API does not currently expose metadata indicating whether a response or message is incomplete. This will be addressed in a future update.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().askObjectStream(
    "conversationId",
    AskObjectRequest
        .builder()
        .schema("schema")
        .conversationMessageId(
            EntityIdBase
                .builder()
                .referenceId("referenceId")
                .build()
        )
        .userId(
            EntityIdBase
                .builder()
                .referenceId("referenceId")
                .build()
        )
        .text("text")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of a new or existing conversation to use as context for the object generation request
    
</dd>
</dl>

<dl>
<dd>

**request:** `AskObjectRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.categorize(conversationId) -> CategorizationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Uses an LLM flow to categorize the conversation. Experimental.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().categorize("conversationId");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of the conversation to categorize
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.createFeedback(request) -> Feedback</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update feedback or create it if it doesn't exist
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().createFeedback(
    FeedbackRequest
        .builder()
        .feedbackId(
            EntityIdBase
                .builder()
                .referenceId("feedback-0")
                .build()
        )
        .conversationId(
            EntityIdBase
                .builder()
                .referenceId("conversation-0")
                .build()
        )
        .conversationMessageId(
            EntityIdBase
                .builder()
                .referenceId("message-1")
                .build()
        )
        .type(FeedbackType.THUMBS_UP)
        .userId(
            EntityIdBase
                .builder()
                .referenceId("user-0")
                .build()
        )
        .text("Great answer!")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `FeedbackRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.submitActionForm(conversationId, request) -> ConversationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Submit a filled out action form
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().submitActionForm(
    "conversationId",
    SubmitActionFormRequest
        .builder()
        .actionFormId("actionFormId")
        .parameters(
            new HashMap<String, ActionFormRequestParamValue>() {{
                put("parameters", ActionFormRequestParamValue.of(new 
                HashMap<String, Object>() {{put("key", "value");
                }}));
            }}
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of a conversation the form being submitted belongs to
    
</dd>
</dl>

<dl>
<dd>

**request:** `SubmitActionFormRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.addConversationMetadata(conversationId, request) -> Map&lt;String, String&gt;</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Replaced by `updateConversationMetadata`. 

Adds metadata to an existing conversation. If a metadata field already exists, it will be overwritten.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().addConversationMetadata(
    "conversationId",
    new HashMap<String, String>() {{
        put("string", "string");
    }}
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of a conversation the metadata being added belongs to
    
</dd>
</dl>

<dl>
<dd>

**request:** `Map<String, String>` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.updateConversationMetadata(conversationId, request) -> ConversationMetadata</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update metadata supplied by the calling application for an existing conversation. 
Does not modify metadata saved by other apps.

If a metadata field already exists for the calling app, it will be overwritten. 
If it does not exist, it will be added. Will not remove metadata fields.

Returns all metadata saved by any app on the conversation.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().updateConversationMetadata(
    "conversation-0",
    UpdateMetadataRequest
        .builder()
        .values(
            new HashMap<String, String>() {{
                put("key", "newValue");
            }}
        )
        .appId("conversation-owning-app")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**conversationId:** `String` — The ID of the conversation to modify metadata for
    
</dd>
</dl>

<dl>
<dd>

**request:** `UpdateMetadataRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.search(request) -> ConversationsResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Search conversations
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().search(
    ConversationsSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ConversationsSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.conversation.deliverMessage(request) -> DeliverMessageResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Deliver a message to a user or conversation.

<Warning>
Currently, messages can only be successfully delivered to conversations with the `ASYNC` capability that are `open`. 
User message delivery is not yet supported.
</Warning>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.conversation().deliverMessage(
    DeliverMessageRequest.user(
        DeliverUserMessageRequest
            .builder()
            .userId(
                EntityIdWithoutAgent
                    .builder()
                    .type(EntityType.AGENT)
                    .appId("appId")
                    .referenceId("referenceId")
                    .build()
            )
            .message(
                ConversationMessageRequest
                    .builder()
                    .conversationMessageId(
                        EntityIdBase
                            .builder()
                            .referenceId("referenceId")
                            .build()
                    )
                    .userId(
                        EntityIdBase
                            .builder()
                            .referenceId("referenceId")
                            .build()
                    )
                    .text("text")
                    .userMessageType(UserConversationMessageType.USER)
                    .build()
            )
            .build()
    )
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `DeliverMessageRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Events
<details><summary><code>client.events.create(request) -> EventResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Create a new event
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.events().create(
    EventRequest.userEvent(
        NovelUserEvent
            .builder()
            .id(
                EntityIdBase
                    .builder()
                    .referenceId("referenceId")
                    .build()
            )
            .eventName(UserEventName.BUTTON_CLICKED)
            .userInfo(
                EventUserInfoBase
                    .builder()
                    .id(
                        EntityIdBase
                            .builder()
                            .referenceId("referenceId")
                            .build()
                    )
                    .build()
            )
            .build()
    )
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `EventRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.events.search(request) -> EventsSearchResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Search events
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.events().search(
    EventsSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `EventsSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.events.get(eventId) -> EventResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieve details of a specific Event item by its ID.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.events().get(
    "eventId",
    EventGetRequest
        .builder()
        .appId("appId")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**eventId:** `String` — The ID of the Event to get.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `String` — The App ID of the Event to retrieve
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Inbox
<details><summary><code>client.inbox.search(request) -> InboxSearchResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieve a paginated list of inbox items for an agent.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.inbox().search(
    InboxSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `InboxSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.inbox.get(inboxItemId) -> InboxItem</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieve details of a specific inbox item by its ID.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.inbox().get(
    "inboxItemId",
    InboxItemRequest
        .builder()
        .appId("appId")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**inboxItemId:** `String` — The ID of the inbox item to get. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `String` — The App ID of the inbox item to retrieve
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.inbox.getFix(inboxItemFixId) -> InboxItemFix</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieve a suggested fix. Includes document information if the fix is a Missing Knowledge suggestion.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.inbox().getFix(
    "inboxItemFixId",
    InboxItemFixRequest
        .builder()
        .appId("appId")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**inboxItemFixId:** `String` — Unique identifier for the inbox fix.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `String` — The App ID of the inbox item fix to retrieve
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.inbox.applyFixes(inboxItemId, request)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Apply a list of fixes belonging to an inbox item.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.inbox().applyFixes(
    "inboxItemId",
    ApplyFixesRequest
        .builder()
        .appId("appId")
        .fixReferenceIds(
            Arrays.asList("fixReferenceIds", "fixReferenceIds")
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**inboxItemId:** `String` — Unique identifier for the inbox item.
    
</dd>
</dl>

<dl>
<dd>

**request:** `ApplyFixesRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.inbox.ignore(inboxItemId)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Ignore a specific inbox item by its ID.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.inbox().ignore(
    "inboxItemId",
    InboxItemIgnoreRequest
        .builder()
        .appId("appId")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**inboxItemId:** `String` — Unique identifier for the inbox item.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `String` — The App ID of the inbox item fix to ignore
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Knowledge
<details><summary><code>client.knowledge.searchKnowledgeBases(request) -> KnowledgeBasesResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Search knowledge bases
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().searchKnowledgeBases(
    KnowledgeBaseSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `KnowledgeBaseSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.createOrUpdateKnowledgeBase(request) -> KnowledgeBaseResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update a knowledge base or create it if it doesn't exist.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().createOrUpdateKnowledgeBase(
    KnowledgeBaseRequest
        .builder()
        .knowledgeBaseId(
            EntityIdBase
                .builder()
                .referenceId("help-center")
                .build()
        )
        .name("Help center")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `KnowledgeBaseRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.getKnowledgeBase(knowledgeBaseReferenceId) -> KnowledgeBaseResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an existing knowledge base by its supplied ID
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().getKnowledgeBase(
    "help-center",
    KnowledgeBaseGetRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseReferenceId:** `String` — The reference ID of the knowledge base to get. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the knowledge base to get. If not provided the ID of the calling app will be used.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.patchKnowledgeBase(knowledgeBaseReferenceId, request) -> KnowledgeBaseResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update mutable knowledge base fields

The `appId` field can be provided to update a knowledge base owned by a different app. 
All other fields will overwrite the existing value on the knowledge base only if provided.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().patchKnowledgeBase(
    "knowledgeBaseReferenceId",
    KnowledgeBasePatchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseReferenceId:** `String` — The reference ID of the knowledge base to patch.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the knowledge base to patch. If not provided the ID of the calling app will be used.
    
</dd>
</dl>

<dl>
<dd>

**name:** `Optional<String>` — The name of the knowledge base.
    
</dd>
</dl>

<dl>
<dd>

**tags:** `Optional<Set<String>>` — The tags of the knowledge base.
    
</dd>
</dl>

<dl>
<dd>

**llmInclusionStatus:** `Optional<LlmInclusionStatus>` — Determines whether documents in the knowledge base are sent to the LLM as part of a conversation. Note that at this time knowledge bases can not be set to `ALWAYS`.
    
</dd>
</dl>

<dl>
<dd>

**precondition:** `Optional<Precondition>` — The preconditions that must be met for a knowledge base to be relevant to a conversation. Can be used to restrict knowledge bases to certain types of users. A null value will remove the precondition from the knowledge base, it will be available on all conversations.
    
</dd>
</dl>

<dl>
<dd>

**segmentId:** `Optional<EntityId>` 

The ID of the segment that must be matched for the knowledge base to be relevant to a conversation. 
A null value will remove the segment from the knowledge base, it will be available on all conversations.

Segments are replacing inline preconditions - a knowledge base may not have both an inline precondition and a segment.
Inline precondition support will be removed in a future release.
    
</dd>
</dl>

<dl>
<dd>

**refreshFrequency:** `Optional<KnowledgeBaseRefreshFrequency>` — How often the knowledge base should be refreshed.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.createKnowledgeBaseVersion(knowledgeBaseReferenceId, request) -> KnowledgeBaseVersion</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Create a new knowledge base version.

If an existing version is in progress, then that version will be finalized in an error state.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().createKnowledgeBaseVersion(
    "help-center",
    KnowledgeBaseVersionRequest
        .builder()
        .type(KnowledgeBaseVersionType.FULL)
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseReferenceId:** `String` — The reference ID of the knowledge base to create a version for. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**request:** `KnowledgeBaseVersionRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.finalizeKnowledgeBaseVersion(knowledgeBaseReferenceId, request) -> KnowledgeBaseVersion</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Finalize the latest knowledge base version. Required to indicate the version is complete. Will throw an exception if the latest version is not in progress.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().finalizeKnowledgeBaseVersion(
    "help-center",
    FinalizeKnowledgeBaseVersionRequest
        .builder()
        .versionId(
            EntityIdWithoutAgent
                .builder()
                .type(EntityType.KNOWLEDGE_BASE_VERSION)
                .referenceId("versionId")
                .appId("maven")
                .build()
        )
        .status(KnowledgeBaseVersionFinalizeStatus.SUCCEEDED)
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseReferenceId:** `String` — The reference ID of the knowledge base to finalize a version for. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**request:** `FinalizeKnowledgeBaseVersionRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.listKnowledgeBaseVersions(knowledgeBaseReferenceId) -> KnowledgeBaseVersionsListResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

List all active versions for a knowledge base. Returns the most recent versions first.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().listKnowledgeBaseVersions(
    "knowledgeBaseReferenceId",
    KnowledgeBaseVersionsListRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseReferenceId:** `String` — The reference ID of the knowledge base to list versions for. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the knowledge base. If not provided the ID of the calling app will be used.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.searchKnowledgeDocuments(request) -> KnowledgeDocumentsResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Search knowledge documents
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().searchKnowledgeDocuments(
    KnowledgeDocumentSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `KnowledgeDocumentSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.createKnowledgeDocument(knowledgeBaseReferenceId, request) -> KnowledgeDocumentResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Create or update a knowledge document. Requires an existing knowledge base with an in progress version. 
Will throw an exception if the latest version is not in progress.
        
<Tip>
This API maintains document version history. If for the same reference ID none of the `title`, `text`, `sourceUrl`, `metadata` fields 
have changed, a new document version will not be created. The existing version will be reused.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().createKnowledgeDocument(
    "help-center",
    KnowledgeDocumentRequest
        .builder()
        .knowledgeDocumentId(
            EntityIdBase
                .builder()
                .referenceId("getting-started")
                .build()
        )
        .contentType(KnowledgeDocumentContentType.MARKDOWN)
        .content("## Getting started\\nThis is a getting started guide for the help center.")
        .title("Getting started")
        .versionId(
            EntityIdWithoutAgent
                .builder()
                .type(EntityType.KNOWLEDGE_BASE_VERSION)
                .referenceId("versionId")
                .appId("maven")
                .build()
        )
        .metadata(
            new HashMap<String, String>() {{
                put("category", "getting-started");
            }}
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseReferenceId:** `String` — The reference ID of the knowledge base to create a document for. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**request:** `KnowledgeDocumentRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.deleteKnowledgeDocument(knowledgeBaseReferenceId, knowledgeDocumentReferenceId, request)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Delete knowledge document from a specific version. 
Requires an existing knowledge base with an in progress version of type PARTIAL. Will throw an exception if the version is not in progress.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().deleteKnowledgeDocument(
    "help-center",
    "getting-started",
    KnowledgeDeleteRequest
        .builder()
        .versionId(
            EntityIdWithoutAgent
                .builder()
                .type(EntityType.KNOWLEDGE_BASE_VERSION)
                .appId("maven")
                .referenceId("versionId")
                .build()
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseReferenceId:** `String` — The reference ID of the knowledge base that contains the document to delete. All other entity ID fields are inferred from the request
    
</dd>
</dl>

<dl>
<dd>

**knowledgeDocumentReferenceId:** `String` — The reference ID of the knowledge document to delete. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**request:** `KnowledgeDeleteRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.knowledge.getKnowledgeDocument(knowledgeBaseVersionReferenceId, knowledgeDocumentReferenceId) -> KnowledgeDocumentResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get a knowledge document by its supplied version and document IDs. Response includes document content in markdown format.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.knowledge().getKnowledgeDocument(
    "knowledgeBaseVersionReferenceId",
    "knowledgeDocumentReferenceId",
    KnowledgeDocumentGetRequest
        .builder()
        .knowledgeBaseVersionAppId("knowledgeBaseVersionAppId")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**knowledgeBaseVersionReferenceId:** `String` — The reference ID of the knowledge base version that contains the document. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**knowledgeDocumentReferenceId:** `String` — The reference ID of the knowledge document to get. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**knowledgeBaseVersionAppId:** `String` — The App ID of the knowledge base version.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Organizations
<details><summary><code>client.organizations.create(organizationReferenceId, request) -> Organization</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Create a new organization.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.organizations().create(
    "organizationReferenceId",
    CreateOrganizationRequest
        .builder()
        .name("name")
        .defaultLanguage("defaultLanguage")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The reference ID of the organization.
    
</dd>
</dl>

<dl>
<dd>

**request:** `CreateOrganizationRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.organizations.get(organizationReferenceId) -> Organization</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an organization by ID
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.organizations().get("organizationReferenceId");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The reference ID of the organization.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.organizations.patch(organizationReferenceId, request) -> Organization</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update mutable organization fields.
All fields will overwrite the existing value on the organization only if provided.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.organizations().patch(
    "organizationReferenceId",
    OrganizationPatchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The reference ID of the organization.
    
</dd>
</dl>

<dl>
<dd>

**request:** `OrganizationPatchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.organizations.delete(organizationReferenceId)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Delete an organization.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.organizations().delete("organizationReferenceId");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**organizationReferenceId:** `String` — The reference ID of the organization.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.organizations.getConversationTable(request) -> ConversationTableResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Retrieves structured conversation data across all organizations, formatted as a table, 
allowing users to group, filter, and define specific metrics to display as columns.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.organizations().getConversationTable(
    ConversationTableRequest
        .builder()
        .fieldGroupings(
            Arrays.asList(
                ConversationGroupBy
                    .builder()
                    .field(ConversationField.CATEGORY)
                    .build()
            )
        )
        .columnDefinitions(
            Arrays.asList(
                ConversationColumnDefinition
                    .builder()
                    .header("count")
                    .metric(
                        ConversationMetric.count(
                            ConversationCount
                                .builder()
                                .build()
                        )
                    )
                    .build(),
                ConversationColumnDefinition
                    .builder()
                    .header("avg_first_response_time")
                    .metric(
                        ConversationMetric.average(
                            ConversationAverage
                                .builder()
                                .targetField(NumericConversationField.FIRST_RESPONSE_TIME)
                                .build()
                        )
                    )
                    .build(),
                ConversationColumnDefinition
                    .builder()
                    .header("percentile_handle_time")
                    .metric(
                        ConversationMetric.percentile(
                            ConversationPercentile
                                .builder()
                                .targetField(NumericConversationField.HANDLE_TIME)
                                .percentile(25.0)
                                .build()
                        )
                    )
                    .build()
            )
        )
        .conversationFilter(
            ConversationFilter
                .builder()
                .languages(
                    Optional.of(
                        Arrays.asList("en", "es")
                    )
                )
                .build()
        )
        .timeGrouping(TimeInterval.DAY)
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ConversationTableRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.organizations.getConversationChart(request) -> ChartResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Fetches conversation data across all organizations, visualized in a chart format. 
Supported chart types include pie chart, date histogram, and stacked bar charts.

<Tip>
This endpoint requires additional permissions. Contact support to request access.
</Tip>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.organizations().getConversationChart(
    ConversationChartRequest.pieChart(
        ConversationPieChartRequest
            .builder()
            .groupBy(
                ConversationGroupBy
                    .builder()
                    .field(ConversationField.CATEGORY)
                    .build()
            )
            .metric(
                ConversationMetric.count(
                    ConversationCount
                        .builder()
                        .build()
                )
            )
            .conversationFilter(
                ConversationFilter
                    .builder()
                    .languages(
                        Optional.of(
                            Arrays.asList("en", "es")
                        )
                    )
                    .build()
            )
            .build()
    )
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `ConversationChartRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Segments
<details><summary><code>client.segments.search(request) -> SegmentsSearchResponse</code></summary>
<dl>
<dd>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.segments().search(
    SegmentsSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `SegmentsSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.segments.createOrUpdate(request) -> SegmentResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update a segment or create it if it doesn't exist.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.segments().createOrUpdate(
    SegmentRequest
        .builder()
        .segmentId(
            EntityIdBase
                .builder()
                .referenceId("admin-users")
                .build()
        )
        .name("Admin users")
        .precondition(
            Precondition.group(
                PreconditionGroup
                    .builder()
                    .operator(PreconditionGroupOperator.AND)
                    .preconditions(
                        Arrays.asList(
                            Precondition.user(
                                MetadataPrecondition
                                    .builder()
                                    .key("userKey")
                                    .build()
                            ),
                            Precondition.user(
                                MetadataPrecondition
                                    .builder()
                                    .key("userKey2")
                                    .build()
                            )
                        )
                    )
                    .build()
            )
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `SegmentRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.segments.get(segmentReferenceId) -> SegmentResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get a segment by its supplied ID
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.segments().get(
    "admin-users",
    SegmentGetRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**segmentReferenceId:** `String` — The reference ID of the segment to get. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the segment to get. If not provided, the ID of the calling app will be used.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.segments.patch(segmentReferenceId, request) -> SegmentResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update mutable segment fields

The `appId` field can be provided to update a segment owned by a different app. 
All other fields will overwrite the existing value on the segment only if provided.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.segments().patch(
    "segmentReferenceId",
    SegmentPatchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**segmentReferenceId:** `String` — The reference ID of the segment to update. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**request:** `SegmentPatchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Translations
<details><summary><code>client.translations.translate(request) -> TranslationResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Translate text from one language to another
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.translations().translate(
    TranslationRequest
        .builder()
        .text("Hello world")
        .targetLanguage("es")
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `TranslationRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Triggers
<details><summary><code>client.triggers.search(request) -> EventTriggersSearchResponse</code></summary>
<dl>
<dd>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.triggers().search(
    EventTriggersSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `EventTriggersSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.triggers.createOrUpdate(request) -> EventTriggerResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update an event trigger or create it if it doesn't exist.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.triggers().createOrUpdate(
    EventTriggerRequest
        .builder()
        .triggerId(
            EntityIdBase
                .builder()
                .referenceId("store-in-snowflake")
                .build()
        )
        .description("Stores conversation data in Snowflake")
        .type(EventTriggerType.CONVERSATION_CREATED)
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `EventTriggerRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.triggers.get(triggerReferenceId) -> EventTriggerResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an event trigger by its supplied ID
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.triggers().get("store-in-snowflake");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**triggerReferenceId:** `String` — The reference ID of the event trigger to get. All other entity ID fields are inferred from the request.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.triggers.delete(triggerReferenceId)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Delete an event trigger
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.triggers().delete("store-in-snowflake");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**triggerReferenceId:** `String` — The reference ID of the event trigger to delete. All other entity ID fields are inferred from the request.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.triggers.partialUpdate(triggerReferenceId, request) -> EventTriggerResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Updates an event trigger. Only the enabled field is editable.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.triggers().partialUpdate(
    "triggerReferenceId",
    PartialUpdateRequest
        .builder()
        .body(
            TriggerPartialUpdate
                .builder()
                .build()
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**triggerReferenceId:** `String` — The reference ID of the event trigger to update. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the trigger to update. If not provided, the ID of the calling app will be used.
    
</dd>
</dl>

<dl>
<dd>

**request:** `TriggerPartialUpdate` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

## Users
<details><summary><code>client.users.search(request) -> AgentUserSearchResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Search across all agent users on an agent.

Agent users are a merged view of the users created by individual apps.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.users().search(
    AgentUserSearchRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `AgentUserSearchRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.users.getAgentUser(agentUserId) -> AgentUser</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an agent user by its supplied ID.

Agent users are a merged view of the users created by individual apps.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.users().getAgentUser("aus_1234567890");
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**agentUserId:** `String` — The ID of the agent user to get.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.users.createOrUpdate(request) -> AppUserResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Update an app user or create it if it doesn't exist.
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.users().createOrUpdate(
    AppUserRequest
        .builder()
        .userId(
            EntityIdBase
                .builder()
                .referenceId("user-0")
                .build()
        )
        .identifiers(
            new HashSet<AppUserIdentifier>(
                Arrays.asList(
                    AppUserIdentifier
                        .builder()
                        .value("joe@myapp.com")
                        .type(AppUserIdentifyingPropertyType.EMAIL)
                        .build()
                )
            )
        )
        .data(
            new HashMap<String, UserData>() {{
                put("name", UserData
                    .builder()
                    .value("Joe")
                    .visibility(VisibilityType.VISIBLE)
                    .build());
            }}
        )
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**request:** `AppUserRequest` 
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.users.get(userId) -> AppUserResponse</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Get an app user by its supplied ID
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.users().get(
    "user-0",
    UserGetRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**userId:** `String` — The reference ID of the app user to get. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the app user to get. If not provided the ID of the calling app will be used.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>

<details><summary><code>client.users.delete(userId)</code></summary>
<dl>
<dd>

#### 📝 Description

<dl>
<dd>

<dl>
<dd>

Deletes all identifiers and user data saved by the specified app.
Does not modify data or identifiers saved by other apps.

If this user is linked to a user from another app, it will not be unlinked. Unlinking of users is not yet supported.

<Warning>This is a destructive operation and cannot be undone.</Warning>
</dd>
</dl>
</dd>
</dl>

#### 🔌 Usage

<dl>
<dd>

<dl>
<dd>

```java
client.users().delete(
    "user-0",
    UserDeleteRequest
        .builder()
        .build()
);
```
</dd>
</dl>
</dd>
</dl>

#### ⚙️ Parameters

<dl>
<dd>

<dl>
<dd>

**userId:** `String` — The reference ID of the app user to delete. All other entity ID fields are inferred from the request.
    
</dd>
</dl>

<dl>
<dd>

**appId:** `Optional<String>` — The App ID of the app user to delete. If not provided the ID of the calling app will be used.
    
</dd>
</dl>
</dd>
</dl>


</dd>
</dl>
</details>
