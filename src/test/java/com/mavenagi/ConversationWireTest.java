package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.AttachmentRequest;
import com.mavenagi.resources.commons.types.ConversationResponse;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.commons.types.EntityIdWithoutAgent;
import com.mavenagi.resources.commons.types.EntityType;
import com.mavenagi.resources.commons.types.Feedback;
import com.mavenagi.resources.commons.types.FeedbackType;
import com.mavenagi.resources.commons.types.UserConversationMessageType;
import com.mavenagi.resources.conversation.requests.ConversationDeleteRequest;
import com.mavenagi.resources.conversation.requests.ConversationGetRequest;
import com.mavenagi.resources.conversation.types.ActionFormRequestParamValue;
import com.mavenagi.resources.conversation.types.AskRequest;
import com.mavenagi.resources.conversation.types.CategorizationResponse;
import com.mavenagi.resources.conversation.types.ConversationMessageRequest;
import com.mavenagi.resources.conversation.types.ConversationMetadata;
import com.mavenagi.resources.conversation.types.ConversationPatchRequest;
import com.mavenagi.resources.conversation.types.ConversationRequest;
import com.mavenagi.resources.conversation.types.ConversationsResponse;
import com.mavenagi.resources.conversation.types.ConversationsSearchRequest;
import com.mavenagi.resources.conversation.types.DeliverMessageRequest;
import com.mavenagi.resources.conversation.types.DeliverMessageResponse;
import com.mavenagi.resources.conversation.types.DeliverUserMessageRequest;
import com.mavenagi.resources.conversation.types.FeedbackRequest;
import com.mavenagi.resources.conversation.types.SubmitActionFormRequest;
import com.mavenagi.resources.conversation.types.UpdateMetadataRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConversationWireTest {
    private MockWebServer server;
    private MavenAGI client;
    private ObjectMapper objectMapper = ObjectMappers.JSON_MAPPER;
    @BeforeEach
    public void setup() throws Exception {
        server = new MockWebServer();
        server.start();
        client = MavenAGI.builder()
            .url(server.url("/").toString())
            .credentials("testuser", "testpass")
            .build();
    }
    @AfterEach
    public void teardown() throws Exception {
        server.shutdown();
    }
    @Test
    public void testInitialize() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"messages\":[{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"},{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"}],\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"responseConfig\":{\"capabilities\":[\"MARKDOWN\",\"MARKDOWN\"],\"isCopilot\":true,\"responseLength\":\"SHORT\"},\"subject\":\"subject\",\"url\":\"url\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"tags\":[\"tags\"],\"metadata\":{\"metadata\":\"metadata\"},\"allMetadata\":{\"allMetadata\":{\"allMetadata\":\"allMetadata\"}},\"conversationId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"analysis\":{\"userRequest\":\"userRequest\",\"agentResponse\":\"agentResponse\",\"resolutionStatus\":\"resolutionStatus\",\"category\":\"category\",\"sentiment\":\"POSITIVE\",\"quality\":\"GOOD\",\"qualityReason\":\"MISSING_KNOWLEDGE\",\"resolvedByMaven\":true,\"primaryLanguage\":\"primaryLanguage\",\"predictedNps\":1.1},\"summary\":{\"actionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"incompleteActionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"insertCount\":1,\"thumbsUpCount\":1,\"thumbsDownCount\":1,\"handoffCount\":1,\"userMessageCount\":1,\"handleTime\":1000000,\"humanAgentResponseDelay\":1000000,\"humanAgents\":[\"humanAgents\",\"humanAgents\"],\"humanAgentsWithInserts\":[\"humanAgentsWithInserts\",\"humanAgentsWithInserts\"],\"users\":[\"users\",\"users\"],\"userIdentifiers\":[\"userIdentifiers\",\"userIdentifiers\"],\"lastUserMessage\":\"lastUserMessage\",\"lastBotMessage\":\"lastBotMessage\"},\"deleted\":true,\"open\":true,\"llmEnabled\":true,\"simulationContext\":{\"additionalPromptText\":\"additionalPromptText\",\"persona\":\"CASUAL_BUDDY\",\"availableKnowledgeBases\":[{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}]}}"));
        ConversationResponse response = client.conversation().initialize(
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
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"conversationId\": {\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"messages\": [\n"
            + "    {\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\"\n"
            + "    }\n"
            + "  ]\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"messages\": [\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"attachments\": [\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"responseConfig\": {\n"
            + "    \"capabilities\": [\n"
            + "      \"MARKDOWN\",\n"
            + "      \"MARKDOWN\"\n"
            + "    ],\n"
            + "    \"isCopilot\": true,\n"
            + "    \"responseLength\": \"SHORT\"\n"
            + "  },\n"
            + "  \"subject\": \"subject\",\n"
            + "  \"url\": \"url\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"tags\": [\n"
            + "    \"tags\"\n"
            + "  ],\n"
            + "  \"metadata\": {\n"
            + "    \"metadata\": \"metadata\"\n"
            + "  },\n"
            + "  \"allMetadata\": {\n"
            + "    \"allMetadata\": {\n"
            + "      \"allMetadata\": \"allMetadata\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"conversationId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"analysis\": {\n"
            + "    \"userRequest\": \"userRequest\",\n"
            + "    \"agentResponse\": \"agentResponse\",\n"
            + "    \"resolutionStatus\": \"resolutionStatus\",\n"
            + "    \"category\": \"category\",\n"
            + "    \"sentiment\": \"POSITIVE\",\n"
            + "    \"quality\": \"GOOD\",\n"
            + "    \"qualityReason\": \"MISSING_KNOWLEDGE\",\n"
            + "    \"resolvedByMaven\": true,\n"
            + "    \"primaryLanguage\": \"primaryLanguage\",\n"
            + "    \"predictedNps\": 1.1\n"
            + "  },\n"
            + "  \"summary\": {\n"
            + "    \"actionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"incompleteActionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"insertCount\": 1,\n"
            + "    \"thumbsUpCount\": 1,\n"
            + "    \"thumbsDownCount\": 1,\n"
            + "    \"handoffCount\": 1,\n"
            + "    \"userMessageCount\": 1,\n"
            + "    \"handleTime\": 1000000,\n"
            + "    \"humanAgentResponseDelay\": 1000000,\n"
            + "    \"humanAgents\": [\n"
            + "      \"humanAgents\",\n"
            + "      \"humanAgents\"\n"
            + "    ],\n"
            + "    \"humanAgentsWithInserts\": [\n"
            + "      \"humanAgentsWithInserts\",\n"
            + "      \"humanAgentsWithInserts\"\n"
            + "    ],\n"
            + "    \"users\": [\n"
            + "      \"users\",\n"
            + "      \"users\"\n"
            + "    ],\n"
            + "    \"userIdentifiers\": [\n"
            + "      \"userIdentifiers\",\n"
            + "      \"userIdentifiers\"\n"
            + "    ],\n"
            + "    \"lastUserMessage\": \"lastUserMessage\",\n"
            + "    \"lastBotMessage\": \"lastBotMessage\"\n"
            + "  },\n"
            + "  \"deleted\": true,\n"
            + "  \"open\": true,\n"
            + "  \"llmEnabled\": true,\n"
            + "  \"simulationContext\": {\n"
            + "    \"additionalPromptText\": \"additionalPromptText\",\n"
            + "    \"persona\": \"CASUAL_BUDDY\",\n"
            + "    \"availableKnowledgeBases\": [\n"
            + "      {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ]\n"
            + "  }\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testPatch() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"conversationId\":{\"referenceId\":\"conversation-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION\"},\"deleted\":false,\"open\":false,\"llmEnabled\":true,\"analysis\":{\"resolutionStatus\":\"Resolved\",\"sentiment\":\"POSITIVE\",\"resolvedByMaven\":true},\"summary\":{\"actionIds\":[],\"incompleteActionIds\":[],\"insertCount\":0,\"thumbsUpCount\":0,\"thumbsDownCount\":0,\"handoffCount\":0,\"userMessageCount\":1,\"humanAgents\":[],\"humanAgentsWithInserts\":[],\"users\":[],\"userIdentifiers\":[]},\"metadata\":{},\"allMetadata\":{},\"attachments\":[],\"messages\":[{\"type\":\"user\",\"userMessageType\":\"USER\",\"conversationMessageId\":{\"referenceId\":\"message-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION_MESSAGE\"},\"status\":\"UNKNOWN\",\"userId\":{\"referenceId\":\"user-0\"},\"text\":\"How do I reset my password?\",\"attachments\":[{\"url\":\"https://example.com/attachment-0\",\"type\":\"image/png\",\"status\":\"ACCEPTED\",\"sizeBytes\":1234}]},{\"type\":\"bot\",\"botMessageType\":\"BOT_RESPONSE\",\"conversationMessageId\":{\"referenceId\":\"message-1\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION_MESSAGE\"},\"status\":\"SENT\",\"responses\":[{\"type\":\"text\",\"text\":\"Hi! Go to acme.com/reset-password\"}],\"metadata\":{\"followupQuestions\":[\"What if I did not get the reset email?\"],\"sources\":[]}}]}"));
        ConversationResponse response = client.conversation().patch(
            "conversation-0",
            ConversationPatchRequest
                .builder()
                .llmEnabled(true)
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PATCH", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"llmEnabled\": true\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"conversationId\": {\n"
            + "    \"referenceId\": \"conversation-0\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CONVERSATION\"\n"
            + "  },\n"
            + "  \"deleted\": false,\n"
            + "  \"open\": false,\n"
            + "  \"llmEnabled\": true,\n"
            + "  \"analysis\": {\n"
            + "    \"resolutionStatus\": \"Resolved\",\n"
            + "    \"sentiment\": \"POSITIVE\",\n"
            + "    \"resolvedByMaven\": true\n"
            + "  },\n"
            + "  \"summary\": {\n"
            + "    \"actionIds\": [],\n"
            + "    \"incompleteActionIds\": [],\n"
            + "    \"insertCount\": 0,\n"
            + "    \"thumbsUpCount\": 0,\n"
            + "    \"thumbsDownCount\": 0,\n"
            + "    \"handoffCount\": 0,\n"
            + "    \"userMessageCount\": 1,\n"
            + "    \"humanAgents\": [],\n"
            + "    \"humanAgentsWithInserts\": [],\n"
            + "    \"users\": [],\n"
            + "    \"userIdentifiers\": []\n"
            + "  },\n"
            + "  \"metadata\": {},\n"
            + "  \"allMetadata\": {},\n"
            + "  \"attachments\": [],\n"
            + "  \"messages\": [\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"referenceId\": \"message-0\",\n"
            + "        \"appId\": \"myapp\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CONVERSATION_MESSAGE\"\n"
            + "      },\n"
            + "      \"status\": \"UNKNOWN\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"user-0\"\n"
            + "      },\n"
            + "      \"text\": \"How do I reset my password?\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"https://example.com/attachment-0\",\n"
            + "          \"type\": \"image/png\",\n"
            + "          \"status\": \"ACCEPTED\",\n"
            + "          \"sizeBytes\": 1234\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"bot\",\n"
            + "      \"botMessageType\": \"BOT_RESPONSE\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"referenceId\": \"message-1\",\n"
            + "        \"appId\": \"myapp\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CONVERSATION_MESSAGE\"\n"
            + "      },\n"
            + "      \"status\": \"SENT\",\n"
            + "      \"responses\": [\n"
            + "        {\n"
            + "          \"type\": \"text\",\n"
            + "          \"text\": \"Hi! Go to acme.com/reset-password\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"metadata\": {\n"
            + "        \"followupQuestions\": [\n"
            + "          \"What if I did not get the reset email?\"\n"
            + "        ],\n"
            + "        \"sources\": []\n"
            + "      }\n"
            + "    }\n"
            + "  ]\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testGet() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"messages\":[{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"},{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"}],\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"responseConfig\":{\"capabilities\":[\"MARKDOWN\",\"MARKDOWN\"],\"isCopilot\":true,\"responseLength\":\"SHORT\"},\"subject\":\"subject\",\"url\":\"url\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"tags\":[\"tags\"],\"metadata\":{\"metadata\":\"metadata\"},\"allMetadata\":{\"allMetadata\":{\"allMetadata\":\"allMetadata\"}},\"conversationId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"analysis\":{\"userRequest\":\"userRequest\",\"agentResponse\":\"agentResponse\",\"resolutionStatus\":\"resolutionStatus\",\"category\":\"category\",\"sentiment\":\"POSITIVE\",\"quality\":\"GOOD\",\"qualityReason\":\"MISSING_KNOWLEDGE\",\"resolvedByMaven\":true,\"primaryLanguage\":\"primaryLanguage\",\"predictedNps\":1.1},\"summary\":{\"actionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"incompleteActionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"insertCount\":1,\"thumbsUpCount\":1,\"thumbsDownCount\":1,\"handoffCount\":1,\"userMessageCount\":1,\"handleTime\":1000000,\"humanAgentResponseDelay\":1000000,\"humanAgents\":[\"humanAgents\",\"humanAgents\"],\"humanAgentsWithInserts\":[\"humanAgentsWithInserts\",\"humanAgentsWithInserts\"],\"users\":[\"users\",\"users\"],\"userIdentifiers\":[\"userIdentifiers\",\"userIdentifiers\"],\"lastUserMessage\":\"lastUserMessage\",\"lastBotMessage\":\"lastBotMessage\"},\"deleted\":true,\"open\":true,\"llmEnabled\":true,\"simulationContext\":{\"additionalPromptText\":\"additionalPromptText\",\"persona\":\"CASUAL_BUDDY\",\"availableKnowledgeBases\":[{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}]}}"));
        ConversationResponse response = client.conversation().get(
            "conversationId",
            ConversationGetRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("GET", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"messages\": [\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"attachments\": [\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"responseConfig\": {\n"
            + "    \"capabilities\": [\n"
            + "      \"MARKDOWN\",\n"
            + "      \"MARKDOWN\"\n"
            + "    ],\n"
            + "    \"isCopilot\": true,\n"
            + "    \"responseLength\": \"SHORT\"\n"
            + "  },\n"
            + "  \"subject\": \"subject\",\n"
            + "  \"url\": \"url\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"tags\": [\n"
            + "    \"tags\"\n"
            + "  ],\n"
            + "  \"metadata\": {\n"
            + "    \"metadata\": \"metadata\"\n"
            + "  },\n"
            + "  \"allMetadata\": {\n"
            + "    \"allMetadata\": {\n"
            + "      \"allMetadata\": \"allMetadata\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"conversationId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"analysis\": {\n"
            + "    \"userRequest\": \"userRequest\",\n"
            + "    \"agentResponse\": \"agentResponse\",\n"
            + "    \"resolutionStatus\": \"resolutionStatus\",\n"
            + "    \"category\": \"category\",\n"
            + "    \"sentiment\": \"POSITIVE\",\n"
            + "    \"quality\": \"GOOD\",\n"
            + "    \"qualityReason\": \"MISSING_KNOWLEDGE\",\n"
            + "    \"resolvedByMaven\": true,\n"
            + "    \"primaryLanguage\": \"primaryLanguage\",\n"
            + "    \"predictedNps\": 1.1\n"
            + "  },\n"
            + "  \"summary\": {\n"
            + "    \"actionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"incompleteActionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"insertCount\": 1,\n"
            + "    \"thumbsUpCount\": 1,\n"
            + "    \"thumbsDownCount\": 1,\n"
            + "    \"handoffCount\": 1,\n"
            + "    \"userMessageCount\": 1,\n"
            + "    \"handleTime\": 1000000,\n"
            + "    \"humanAgentResponseDelay\": 1000000,\n"
            + "    \"humanAgents\": [\n"
            + "      \"humanAgents\",\n"
            + "      \"humanAgents\"\n"
            + "    ],\n"
            + "    \"humanAgentsWithInserts\": [\n"
            + "      \"humanAgentsWithInserts\",\n"
            + "      \"humanAgentsWithInserts\"\n"
            + "    ],\n"
            + "    \"users\": [\n"
            + "      \"users\",\n"
            + "      \"users\"\n"
            + "    ],\n"
            + "    \"userIdentifiers\": [\n"
            + "      \"userIdentifiers\",\n"
            + "      \"userIdentifiers\"\n"
            + "    ],\n"
            + "    \"lastUserMessage\": \"lastUserMessage\",\n"
            + "    \"lastBotMessage\": \"lastBotMessage\"\n"
            + "  },\n"
            + "  \"deleted\": true,\n"
            + "  \"open\": true,\n"
            + "  \"llmEnabled\": true,\n"
            + "  \"simulationContext\": {\n"
            + "    \"additionalPromptText\": \"additionalPromptText\",\n"
            + "    \"persona\": \"CASUAL_BUDDY\",\n"
            + "    \"availableKnowledgeBases\": [\n"
            + "      {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ]\n"
            + "  }\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testDelete() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{}"));
        client.conversation().delete(
            "conversation-0",
            ConversationDeleteRequest
                .builder()
                .reason("GDPR deletion request 1234.")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("DELETE", request.getMethod());
    }
    @Test
    public void testAppendNewMessages() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"messages\":[{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"},{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"}],\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"responseConfig\":{\"capabilities\":[\"MARKDOWN\",\"MARKDOWN\"],\"isCopilot\":true,\"responseLength\":\"SHORT\"},\"subject\":\"subject\",\"url\":\"url\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"tags\":[\"tags\"],\"metadata\":{\"metadata\":\"metadata\"},\"allMetadata\":{\"allMetadata\":{\"allMetadata\":\"allMetadata\"}},\"conversationId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"analysis\":{\"userRequest\":\"userRequest\",\"agentResponse\":\"agentResponse\",\"resolutionStatus\":\"resolutionStatus\",\"category\":\"category\",\"sentiment\":\"POSITIVE\",\"quality\":\"GOOD\",\"qualityReason\":\"MISSING_KNOWLEDGE\",\"resolvedByMaven\":true,\"primaryLanguage\":\"primaryLanguage\",\"predictedNps\":1.1},\"summary\":{\"actionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"incompleteActionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"insertCount\":1,\"thumbsUpCount\":1,\"thumbsDownCount\":1,\"handoffCount\":1,\"userMessageCount\":1,\"handleTime\":1000000,\"humanAgentResponseDelay\":1000000,\"humanAgents\":[\"humanAgents\",\"humanAgents\"],\"humanAgentsWithInserts\":[\"humanAgentsWithInserts\",\"humanAgentsWithInserts\"],\"users\":[\"users\",\"users\"],\"userIdentifiers\":[\"userIdentifiers\",\"userIdentifiers\"],\"lastUserMessage\":\"lastUserMessage\",\"lastBotMessage\":\"lastBotMessage\"},\"deleted\":true,\"open\":true,\"llmEnabled\":true,\"simulationContext\":{\"additionalPromptText\":\"additionalPromptText\",\"persona\":\"CASUAL_BUDDY\",\"availableKnowledgeBases\":[{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}]}}"));
        ConversationResponse response = client.conversation().appendNewMessages(
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
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "[\n"
            + "  {\n"
            + "    \"conversationMessageId\": {\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"userId\": {\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"text\": \"text\",\n"
            + "    \"userMessageType\": \"USER\"\n"
            + "  },\n"
            + "  {\n"
            + "    \"conversationMessageId\": {\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"userId\": {\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"text\": \"text\",\n"
            + "    \"userMessageType\": \"USER\"\n"
            + "  }\n"
            + "]";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"messages\": [\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"attachments\": [\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"responseConfig\": {\n"
            + "    \"capabilities\": [\n"
            + "      \"MARKDOWN\",\n"
            + "      \"MARKDOWN\"\n"
            + "    ],\n"
            + "    \"isCopilot\": true,\n"
            + "    \"responseLength\": \"SHORT\"\n"
            + "  },\n"
            + "  \"subject\": \"subject\",\n"
            + "  \"url\": \"url\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"tags\": [\n"
            + "    \"tags\"\n"
            + "  ],\n"
            + "  \"metadata\": {\n"
            + "    \"metadata\": \"metadata\"\n"
            + "  },\n"
            + "  \"allMetadata\": {\n"
            + "    \"allMetadata\": {\n"
            + "      \"allMetadata\": \"allMetadata\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"conversationId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"analysis\": {\n"
            + "    \"userRequest\": \"userRequest\",\n"
            + "    \"agentResponse\": \"agentResponse\",\n"
            + "    \"resolutionStatus\": \"resolutionStatus\",\n"
            + "    \"category\": \"category\",\n"
            + "    \"sentiment\": \"POSITIVE\",\n"
            + "    \"quality\": \"GOOD\",\n"
            + "    \"qualityReason\": \"MISSING_KNOWLEDGE\",\n"
            + "    \"resolvedByMaven\": true,\n"
            + "    \"primaryLanguage\": \"primaryLanguage\",\n"
            + "    \"predictedNps\": 1.1\n"
            + "  },\n"
            + "  \"summary\": {\n"
            + "    \"actionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"incompleteActionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"insertCount\": 1,\n"
            + "    \"thumbsUpCount\": 1,\n"
            + "    \"thumbsDownCount\": 1,\n"
            + "    \"handoffCount\": 1,\n"
            + "    \"userMessageCount\": 1,\n"
            + "    \"handleTime\": 1000000,\n"
            + "    \"humanAgentResponseDelay\": 1000000,\n"
            + "    \"humanAgents\": [\n"
            + "      \"humanAgents\",\n"
            + "      \"humanAgents\"\n"
            + "    ],\n"
            + "    \"humanAgentsWithInserts\": [\n"
            + "      \"humanAgentsWithInserts\",\n"
            + "      \"humanAgentsWithInserts\"\n"
            + "    ],\n"
            + "    \"users\": [\n"
            + "      \"users\",\n"
            + "      \"users\"\n"
            + "    ],\n"
            + "    \"userIdentifiers\": [\n"
            + "      \"userIdentifiers\",\n"
            + "      \"userIdentifiers\"\n"
            + "    ],\n"
            + "    \"lastUserMessage\": \"lastUserMessage\",\n"
            + "    \"lastBotMessage\": \"lastBotMessage\"\n"
            + "  },\n"
            + "  \"deleted\": true,\n"
            + "  \"open\": true,\n"
            + "  \"llmEnabled\": true,\n"
            + "  \"simulationContext\": {\n"
            + "    \"additionalPromptText\": \"additionalPromptText\",\n"
            + "    \"persona\": \"CASUAL_BUDDY\",\n"
            + "    \"availableKnowledgeBases\": [\n"
            + "      {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ]\n"
            + "  }\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testAsk() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"conversationId\":{\"referenceId\":\"conversation-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION\"},\"deleted\":false,\"open\":false,\"llmEnabled\":true,\"analysis\":{\"resolutionStatus\":\"Resolved\",\"sentiment\":\"POSITIVE\",\"resolvedByMaven\":true},\"summary\":{\"actionIds\":[],\"incompleteActionIds\":[],\"insertCount\":0,\"thumbsUpCount\":0,\"thumbsDownCount\":0,\"handoffCount\":0,\"userMessageCount\":1,\"humanAgents\":[],\"humanAgentsWithInserts\":[],\"users\":[],\"userIdentifiers\":[]},\"metadata\":{},\"allMetadata\":{},\"attachments\":[],\"messages\":[{\"type\":\"user\",\"userMessageType\":\"USER\",\"conversationMessageId\":{\"referenceId\":\"message-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION_MESSAGE\"},\"status\":\"UNKNOWN\",\"userId\":{\"referenceId\":\"user-0\"},\"text\":\"How do I reset my password?\",\"attachments\":[{\"url\":\"https://example.com/attachment-0\",\"type\":\"image/png\",\"status\":\"ACCEPTED\",\"sizeBytes\":1234}]},{\"type\":\"bot\",\"botMessageType\":\"BOT_RESPONSE\",\"conversationMessageId\":{\"referenceId\":\"message-1\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION_MESSAGE\"},\"status\":\"SENT\",\"responses\":[{\"type\":\"text\",\"text\":\"Hi! Go to acme.com/reset-password\"}],\"metadata\":{\"followupQuestions\":[\"What if I did not get the reset email?\"],\"sources\":[]}}]}"));
        ConversationResponse response = client.conversation().ask(
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
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"conversationMessageId\": {\n"
            + "    \"referenceId\": \"message-0\"\n"
            + "  },\n"
            + "  \"userId\": {\n"
            + "    \"referenceId\": \"user-0\"\n"
            + "  },\n"
            + "  \"text\": \"How do I reset my password?\",\n"
            + "  \"attachments\": [\n"
            + "    {\n"
            + "      \"type\": \"image/png\",\n"
            + "      \"content\": \"iVBORw0KGgo...\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"transientData\": {\n"
            + "    \"userToken\": \"abcdef123\",\n"
            + "    \"queryApiKey\": \"foobar456\"\n"
            + "  },\n"
            + "  \"timezone\": \"America/New_York\"\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"conversationId\": {\n"
            + "    \"referenceId\": \"conversation-0\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CONVERSATION\"\n"
            + "  },\n"
            + "  \"deleted\": false,\n"
            + "  \"open\": false,\n"
            + "  \"llmEnabled\": true,\n"
            + "  \"analysis\": {\n"
            + "    \"resolutionStatus\": \"Resolved\",\n"
            + "    \"sentiment\": \"POSITIVE\",\n"
            + "    \"resolvedByMaven\": true\n"
            + "  },\n"
            + "  \"summary\": {\n"
            + "    \"actionIds\": [],\n"
            + "    \"incompleteActionIds\": [],\n"
            + "    \"insertCount\": 0,\n"
            + "    \"thumbsUpCount\": 0,\n"
            + "    \"thumbsDownCount\": 0,\n"
            + "    \"handoffCount\": 0,\n"
            + "    \"userMessageCount\": 1,\n"
            + "    \"humanAgents\": [],\n"
            + "    \"humanAgentsWithInserts\": [],\n"
            + "    \"users\": [],\n"
            + "    \"userIdentifiers\": []\n"
            + "  },\n"
            + "  \"metadata\": {},\n"
            + "  \"allMetadata\": {},\n"
            + "  \"attachments\": [],\n"
            + "  \"messages\": [\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"referenceId\": \"message-0\",\n"
            + "        \"appId\": \"myapp\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CONVERSATION_MESSAGE\"\n"
            + "      },\n"
            + "      \"status\": \"UNKNOWN\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"user-0\"\n"
            + "      },\n"
            + "      \"text\": \"How do I reset my password?\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"https://example.com/attachment-0\",\n"
            + "          \"type\": \"image/png\",\n"
            + "          \"status\": \"ACCEPTED\",\n"
            + "          \"sizeBytes\": 1234\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"bot\",\n"
            + "      \"botMessageType\": \"BOT_RESPONSE\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"referenceId\": \"message-1\",\n"
            + "        \"appId\": \"myapp\",\n"
            + "        \"organizationId\": \"acme\",\n"
            + "        \"agentId\": \"support\",\n"
            + "        \"type\": \"CONVERSATION_MESSAGE\"\n"
            + "      },\n"
            + "      \"status\": \"SENT\",\n"
            + "      \"responses\": [\n"
            + "        {\n"
            + "          \"type\": \"text\",\n"
            + "          \"text\": \"Hi! Go to acme.com/reset-password\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"metadata\": {\n"
            + "        \"followupQuestions\": [\n"
            + "          \"What if I did not get the reset email?\"\n"
            + "        ],\n"
            + "        \"sources\": []\n"
            + "      }\n"
            + "    }\n"
            + "  ]\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testCategorize() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"category\":\"category\"}"));
        CategorizationResponse response = client.conversation().categorize("conversationId");
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"category\": \"category\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testCreateFeedback() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"feedbackId\":{\"referenceId\":\"feedback-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"FEEDBACK\"},\"conversationId\":{\"referenceId\":\"conversation-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION\"},\"conversationMessageId\":{\"referenceId\":\"message-1\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION_MESSAGE\"},\"userId\":{\"referenceId\":\"user-0\",\"appId\":\"myapp\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"USER\"},\"type\":\"THUMBS_UP\",\"text\":\"Great answer!\"}"));
        Feedback response = client.conversation().createFeedback(
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
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"feedbackId\": {\n"
            + "    \"referenceId\": \"feedback-0\"\n"
            + "  },\n"
            + "  \"userId\": {\n"
            + "    \"referenceId\": \"user-0\"\n"
            + "  },\n"
            + "  \"conversationId\": {\n"
            + "    \"referenceId\": \"conversation-0\"\n"
            + "  },\n"
            + "  \"conversationMessageId\": {\n"
            + "    \"referenceId\": \"message-1\"\n"
            + "  },\n"
            + "  \"type\": \"THUMBS_UP\",\n"
            + "  \"text\": \"Great answer!\"\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"feedbackId\": {\n"
            + "    \"referenceId\": \"feedback-0\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"FEEDBACK\"\n"
            + "  },\n"
            + "  \"conversationId\": {\n"
            + "    \"referenceId\": \"conversation-0\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CONVERSATION\"\n"
            + "  },\n"
            + "  \"conversationMessageId\": {\n"
            + "    \"referenceId\": \"message-1\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CONVERSATION_MESSAGE\"\n"
            + "  },\n"
            + "  \"userId\": {\n"
            + "    \"referenceId\": \"user-0\",\n"
            + "    \"appId\": \"myapp\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"USER\"\n"
            + "  },\n"
            + "  \"type\": \"THUMBS_UP\",\n"
            + "  \"text\": \"Great answer!\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testSubmitActionForm() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"messages\":[{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"},{\"type\":\"user\",\"conversationMessageId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"language\":\"language\",\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"agentUserId\":\"agentUserId\",\"userDisplayName\":\"userDisplayName\",\"status\":\"SENDING\",\"responseState\":\"NOT_ASKED\",\"userId\":{\"referenceId\":\"referenceId\"},\"text\":\"text\",\"userMessageType\":\"USER\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\"}],\"attachments\":[{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"},{\"url\":\"url\",\"sizeBytes\":1000000,\"status\":\"PENDING\",\"type\":\"type\",\"name\":\"name\"}],\"responseConfig\":{\"capabilities\":[\"MARKDOWN\",\"MARKDOWN\"],\"isCopilot\":true,\"responseLength\":\"SHORT\"},\"subject\":\"subject\",\"url\":\"url\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"tags\":[\"tags\"],\"metadata\":{\"metadata\":\"metadata\"},\"allMetadata\":{\"allMetadata\":{\"allMetadata\":\"allMetadata\"}},\"conversationId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"analysis\":{\"userRequest\":\"userRequest\",\"agentResponse\":\"agentResponse\",\"resolutionStatus\":\"resolutionStatus\",\"category\":\"category\",\"sentiment\":\"POSITIVE\",\"quality\":\"GOOD\",\"qualityReason\":\"MISSING_KNOWLEDGE\",\"resolvedByMaven\":true,\"primaryLanguage\":\"primaryLanguage\",\"predictedNps\":1.1},\"summary\":{\"actionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"incompleteActionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"insertCount\":1,\"thumbsUpCount\":1,\"thumbsDownCount\":1,\"handoffCount\":1,\"userMessageCount\":1,\"handleTime\":1000000,\"humanAgentResponseDelay\":1000000,\"humanAgents\":[\"humanAgents\",\"humanAgents\"],\"humanAgentsWithInserts\":[\"humanAgentsWithInserts\",\"humanAgentsWithInserts\"],\"users\":[\"users\",\"users\"],\"userIdentifiers\":[\"userIdentifiers\",\"userIdentifiers\"],\"lastUserMessage\":\"lastUserMessage\",\"lastBotMessage\":\"lastBotMessage\"},\"deleted\":true,\"open\":true,\"llmEnabled\":true,\"simulationContext\":{\"additionalPromptText\":\"additionalPromptText\",\"persona\":\"CASUAL_BUDDY\",\"availableKnowledgeBases\":[{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}]}}"));
        ConversationResponse response = client.conversation().submitActionForm(
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
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"actionFormId\": \"actionFormId\",\n"
            + "  \"parameters\": {\n"
            + "    \"parameters\": {\n"
            + "      \"key\": \"value\"\n"
            + "    }\n"
            + "  }\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"messages\": [\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"type\": \"user\",\n"
            + "      \"conversationMessageId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"language\": \"language\",\n"
            + "      \"attachments\": [\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"url\": \"url\",\n"
            + "          \"sizeBytes\": 1000000,\n"
            + "          \"status\": \"PENDING\",\n"
            + "          \"type\": \"type\",\n"
            + "          \"name\": \"name\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"agentUserId\": \"agentUserId\",\n"
            + "      \"userDisplayName\": \"userDisplayName\",\n"
            + "      \"status\": \"SENDING\",\n"
            + "      \"responseState\": \"NOT_ASKED\",\n"
            + "      \"userId\": {\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"text\": \"text\",\n"
            + "      \"userMessageType\": \"USER\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"attachments\": [\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"url\": \"url\",\n"
            + "      \"sizeBytes\": 1000000,\n"
            + "      \"status\": \"PENDING\",\n"
            + "      \"type\": \"type\",\n"
            + "      \"name\": \"name\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"responseConfig\": {\n"
            + "    \"capabilities\": [\n"
            + "      \"MARKDOWN\",\n"
            + "      \"MARKDOWN\"\n"
            + "    ],\n"
            + "    \"isCopilot\": true,\n"
            + "    \"responseLength\": \"SHORT\"\n"
            + "  },\n"
            + "  \"subject\": \"subject\",\n"
            + "  \"url\": \"url\",\n"
            + "  \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "  \"tags\": [\n"
            + "    \"tags\"\n"
            + "  ],\n"
            + "  \"metadata\": {\n"
            + "    \"metadata\": \"metadata\"\n"
            + "  },\n"
            + "  \"allMetadata\": {\n"
            + "    \"allMetadata\": {\n"
            + "      \"allMetadata\": \"allMetadata\"\n"
            + "    }\n"
            + "  },\n"
            + "  \"conversationId\": {\n"
            + "    \"organizationId\": \"organizationId\",\n"
            + "    \"agentId\": \"agentId\",\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"analysis\": {\n"
            + "    \"userRequest\": \"userRequest\",\n"
            + "    \"agentResponse\": \"agentResponse\",\n"
            + "    \"resolutionStatus\": \"resolutionStatus\",\n"
            + "    \"category\": \"category\",\n"
            + "    \"sentiment\": \"POSITIVE\",\n"
            + "    \"quality\": \"GOOD\",\n"
            + "    \"qualityReason\": \"MISSING_KNOWLEDGE\",\n"
            + "    \"resolvedByMaven\": true,\n"
            + "    \"primaryLanguage\": \"primaryLanguage\",\n"
            + "    \"predictedNps\": 1.1\n"
            + "  },\n"
            + "  \"summary\": {\n"
            + "    \"actionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"incompleteActionIds\": [\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      {\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ],\n"
            + "    \"insertCount\": 1,\n"
            + "    \"thumbsUpCount\": 1,\n"
            + "    \"thumbsDownCount\": 1,\n"
            + "    \"handoffCount\": 1,\n"
            + "    \"userMessageCount\": 1,\n"
            + "    \"handleTime\": 1000000,\n"
            + "    \"humanAgentResponseDelay\": 1000000,\n"
            + "    \"humanAgents\": [\n"
            + "      \"humanAgents\",\n"
            + "      \"humanAgents\"\n"
            + "    ],\n"
            + "    \"humanAgentsWithInserts\": [\n"
            + "      \"humanAgentsWithInserts\",\n"
            + "      \"humanAgentsWithInserts\"\n"
            + "    ],\n"
            + "    \"users\": [\n"
            + "      \"users\",\n"
            + "      \"users\"\n"
            + "    ],\n"
            + "    \"userIdentifiers\": [\n"
            + "      \"userIdentifiers\",\n"
            + "      \"userIdentifiers\"\n"
            + "    ],\n"
            + "    \"lastUserMessage\": \"lastUserMessage\",\n"
            + "    \"lastBotMessage\": \"lastBotMessage\"\n"
            + "  },\n"
            + "  \"deleted\": true,\n"
            + "  \"open\": true,\n"
            + "  \"llmEnabled\": true,\n"
            + "  \"simulationContext\": {\n"
            + "    \"additionalPromptText\": \"additionalPromptText\",\n"
            + "    \"persona\": \"CASUAL_BUDDY\",\n"
            + "    \"availableKnowledgeBases\": [\n"
            + "      {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      }\n"
            + "    ]\n"
            + "  }\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testAddConversationMetadata() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"string\":\"string\"}"));
        Map<String, String> response = client.conversation().addConversationMetadata(
            "conversationId",
            new HashMap<String, String>() {{
                put("string", "string");
            }}
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"string\": \"string\"\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"string\": \"string\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testUpdateConversationMetadata() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"metadata\":{\"myapp\":{\"key\":\"newValue\"},\"conversation-owning-app\":{\"existingKey\":\"existingValue\"}}}"));
        ConversationMetadata response = client.conversation().updateConversationMetadata(
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
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PUT", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"appId\": \"conversation-owning-app\",\n"
            + "  \"values\": {\n"
            + "    \"key\": \"newValue\"\n"
            + "  }\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"metadata\": {\n"
            + "    \"myapp\": {\n"
            + "      \"key\": \"newValue\"\n"
            + "    },\n"
            + "    \"conversation-owning-app\": {\n"
            + "      \"existingKey\": \"existingValue\"\n"
            + "    }\n"
            + "  }\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testSearch() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"conversations\":[{\"responseConfig\":{\"capabilities\":[\"MARKDOWN\",\"MARKDOWN\"],\"isCopilot\":true,\"responseLength\":\"SHORT\"},\"subject\":\"subject\",\"url\":\"url\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"tags\":[\"tags\"],\"metadata\":{\"metadata\":\"metadata\"},\"allMetadata\":{\"allMetadata\":{\"allMetadata\":\"allMetadata\"}},\"conversationId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"analysis\":{\"userRequest\":\"userRequest\",\"agentResponse\":\"agentResponse\",\"resolutionStatus\":\"resolutionStatus\",\"category\":\"category\",\"sentiment\":\"POSITIVE\",\"quality\":\"GOOD\",\"qualityReason\":\"MISSING_KNOWLEDGE\",\"resolvedByMaven\":true,\"primaryLanguage\":\"primaryLanguage\",\"predictedNps\":1.1},\"summary\":{\"actionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"incompleteActionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"insertCount\":1,\"thumbsUpCount\":1,\"thumbsDownCount\":1,\"handoffCount\":1,\"userMessageCount\":1,\"handleTime\":1000000,\"humanAgentResponseDelay\":1000000,\"humanAgents\":[\"humanAgents\",\"humanAgents\"],\"humanAgentsWithInserts\":[\"humanAgentsWithInserts\",\"humanAgentsWithInserts\"],\"users\":[\"users\",\"users\"],\"userIdentifiers\":[\"userIdentifiers\",\"userIdentifiers\"],\"lastUserMessage\":\"lastUserMessage\",\"lastBotMessage\":\"lastBotMessage\"},\"deleted\":true,\"open\":true,\"llmEnabled\":true,\"simulationContext\":{\"additionalPromptText\":\"additionalPromptText\",\"persona\":\"CASUAL_BUDDY\",\"availableKnowledgeBases\":[{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}]}},{\"responseConfig\":{\"capabilities\":[\"MARKDOWN\",\"MARKDOWN\"],\"isCopilot\":true,\"responseLength\":\"SHORT\"},\"subject\":\"subject\",\"url\":\"url\",\"createdAt\":\"2024-01-15T09:30:00Z\",\"updatedAt\":\"2024-01-15T09:30:00Z\",\"tags\":[\"tags\"],\"metadata\":{\"metadata\":\"metadata\"},\"allMetadata\":{\"allMetadata\":{\"allMetadata\":\"allMetadata\"}},\"conversationId\":{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},\"analysis\":{\"userRequest\":\"userRequest\",\"agentResponse\":\"agentResponse\",\"resolutionStatus\":\"resolutionStatus\",\"category\":\"category\",\"sentiment\":\"POSITIVE\",\"quality\":\"GOOD\",\"qualityReason\":\"MISSING_KNOWLEDGE\",\"resolvedByMaven\":true,\"primaryLanguage\":\"primaryLanguage\",\"predictedNps\":1.1},\"summary\":{\"actionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"incompleteActionIds\":[{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"},{\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}],\"insertCount\":1,\"thumbsUpCount\":1,\"thumbsDownCount\":1,\"handoffCount\":1,\"userMessageCount\":1,\"handleTime\":1000000,\"humanAgentResponseDelay\":1000000,\"humanAgents\":[\"humanAgents\",\"humanAgents\"],\"humanAgentsWithInserts\":[\"humanAgentsWithInserts\",\"humanAgentsWithInserts\"],\"users\":[\"users\",\"users\"],\"userIdentifiers\":[\"userIdentifiers\",\"userIdentifiers\"],\"lastUserMessage\":\"lastUserMessage\",\"lastBotMessage\":\"lastBotMessage\"},\"deleted\":true,\"open\":true,\"llmEnabled\":true,\"simulationContext\":{\"additionalPromptText\":\"additionalPromptText\",\"persona\":\"CASUAL_BUDDY\",\"availableKnowledgeBases\":[{\"organizationId\":\"organizationId\",\"agentId\":\"agentId\",\"type\":\"AGENT\",\"appId\":\"appId\",\"referenceId\":\"referenceId\"}]}}],\"number\":1,\"size\":1,\"totalElements\":1000000,\"totalPages\":1}"));
        ConversationsResponse response = client.conversation().search(
            ConversationsSearchRequest
                .builder()
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"conversations\": [\n"
            + "    {\n"
            + "      \"responseConfig\": {\n"
            + "        \"capabilities\": [\n"
            + "          \"MARKDOWN\",\n"
            + "          \"MARKDOWN\"\n"
            + "        ],\n"
            + "        \"isCopilot\": true,\n"
            + "        \"responseLength\": \"SHORT\"\n"
            + "      },\n"
            + "      \"subject\": \"subject\",\n"
            + "      \"url\": \"url\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"tags\": [\n"
            + "        \"tags\"\n"
            + "      ],\n"
            + "      \"metadata\": {\n"
            + "        \"metadata\": \"metadata\"\n"
            + "      },\n"
            + "      \"allMetadata\": {\n"
            + "        \"allMetadata\": {\n"
            + "          \"allMetadata\": \"allMetadata\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"conversationId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"analysis\": {\n"
            + "        \"userRequest\": \"userRequest\",\n"
            + "        \"agentResponse\": \"agentResponse\",\n"
            + "        \"resolutionStatus\": \"resolutionStatus\",\n"
            + "        \"category\": \"category\",\n"
            + "        \"sentiment\": \"POSITIVE\",\n"
            + "        \"quality\": \"GOOD\",\n"
            + "        \"qualityReason\": \"MISSING_KNOWLEDGE\",\n"
            + "        \"resolvedByMaven\": true,\n"
            + "        \"primaryLanguage\": \"primaryLanguage\",\n"
            + "        \"predictedNps\": 1.1\n"
            + "      },\n"
            + "      \"summary\": {\n"
            + "        \"actionIds\": [\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        ],\n"
            + "        \"incompleteActionIds\": [\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        ],\n"
            + "        \"insertCount\": 1,\n"
            + "        \"thumbsUpCount\": 1,\n"
            + "        \"thumbsDownCount\": 1,\n"
            + "        \"handoffCount\": 1,\n"
            + "        \"userMessageCount\": 1,\n"
            + "        \"handleTime\": 1000000,\n"
            + "        \"humanAgentResponseDelay\": 1000000,\n"
            + "        \"humanAgents\": [\n"
            + "          \"humanAgents\",\n"
            + "          \"humanAgents\"\n"
            + "        ],\n"
            + "        \"humanAgentsWithInserts\": [\n"
            + "          \"humanAgentsWithInserts\",\n"
            + "          \"humanAgentsWithInserts\"\n"
            + "        ],\n"
            + "        \"users\": [\n"
            + "          \"users\",\n"
            + "          \"users\"\n"
            + "        ],\n"
            + "        \"userIdentifiers\": [\n"
            + "          \"userIdentifiers\",\n"
            + "          \"userIdentifiers\"\n"
            + "        ],\n"
            + "        \"lastUserMessage\": \"lastUserMessage\",\n"
            + "        \"lastBotMessage\": \"lastBotMessage\"\n"
            + "      },\n"
            + "      \"deleted\": true,\n"
            + "      \"open\": true,\n"
            + "      \"llmEnabled\": true,\n"
            + "      \"simulationContext\": {\n"
            + "        \"additionalPromptText\": \"additionalPromptText\",\n"
            + "        \"persona\": \"CASUAL_BUDDY\",\n"
            + "        \"availableKnowledgeBases\": [\n"
            + "          {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        ]\n"
            + "      }\n"
            + "    },\n"
            + "    {\n"
            + "      \"responseConfig\": {\n"
            + "        \"capabilities\": [\n"
            + "          \"MARKDOWN\",\n"
            + "          \"MARKDOWN\"\n"
            + "        ],\n"
            + "        \"isCopilot\": true,\n"
            + "        \"responseLength\": \"SHORT\"\n"
            + "      },\n"
            + "      \"subject\": \"subject\",\n"
            + "      \"url\": \"url\",\n"
            + "      \"createdAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"updatedAt\": \"2024-01-15T09:30:00Z\",\n"
            + "      \"tags\": [\n"
            + "        \"tags\"\n"
            + "      ],\n"
            + "      \"metadata\": {\n"
            + "        \"metadata\": \"metadata\"\n"
            + "      },\n"
            + "      \"allMetadata\": {\n"
            + "        \"allMetadata\": {\n"
            + "          \"allMetadata\": \"allMetadata\"\n"
            + "        }\n"
            + "      },\n"
            + "      \"conversationId\": {\n"
            + "        \"organizationId\": \"organizationId\",\n"
            + "        \"agentId\": \"agentId\",\n"
            + "        \"type\": \"AGENT\",\n"
            + "        \"appId\": \"appId\",\n"
            + "        \"referenceId\": \"referenceId\"\n"
            + "      },\n"
            + "      \"analysis\": {\n"
            + "        \"userRequest\": \"userRequest\",\n"
            + "        \"agentResponse\": \"agentResponse\",\n"
            + "        \"resolutionStatus\": \"resolutionStatus\",\n"
            + "        \"category\": \"category\",\n"
            + "        \"sentiment\": \"POSITIVE\",\n"
            + "        \"quality\": \"GOOD\",\n"
            + "        \"qualityReason\": \"MISSING_KNOWLEDGE\",\n"
            + "        \"resolvedByMaven\": true,\n"
            + "        \"primaryLanguage\": \"primaryLanguage\",\n"
            + "        \"predictedNps\": 1.1\n"
            + "      },\n"
            + "      \"summary\": {\n"
            + "        \"actionIds\": [\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        ],\n"
            + "        \"incompleteActionIds\": [\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          },\n"
            + "          {\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        ],\n"
            + "        \"insertCount\": 1,\n"
            + "        \"thumbsUpCount\": 1,\n"
            + "        \"thumbsDownCount\": 1,\n"
            + "        \"handoffCount\": 1,\n"
            + "        \"userMessageCount\": 1,\n"
            + "        \"handleTime\": 1000000,\n"
            + "        \"humanAgentResponseDelay\": 1000000,\n"
            + "        \"humanAgents\": [\n"
            + "          \"humanAgents\",\n"
            + "          \"humanAgents\"\n"
            + "        ],\n"
            + "        \"humanAgentsWithInserts\": [\n"
            + "          \"humanAgentsWithInserts\",\n"
            + "          \"humanAgentsWithInserts\"\n"
            + "        ],\n"
            + "        \"users\": [\n"
            + "          \"users\",\n"
            + "          \"users\"\n"
            + "        ],\n"
            + "        \"userIdentifiers\": [\n"
            + "          \"userIdentifiers\",\n"
            + "          \"userIdentifiers\"\n"
            + "        ],\n"
            + "        \"lastUserMessage\": \"lastUserMessage\",\n"
            + "        \"lastBotMessage\": \"lastBotMessage\"\n"
            + "      },\n"
            + "      \"deleted\": true,\n"
            + "      \"open\": true,\n"
            + "      \"llmEnabled\": true,\n"
            + "      \"simulationContext\": {\n"
            + "        \"additionalPromptText\": \"additionalPromptText\",\n"
            + "        \"persona\": \"CASUAL_BUDDY\",\n"
            + "        \"availableKnowledgeBases\": [\n"
            + "          {\n"
            + "            \"organizationId\": \"organizationId\",\n"
            + "            \"agentId\": \"agentId\",\n"
            + "            \"type\": \"AGENT\",\n"
            + "            \"appId\": \"appId\",\n"
            + "            \"referenceId\": \"referenceId\"\n"
            + "          }\n"
            + "        ]\n"
            + "      }\n"
            + "    }\n"
            + "  ],\n"
            + "  \"number\": 1,\n"
            + "  \"size\": 1,\n"
            + "  \"totalElements\": 1000000,\n"
            + "  \"totalPages\": 1\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
    @Test
    public void testDeliverMessage() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"status\":\"DELIVERED\"}"));
        DeliverMessageResponse response = client.conversation().deliverMessage(
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
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("POST", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"type\": \"user\",\n"
            + "  \"userId\": {\n"
            + "    \"type\": \"AGENT\",\n"
            + "    \"appId\": \"appId\",\n"
            + "    \"referenceId\": \"referenceId\"\n"
            + "  },\n"
            + "  \"message\": {\n"
            + "    \"conversationMessageId\": {\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"userId\": {\n"
            + "      \"referenceId\": \"referenceId\"\n"
            + "    },\n"
            + "    \"text\": \"text\",\n"
            + "    \"userMessageType\": \"USER\"\n"
            + "  }\n"
            + "}";
        JsonNode actualJson = objectMapper.readTree(actualRequestBody);
        JsonNode expectedJson = objectMapper.readTree(expectedRequestBody);
        Assertions.assertEquals(expectedJson, actualJson, "Request body structure does not match expected");
        if (actualJson.has("type") || actualJson.has("_type") || actualJson.has("kind")) {
            String discriminator = null;
            if (actualJson.has("type")) discriminator = actualJson.get("type").asText();
            else if (actualJson.has("_type")) discriminator = actualJson.get("_type").asText();
            else if (actualJson.has("kind")) discriminator = actualJson.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualJson.isNull()) {
            Assertions.assertTrue(actualJson.isObject() || actualJson.isArray() || actualJson.isValueNode(), "request should be a valid JSON value");
        }
        
        if (actualJson.isArray()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Array should have valid size");
        }
        if (actualJson.isObject()) {
            Assertions.assertTrue(actualJson.size() >= 0, "Object should have valid field count");
        }
        
        // Validate response body
        Assertions.assertNotNull(response, "Response should not be null");
        String actualResponseJson = objectMapper.writeValueAsString(response);
        String expectedResponseBody = ""
            + "{\n"
            + "  \"status\": \"DELIVERED\"\n"
            + "}";
        JsonNode actualResponseNode = objectMapper.readTree(actualResponseJson);
        JsonNode expectedResponseNode = objectMapper.readTree(expectedResponseBody);
        Assertions.assertEquals(expectedResponseNode, actualResponseNode, "Response body structure does not match expected");
        if (actualResponseNode.has("type") || actualResponseNode.has("_type") || actualResponseNode.has("kind")) {
            String discriminator = null;
            if (actualResponseNode.has("type")) discriminator = actualResponseNode.get("type").asText();
            else if (actualResponseNode.has("_type")) discriminator = actualResponseNode.get("_type").asText();
            else if (actualResponseNode.has("kind")) discriminator = actualResponseNode.get("kind").asText();
            Assertions.assertNotNull(discriminator, "Union type should have a discriminator field");
            Assertions.assertFalse(discriminator.isEmpty(), "Union discriminator should not be empty");
        }
        
        if (!actualResponseNode.isNull()) {
            Assertions.assertTrue(actualResponseNode.isObject() || actualResponseNode.isArray() || actualResponseNode.isValueNode(), "response should be a valid JSON value");
        }
        
        if (actualResponseNode.isArray()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Array should have valid size");
        }
        if (actualResponseNode.isObject()) {
            Assertions.assertTrue(actualResponseNode.size() >= 0, "Object should have valid field count");
        }
    }
}
