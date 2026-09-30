package com.mavenagi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenagi.MavenAGI;
import com.mavenagi.core.ObjectMappers;
import com.mavenagi.resources.commons.types.EntityIdBase;
import com.mavenagi.resources.conversationkickoffs.requests.CreateConversationKickoffRequest;
import com.mavenagi.resources.conversationkickoffs.types.ConversationKickoff;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConversationKickoffsWireTest {
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
    public void testCreateOrUpdate() throws Exception {
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"kickoffId\":{\"referenceId\":\"greet-returning-customer\",\"appId\":\"my-billing-system\",\"organizationId\":\"acme\",\"agentId\":\"support\",\"type\":\"CONVERSATION_KICKOFF\"},\"name\":\"Greet returning customer\",\"description\":\"Looks up the customer's open orders before the agent's first reply\",\"status\":\"INACTIVE\",\"createdAt\":\"2026-01-15T10:30:00Z\",\"updatedAt\":\"2026-01-15T10:30:00Z\"}"));
        ConversationKickoff response = client.conversationKickoffs().createOrUpdate(
            CreateConversationKickoffRequest
                .builder()
                .kickoffId(
                    EntityIdBase
                        .builder()
                        .referenceId("greet-returning-customer")
                        .build()
                )
                .name("Greet returning customer")
                .description("Looks up the customer's open orders before the agent's first reply")
                .build()
        );
        RecordedRequest request = server.takeRequest();
        Assertions.assertNotNull(request);
        Assertions.assertEquals("PUT", request.getMethod());
        // Validate request body
        String actualRequestBody = request.getBody().readUtf8();
        String expectedRequestBody = ""
            + "{\n"
            + "  \"kickoffId\": {\n"
            + "    \"referenceId\": \"greet-returning-customer\"\n"
            + "  },\n"
            + "  \"name\": \"Greet returning customer\",\n"
            + "  \"description\": \"Looks up the customer's open orders before the agent's first reply\"\n"
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
            + "  \"kickoffId\": {\n"
            + "    \"referenceId\": \"greet-returning-customer\",\n"
            + "    \"appId\": \"my-billing-system\",\n"
            + "    \"organizationId\": \"acme\",\n"
            + "    \"agentId\": \"support\",\n"
            + "    \"type\": \"CONVERSATION_KICKOFF\"\n"
            + "  },\n"
            + "  \"name\": \"Greet returning customer\",\n"
            + "  \"description\": \"Looks up the customer's open orders before the agent's first reply\",\n"
            + "  \"status\": \"INACTIVE\",\n"
            + "  \"createdAt\": \"2026-01-15T10:30:00Z\",\n"
            + "  \"updatedAt\": \"2026-01-15T10:30:00Z\"\n"
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
